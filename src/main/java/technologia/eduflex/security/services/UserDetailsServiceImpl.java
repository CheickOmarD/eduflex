package technologia.eduflex.security.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import technologia.eduflex.enums.Statut;
import technologia.eduflex.exceptions.NotFoundException;
import technologia.eduflex.models.Users;
import technologia.eduflex.repositories.UsersRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository repository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = repository.findByEmailAndStatut(email, Statut.ACTIVATER);
        if (users == null){
            throw new NotFoundException("Email ou mot de passe incorrect");
        }
        return UserDetailsImpl.build(users);
    }
}
