package technologia.eduflex.services.etablissement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import technologia.eduflex.dto.EtablissementResponse;
import technologia.eduflex.exceptions.NotFoundException;
import technologia.eduflex.models.Etablissement;
import technologia.eduflex.repositories.EtablissementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EtablissementServiceImpl implements EtablissementService {

    private final EtablissementRepository etablissementRepository;

    @Override
    public EtablissementResponse save(Etablissement etablissement) {
        Etablissement savedEtablissement = etablissementRepository.save(etablissement);
        return mapToResponse(savedEtablissement);
    }

    @Override
    public EtablissementResponse findByIdAndName(Long id, String name) {
        Etablissement etablissement = etablissementRepository.findByIdAndName(id, name);
        if (etablissement == null) {
            throw new NotFoundException("Etablissement avec l'ID " + id + " et le nom '" + name + "' non trouvé.");
        }
        return mapToResponse(etablissement);
    }

    @Override
    public EtablissementResponse findByIdAndNameNot(Long id, String name) {
        Etablissement etablissement = etablissementRepository.findByIdAndNameNot(id, name);
        if (etablissement == null) {
            throw new NotFoundException("Etablissement avec l'ID " + id + " et non le nom '" + name + "' non trouvé.");
        }
        return mapToResponse(etablissement);
    }

    @Override
    public EtablissementResponse findByName(String name) {
        Etablissement etablissement = etablissementRepository.findByName(name);
        if (etablissement == null) {
            throw new NotFoundException("Etablissement avec le nom '" + name + "' non trouvé.");
        }
        return mapToResponse(etablissement);
    }

    @Override
    public EtablissementResponse findByNameAndAdress(String name, String address) {
        Etablissement etablissement = etablissementRepository.findByNameAndAddress(name, address);
        if (etablissement == null) {
            throw new NotFoundException("Etablissement avec le nom '" + name + "' et l'adresse '" + address + "' non trouvé.");
        }
        return mapToResponse(etablissement);
    }

    @Override
    public List<EtablissementResponse> findAll() {
        return etablissementRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList(); // Utilisation de toList() pour collecter les résultats
    }

    @Override
    public EtablissementResponse update(Etablissement etablissement) {
        if (!etablissementRepository.existsById(etablissement.getId())) {
            throw new NotFoundException("Etablissement avec l'ID " + etablissement.getId() + " non trouvé pour la mise à jour.");
        }
        Etablissement updatedEtablissement = etablissementRepository.save(etablissement);
        return mapToResponse(updatedEtablissement);
    }

    @Override
    public void delete(Long id) {
        if (!etablissementRepository.existsById(id)) {
            throw new NotFoundException("Etablissement avec l'ID " + id + " non trouvé pour la suppression.");
        }
        etablissementRepository.deleteById(id);
    }

    private EtablissementResponse mapToResponse(Etablissement etablissement) {
        return new EtablissementResponse(
                etablissement.getId(),
                etablissement.getName(),
                etablissement.getAddress(),
                etablissement.getDescription() // Ajustez selon les champs réels de la classe Etablissement
        );
    }
}
