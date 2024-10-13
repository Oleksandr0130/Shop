package de.ait_tr.shop.service.interfaces;

import de.ait_tr.shop.model.dto.UserRegisterDTO;

public interface UserService {

    void register(UserRegisterDTO user);
}
