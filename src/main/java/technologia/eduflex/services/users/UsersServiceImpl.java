package technologia.eduflex.services.users;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import technologia.eduflex.dto.RoleResponse;
import technologia.eduflex.dto.UsersResponse;
import technologia.eduflex.enums.Statut;
import technologia.eduflex.exceptions.AlreadyExistException;
import technologia.eduflex.exceptions.NotFoundException;
import technologia.eduflex.models.Users;
import technologia.eduflex.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;


     @Override
    public UsersResponse save(Users users) {

        Users email = usersRepository
                .findByEmailAndStatutNot(users.getEmail(), Statut.SUPPRIMER);
        if (email != null) {
            throw new AlreadyExistException("Cette adresse email est déjà utilisée.");
        }
        if (users.getPassword() != null && !users.getPassword().isEmpty()) {
            users.setPassword(passwordEncoder.encode(users.getPassword()));
        }
        return mapToResponse(usersRepository.save(users));
    }

    @Override
    public UsersResponse findByIdAndStatut(Long id, Statut statut) {
        Users users = usersRepository.findByIdAndStatut(id, statut);
        if (users == null) {
            throw new NotFoundException("Cet utilisateur n'existe pas.");
        }
        return mapToResponse(users);
    }

    @Override
    public List<UsersResponse> findByStatut(Statut statut) {
        List<Users> users = usersRepository.findByStatut(statut);
        users.sort(Comparator.comparing(Users::getId).reversed());
        return mapToResponse(users);
    }

    @Override
    public List<UsersResponse> findByStatutNot(Statut statut) {
        List<Users> users = usersRepository.findByStatutNot(statut);
        users.sort(Comparator.comparing(Users::getId).reversed());
        return mapToResponse(users);
    }

    @Override
    public UsersResponse update(Users users) {
        Users userToUpdate = usersRepository
                .findByIdAndStatutNot(users.getId(), Statut.ACTIVATER);
        if (userToUpdate == null) {
            throw new NotFoundException("Cet utilisateur n'existe pas.");
        }
        userToUpdate.setFirstName(users.getFirstName());
        userToUpdate.setLastName(users.getLastName());
        userToUpdate.setEmail(users.getEmail());
        userToUpdate.setPassword(users.getPassword());
        return mapToResponse(usersRepository.save(userToUpdate));
    }

    @Override
    public void delete(Long id) {
        Users users = usersRepository.findByIdAndStatutNot(id, Statut.SUPPRIMER);
        if (users == null) {
            throw new NotFoundException("Cet utilisateur n'existe pas.");
        }
        users.setStatut(Statut.SUPPRIMER);
    }

    @Override
    public UsersResponse activate(Users users) {
        Users userToActivate = usersRepository
                .findByIdAndStatutNot(users.getId(), Statut.SUPPRIMER);
        if (userToActivate == null) {
            throw new NotFoundException("Cet utilisateur n'existe pas.");
        }
        userToActivate.setStatut(Statut.ACTIVATER);
        return mapToResponse(usersRepository.save(userToActivate));
    }

    @Override
    public UsersResponse mapToResponse(Users users) {
        List<RoleResponse> roles = new ArrayList<>();
        if (users.getRoles() != null && !users.getRoles().isEmpty()) {
            roles = users.getRoles().stream()
                    .map(role -> RoleResponse.builder()
                            .id(role.getId())
                            .name(role.getName())
                            .build()).toList();
        }
        return UsersResponse.builder()
                .id(users.getId())
                .firstName(users.getFirstName())
                .lastName(users.getLastName())
                .email(users.getEmail())
                .roles(roles)
                .statut(users.getStatut())
                .build();
    }

    @Override
    public List<UsersResponse> mapToResponse(List<Users> users) {
        List<UsersResponse> usersResponse = new ArrayList<>();
        for (Users user : users) {
            usersResponse.add(mapToResponse(user));
        }
        return usersResponse;
    }

    public List<Users> getAllUsers(String role) {
        if (role != null && !role.isEmpty()) {
            return usersRepository.findByRoles_Name(role);
        }
        return usersRepository.findAll();
    }



}
