package technologia.eduflex.services.Personnel;

import technologia.eduflex.models.Personnel;

import java.util.List;

public interface PersonnelService {
    Personnel creerPersonnel(Personnel personnel);

    List<Personnel> visualiserPersonnel();
}
