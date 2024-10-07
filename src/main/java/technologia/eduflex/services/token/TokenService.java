package technologia.eduflex.services.token;


import technologia.eduflex.models.Token;
import technologia.eduflex.models.Users;

public interface TokenService {
    Token save(Users users, String token);
    void  revokedAllUsersTokens(Users users);

    void revokeAllUsersTokens(Users users);
}