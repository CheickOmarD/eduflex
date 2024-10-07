package technologia.eduflex.services.authentification;


import technologia.eduflex.dto.AuthentificationRequest;
import technologia.eduflex.dto.AuthentificationResponse;
import technologia.eduflex.models.Users;

public interface AuthentificationService {
    AuthentificationResponse authentificate(AuthentificationRequest authentificationRequest);
    Users getAuthor();
}
