package de.ait_tr.shop.repository;

import de.ait_tr.shop.model.entity.ConfirmationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmationCodeRepository extends JpaRepository<ConfirmationCode, Long> {

    // Метод для поиска кода по его значению
    Optional<ConfirmationCode> findByCode(String code);
}
