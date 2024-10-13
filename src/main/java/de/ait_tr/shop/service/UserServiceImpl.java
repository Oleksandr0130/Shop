package de.ait_tr.shop.service;

import de.ait_tr.shop.model.dto.UserRegisterDTO;
import de.ait_tr.shop.model.entity.ConfirmationCode;
import de.ait_tr.shop.model.entity.User;
import de.ait_tr.shop.repository.UserRepository;
import de.ait_tr.shop.service.interfaces.ConfirmationCodeService;
import de.ait_tr.shop.service.interfaces.EmailService;
import de.ait_tr.shop.service.interfaces.RoleService;
import de.ait_tr.shop.service.interfaces.UserService;
import de.ait_tr.shop.service.mapping.UserMappingService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final EmailService emailService;
    private final UserMappingService userMappingService;
    private final ConfirmationCodeService confirmationCodeService;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, RoleService roleService, EmailService emailService, UserMappingService userMappingService, ConfirmationCodeService confirmationCodeService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.emailService = emailService;
        this.userMappingService = userMappingService;
        this.confirmationCodeService = confirmationCodeService;
    }

    @Transactional
    @Override
    public void register(UserRegisterDTO registerDTO) {
        User user = userMappingService.mapDtoToEntity(registerDTO);

        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());

        //проверка существует ли пользователь с таким емаил
        if (optionalUser.isPresent() && optionalUser.get().isActive()) {
            throw new RuntimeException("Email " + user.getEmail() + " already in use");
        }
        if (optionalUser.isPresent()){
            // Пользователи в базе со статусом active- false
            user = optionalUser.get();
            ConfirmationCode codeOld = confirmationCodeService.findCodeByUser(user).orElse(null);
            if (codeOld != null) {
                confirmationCodeService.remove(codeOld);
            }
        }else {
            // Регистрация нового пользователя
            // Присваеваем роль User новому пользователю
            user.setRoles(Set.of(roleService.getRoleUser()));
        }
        // Устанавливаем зашифрованый пароль
        user.setPassword(passwordEncoder.encode(registerDTO.password()));

        user.setActive(false);

        // Сохраняем пользователя в БД
        userRepository.save(user);

        // отправляем письмо с кодом подтверждения
        emailService.sendConfirmationEmail(user);
    }

    @Override
    public String confirmationMailByCode(String code) {
        ConfirmationCode confirmationCode = confirmationCodeService.findByCode(code).orElseThrow(
                () -> new RuntimeException("Confirmation code not found")
        );

        if (confirmationCode.getExpired().isAfter(LocalDateTime.now())){
            User user = confirmationCode.getUser();
            user.setActive(true);
            userRepository.save(user);
            return  user.getEmail() + " confirmed!";
        }
        throw new RuntimeException("Wrong code");

    }
}
