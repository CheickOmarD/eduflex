package technologia.eduflex.services.token;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import technologia.eduflex.models.Token;
import technologia.eduflex.models.Users;
import technologia.eduflex.repositories.TokenRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenRepository repository;

    @Override
    public Token save(Users users, String token) {
        Token savedToken = Token.builder()
                .token(token)
                // correction: "rovoked" devient "revoked"
                .revoked(false)
                .expired(false)
                .createdAt(LocalDateTime.now())
                // initialisation de logout à null car l'utilisateur
                // n'est pas encore déconnecté
                .logoutAt(null)
                .users(users)
                .build();
        return repository.save(savedToken);
    }


    @Override
    public void revokedAllUsersTokens(Users users) {
        List<Token> validTokens = repository
                .findAllValidTokensByUsersId(users.getId());

        if (validTokens.isEmpty()) {
            return;
        }
        validTokens.forEach(token -> {
            token.setRevoked(true);
            token.setExpired(true);
        });
        repository.saveAll(validTokens);
    }

    @Override
    public void revokeAllUsersTokens(Users users) {
        List<Token> tokens = repository
                .findAllByUsersIdAndExpiredIsFalseOrRevokedIsFalse(users.getId());
        if (tokens.isEmpty()) {
            return;
        }
        tokens.forEach(token -> {
            if (token.getUsers().equals(users)) {
                token.setRevoked(true);
                token.setExpired(true);
                token.setLogoutAt(LocalDateTime.now());
            }
        });
        repository.saveAll(tokens);
    }

}