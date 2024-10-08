package technologia.eduflex.services.Personnel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technologia.eduflex.dto.PersonnelResponse;
import technologia.eduflex.exceptions.AlreadyExistException;
import technologia.eduflex.exceptions.NotFoundException;
import technologia.eduflex.models.Personnel;
import technologia.eduflex.repositories.PersonnelRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonnelServiceImpl {

    @Autowired
    private PersonnelRepository personnelRepository;

    public PersonnelServiceImpl(PersonnelRepository personnelRepository) {
        this.personnelRepository = personnelRepository;
    }

    public PersonnelResponse creerPersonnel(Personnel personnel) {
        if (personnel != null) {
            if (personnelRepository.existsByEmail(personnel.getEmail())) {
                throw new AlreadyExistException("Cet email est déjà utilisé.");
            }
            if (personnelRepository.existsByPhoneNumber(personnel.getPhoneNumber())) {
                throw new AlreadyExistException("Ce numéro de téléphone est déjà utilisé.");
            }
            return mapToResponse(personnelRepository.save(personnel));
        }
        throw new IllegalArgumentException("Le personnel ne peut pas être nul.");
    }

    public PersonnelResponse modifierPersonnel(Long id, Personnel personnelDetails) {
        Personnel personnel = getPersonnelById(id);
        updatePersonnelDetails(personnel, personnelDetails);
        return mapToResponse(personnelRepository.save(personnel));
    }

    public List<PersonnelResponse> visualiserPersonnel() {
        return personnelRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public void supprimerPersonnel(Long id) {
        Personnel personnel = getPersonnelById(id);
        personnelRepository.delete(personnel);
    }

    private Personnel getPersonnelById(Long id) {
        return personnelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Personnel non trouvé"));
    }

    private void updatePersonnelDetails(Personnel personnel, Personnel personnelDetails) {
        if (personnelDetails != null) {
            if (personnelDetails.getNom() != null) {
                personnel.setNom(personnelDetails.getNom());
            }
            if (personnelDetails.getPrenom() != null) {
                personnel.setPrenom(personnelDetails.getPrenom());
            }
            if (personnelDetails.getEmail() != null) {
                personnel.setEmail(personnelDetails.getEmail());
            }
            if (personnelDetails.getPhoneNumber() != null) {
                if (personnelRepository.existsByPhoneNumber(personnelDetails.getPhoneNumber())) {
                    throw new AlreadyExistException("Ce numéro de téléphone est déjà utilisé.");
                }
                personnel.setPhoneNumber(personnelDetails.getPhoneNumber());
            }
        }
    }

    private PersonnelResponse mapToResponse(Personnel personnel) {
        if (personnel == null) {
            throw new NotFoundException("Personnel non trouvé");
        }

        if (personnel.getRole() == null || personnel.getRole().isEmpty()) {
            throw new NotFoundException("Ce rôle n'existe pas pour le personnel");
        }

        return PersonnelResponse.builder()
                .id(personnel.getId())
                .nom(personnel.getNom())
                .prenom(personnel.getPrenom())
                .email(personnel.getEmail())
                .phoneNumber(personnel.getPhoneNumber())
                .password(personnel.getPassword())
                .build();
    }
}

