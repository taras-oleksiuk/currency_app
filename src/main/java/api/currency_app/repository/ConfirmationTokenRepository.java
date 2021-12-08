package api.currency_app.repository;

import api.currency_app.registration.token.ConfirmationToken;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ConfirmationTokenRepository
        extends JpaRepository<ConfirmationToken, Long> {
    Optional<ConfirmationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationToken ct "
    + "SET ct.confirmedAt = ?2 "
    + "WHERE  ct.token = ?1")
    int updateConfirmed(String token,
                        LocalDateTime confirmed);
}
