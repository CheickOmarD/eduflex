package technologia.eduflex.services.Etudiant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technologia.eduflex.dto.EtudiantResponse;
import technologia.eduflex.enums.Statut;
import technologia.eduflex.exceptions.AlreadyExistException;
import technologia.eduflex.exceptions.NotFoundException;
import technologia.eduflex.models.Etudiant;
import technologia.eduflex.models.Role;
import technologia.eduflex.repositories.EtudiantRepository;
import technologia.eduflex.repositories.RoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EtudiantServiceImpl {

    @Autowired
    private EtudiantRepository etudiantRepository;
    private RoleRepository roleRepository;

    public EtudiantResponse creerEtudiant(Etudiant etudiant) {
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

    public EtudiantResponse modifierEtudiant(Long id, Etudiant etudiantDetails) {
        Etudiant etudiant = getEtudiantById(id);
        updateEtudiantDetails(etudiant, etudiantDetails);
        return mapToResponse(etudiantRepository.save(etudiant));
    }

    public List<EtudiantResponse> visualiserEtudiants() {
        return etudiantRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public void supprimerEtudiant(Long id) {
        Etudiant etudiant = getEtudiantById(id);
        etudiantRepository.delete(etudiant);
    }

    private Etudiant getEtudiantById(Long id) {
        return etudiantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Étudiant non trouvé"));
    }

    private void updateEtudiantDetails(Etudiant etudiant, Etudiant etudiantDetails) {
        if (etudiantDetails != null) {
            if (etudiantDetails.getNom() != null) {
                etudiant.setNom(etudiantDetails.getNom());
            }
            if (etudiantDetails.getPrenom() != null) {
                etudiant.setPrenom(etudiantDetails.getPrenom());
            }
            if (etudiantDetails.getDateNaissance() != null) {
                etudiant.setDateNaissance(etudiantDetails.getDateNaissance());
            }
            if (etudiantDetails.getClasse() != null) {
                etudiant.setClasse(etudiantDetails.getClasse());
            }
            if (etudiantDetails.getTuteur() != null) {
                etudiant.setTuteur(etudiantDetails.getTuteur());
            }
        }
    }

    private EtudiantResponse mapToResponse(Etudiant etudiant) {
        if (etudiant == null) {
            return null; // Ou lancer une exception si souhaité
        }
        return EtudiantResponse.builder()
                .id(etudiant.getId())
                .nom(etudiant.getNom())
                .prenom(etudiant.getPrenom())
                .dateNaissance(String.valueOf(etudiant.getDateNaissance()))
                .classe(etudiant.getClasse())
                .tuteur(etudiant.getTuteur())
                .build();
    }
}
