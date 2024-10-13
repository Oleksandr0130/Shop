package de.ait_tr.shop.service.interfaces;

import de.ait_tr.shop.model.entity.User;

public interface ConfirmationCodeService {

    // Метод для генерации кода подтверждения
    String generateConfirmationCode(User user);
}
