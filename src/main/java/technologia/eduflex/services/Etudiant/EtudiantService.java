package technologia.eduflex.services.Etudiant;

import technologia.eduflex.models.Etudiant;

import java.util.List;

public interface EtudiantService {
    Etudiant creerEtudiant(Etudiant etudiant);

    Etudiant modifierEtudiant(Long id, Etudiant etudiantDetails);

    List<Etudiant> visualiserEtudiants();

    void supprimerEtudiant(Long id);
}
