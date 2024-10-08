package technologia.eduflex.security;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import technologia.eduflex.enums.Statut;
import technologia.eduflex.models.Role;
import technologia.eduflex.models.Users;
import technologia.eduflex.repositories.RoleRepository;
import technologia.eduflex.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class SetupLoaderData implements ApplicationListener<ContextRefreshedEvent> {

    private final UsersRepository usersRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private boolean alreadySetup = false;

    public void onApplicationEvent(ContextRefreshedEvent event){
        if (alreadySetup) {
            return;
        }

        final Role comptableRole = createRoleIfNotFound("COMPTABLE");
        final Role enseignantRole = createRoleIfNotFound("ENSEIGNANT");
        final Role etudiantRole = createRoleIfNotFound("ETUDIANT");
        final Role userRole = createRoleIfNotFound("USERS");
        final Role adminRole = createRoleIfNotFound("ADMIN");
        final List<Role> adminRoles = new ArrayList<>(Arrays.asList(
                etudiantRole,comptableRole,enseignantRole,userRole,adminRole
        ));
        createUsersIfNotFound(adminRoles );
        alreadySetup = true;
    }

    void createUsersIfNotFound(List<Role> role){
        Users users = usersRepository.findByEmailAndStatut("eduflex@gmail.com", Statut.ACTIVATER);
        if (users == null) {
            users = new Users();
            users.setFirstName("BKO DESIGN");
            users.setLastName("SCHOOL");
            users.setEmail("eduflex@gmail.com");
            users.setPassword(passwordEncoder.encode(
                    "eduflex@12!"));
        }
        users.setRoles(role);
        usersRepository.save(users);
    }

    Role createRoleIfNotFound(final String name){
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role = roleRepository.save(role);
        }
        return role;
    }
}
