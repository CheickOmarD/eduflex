package technologia.eduflex.services.logout;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import technologia.eduflex.exceptions.TokenExpiredException;
import technologia.eduflex.models.Token;
import technologia.eduflex.repositories.TokenRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LogoutServiceImpl implements LogoutService, LogoutHandler {

    private final TokenRepository tokenRepository;

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            Token storedToken = tokenRepository.findByTokenAndRevokedIsFalseOrExpiredIsFalse(jwt);
            if (storedToken != null) {
                storedToken.setExpired(true);
                storedToken.setRevoked(true);
                storedToken.setLogoutAt(LocalDateTime.now());
                tokenRepository.save(storedToken);
                SecurityContextHolder.clearContext();
            } else {
                throw new TokenExpiredException("Le token est invalide ou déjà expiré.");
            }
        } else {
            throw new TokenExpiredException("Le token n'a pas été trouvé dans l'en-tête Authorization.");
        }
    }
}

