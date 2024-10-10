package technologia.eduflex.services.tuteur;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import technologia.eduflex.dto.TuteurResponse;
import technologia.eduflex.exceptions.NotFoundException;
import technologia.eduflex.models.Tuteur;
import technologia.eduflex.repositories.TuteurRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TuteurServiceImpl implements TuteurService {

    private final TuteurRepository tuteurRepository;

    @Override
    public TuteurResponse save(Tuteur tuteur) {
        if (tuteur == null) {
            throw new IllegalArgumentException("Le tuteur ne peut pas être null.");
        }
        Tuteur savedTuteur = tuteurRepository.save(tuteur);
        return mapToResponse(savedTuteur);
    }

    @Override
    public TuteurResponse findByIdAndFirstname(Long id, String firstname) {
        Tuteur foundTuteur = tuteurRepository.findByIdAndFirstname(id, firstname);
        if (foundTuteur == null) {
            throw new NotFoundException("Tuteur avec l'ID " + id + " et le prénom '" + firstname + "' non trouvé.");
        }
        return mapToResponse(foundTuteur);
    }

    @Override
    public TuteurResponse findByFirstNameAndLastName(String firstname, String lastname) {
        Tuteur foundTuteur = tuteurRepository.findByFirstNameAndLastName(firstname, lastname);
        if (foundTuteur == null) {
            throw new NotFoundException("Tuteur avec le prénom '" + firstname + "' et le nom '" + lastname + "' non trouvé.");
        }
        return mapToResponse(foundTuteur);
    }

    @Override
    public TuteurResponse findByIdAndFistname(Long id, String firstname) {
        Tuteur foundTuteur = tuteurRepository.findByIdAndFirstname(id, firstname);
        if (foundTuteur == null) {
            throw new NotFoundException("Tuteur avec l'ID " + id + " et le prénom '" + firstname + "' non trouvé.");
        }
        return mapToResponse(foundTuteur);
    }

    @Override
    public List<TuteurResponse> findAll() {
        return tuteurRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public TuteurResponse update(Tuteur tuteur) {
        if (tuteur == null || tuteurRepository.findById(tuteur.getId()) == null) {
            throw new NotFoundException("Tuteur avec l'ID " + (tuteur != null ? tuteur.getId() : "inconnu") + " non trouvé pour la mise à jour.");
        }
        Tuteur updatedTuteur = tuteurRepository.save(tuteur);
        return mapToResponse(updatedTuteur);
    }

    @Override
    public void delete(Long id) {
        if (tuteurRepository.findById(id) == null) {
            throw new NotFoundException("Tuteur avec l'ID " + id + " non trouvé pour la suppression.");
        }
        tuteurRepository.deleteById(id);
    }

    private TuteurResponse mapToResponse(Tuteur tuteur) {
        return new TuteurResponse(
                tuteur.getId(),
                tuteur.getFirstname(),
                tuteur.getLastname(),
                tuteur.getEmail() // Ajustez selon les champs réels de la classe Tuteur
        );
    }
}
