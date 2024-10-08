package technologia.eduflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technologia.eduflex.models.Token;

import java.util.List;
import java.util.Optional;
@Repository
public interface TokenRepository extends JpaRepository<Token,String> {
    Optional<Token> findByToken(String jwt);
    List<Token> findAllByUsersIdAndExpiredIsFalseOrRevokedIsFalse(Long id);
    Token findByTokenAndRevokedIsFalseOrExpiredIsFalse(String jwt);
    List<Token> findAllValidTokensByUsersId(Long id);
}
