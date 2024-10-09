package technologia.eduflex.services.Etudiant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import technologia.eduflex.dto.EtudiantResponse;
import technologia.eduflex.enums.Statut;
import technologia.eduflex.exceptions.AlreadyExistException;
import technologia.eduflex.exceptions.NotFoundException;
import technologia.eduflex.models.Classe;
import technologia.eduflex.models.Etudiant;
import technologia.eduflex.models.Role;
import technologia.eduflex.repositories.EtudiantRepository;
import technologia.eduflex.repositories.RoleRepository;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class EtudiantServiceImpl {

    private final   EtudiantRepository etudiantRepository;
    private final RoleRepository roleRepository;


    public EtudiantResponse save(Etudiant etudiant) {
        Etudiant email = etudiantRepository.findByEmailAndStatut(etudiant.getEmail(), Statut.ACTIVATER);
        if (email != null) {
            throw new AlreadyExistException("Ce email existe deja ! ");
        }
        Role role = roleRepository.findByNameAndStatut("ETUDIANT", Statut.ACTIVATER);
        Etudiant etudiantSave = etudiantRepository.save(etudiant);
        if (etudiant.getRole()== null){
            etudiantSave.setRole(role);
        }

        return mapToResponse(etudiantSave);
    }


    public Etudiant update(Long id, Etudiant updatedEtudiant) {
        Etudiant etudiant = etudiantRepository.findByIdAndFirstNameAndLastNameAndBirthday(
                id,
                updatedEtudiant.getFirstName(),
                updatedEtudiant.getLastName(),
                updatedEtudiant.getBirthday()
        );

        if (etudiant != null) {
            etudiant.setFirstName(updatedEtudiant.getFirstName());
            etudiant.setLastName(updatedEtudiant.getLastName());
            etudiant.setBirthday(updatedEtudiant.getBirthday());
            etudiant.setEmail(updatedEtudiant.getEmail());
            etudiant.setClasse(updatedEtudiant.getClasse());
            etudiant.setTuteur(updatedEtudiant.getTuteur());

            if (updatedEtudiant.getNotes() != null) {
                etudiant.setNotes(updatedEtudiant.getNotes());
            }

            if (updatedEtudiant.getRole() != null) {
                etudiant.setRole(updatedEtudiant.getRole());
            }
            etudiant.setCreatedAt(LocalDateTime.now());
            return etudiantRepository.save(etudiant);
        }

        throw new NotFoundException("L'étudiant avec l'id " + id + " n'a pas été trouvé.");
    }



    public Etudiant delete(Etudiant etudiant) {
        // Vérifie si l'objet etudiant est null
        if (etudiant == null) {
            throw new IllegalArgumentException("L'objet Etudiant ne peut pas être null.");
        }
        // Vérifie si l'ID de l'étudiant est nul
        if (etudiant.getId() == null) {
            throw new IllegalArgumentException("L'ID de l'étudiant ne peut pas être null.");
        }
        // Recherche l'étudiant à supprimer
        Etudiant foundEtudiant = etudiantRepository.findByIdAndClasseAndBirthday(
                etudiant.getId(),
                etudiant.getClasse(),
                etudiant.getBirthday()
        );
        // Vérifie si l'étudiant a été trouvé
        if (foundEtudiant == null) {
            throw new NotFoundException("Etudiant non trouvé avec l'ID : " + etudiant.getId() +
                    ", Classe : " + etudiant.getClasse() +
                    ", Birthday : " + etudiant.getBirthday());
        }

        // Vérifie si l'étudiant a des notes avant de supprimer
        if (foundEtudiant.getNotes() != null && !foundEtudiant.getNotes().isEmpty()) {
            throw new IllegalStateException("L'étudiant ne peut pas être supprimé car il a des notes associées.");
        }

        // Supprime l'étudiant
        etudiantRepository.delete(foundEtudiant);

        // Retourne l'étudiant supprimé
        return foundEtudiant;
    }

    public Optional<Etudiant> findByFirstNameAndLastnameAndClasse(String firstName, String lastName, Classe classe) {
        if (firstName == null || lastName == null || classe == null) {
            return Optional.empty(); // Vérification des paramètres
        }
        // Supposons que vous ayez un moyen de récupérer l'ID de l'étudiant basé sur le prénom, le nom et la classe
        Optional<Etudiant> etudiantOpt = etudiantRepository.findByFirstNameAndLastnameAndClasse(firstName, lastName, classe);

        if (etudiantOpt.isPresent()) {
            Etudiant etudiant = etudiantOpt.get();
            // Récupération de l'étudiant en utilisant findByIdAndFirstNameAndLastnameAndClasse
            return etudiantRepository.findByIdAndFirstNameAndLastnameAndClasse(etudiant.getId(), firstName, lastName, classe);
        }

        return Optional.empty();
    }


    private EtudiantResponse mapToResponse(Etudiant etudiant) {
        if (etudiant == null) {
            return null;
        }
        return EtudiantResponse.builder()
                .id(etudiant.getId())
                .firstName(etudiant.getFirstName())
                .lastName(etudiant.getLastName())
                .Birthday(etudiant.getBirthday())
                .classe(etudiant.getClasse())
                .tuteur(etudiant.getTuteur())
                .build();
    }
}
