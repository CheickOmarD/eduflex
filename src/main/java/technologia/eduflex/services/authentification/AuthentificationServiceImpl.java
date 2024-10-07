package technologia.eduflex.services.authentification;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import technologia.eduflex.dto.AuthentificationRequest;
import technologia.eduflex.dto.AuthentificationResponse;
import technologia.eduflex.enums.Statut;
import technologia.eduflex.exceptions.NotFoundException;
import technologia.eduflex.models.Role;
import technologia.eduflex.models.Users;
import technologia.eduflex.repositories.RoleRepository;
import technologia.eduflex.repositories.UsersRepository;
import technologia.eduflex.security.jwt.JwtService;
import technologia.eduflex.security.services.UserDetailsImpl;
import technologia.eduflex.services.token.TokenService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthentificationServiceImpl implements AuthentificationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RoleRepository roleRepository;
    private final TokenService tokenService;
    private final UsersRepository usersRepository;

    @Override
    public AuthentificationResponse authentificate(AuthentificationRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtService.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();

            Users author = getAuthor();
            if (author != null) {
                tokenService.revokedAllUsersTokens(author);
                tokenService.save(author, token);
            }

            return new AuthentificationResponse(
                    userDetails.getId(),
                    userDetails.getFirstName(),
                    userDetails.getLastName(),
                    userDetails.getEmail(),
                    new ArrayList<>(),
                    userDetails.getStatut(),
                    token
            );
        } catch (BadCredentialsException e) {
            throw new NotFoundException("Email ou mot de passe incorrect");
        }
    }

    public Users getAuthor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            return usersRepository.findByEmailAndStatut(email, Statut.ACTIVATER);
        }
        return null;
    }

    Role getRoleByName(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            throw new NotFoundException("Role incorrect");
        }
        return role;
    }
}

