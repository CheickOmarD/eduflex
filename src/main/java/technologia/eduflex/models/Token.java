package technologia.eduflex.models;

import jakarta.persistence.*;
import lombok.*;
import technologia.eduflex.models.Users;

import java.time.LocalDateTime;

@Entity
@ToString
@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String token;
    private boolean revoked;
    private boolean expired;
    private LocalDateTime createdAt;
    private LocalDateTime logoutAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;
}
