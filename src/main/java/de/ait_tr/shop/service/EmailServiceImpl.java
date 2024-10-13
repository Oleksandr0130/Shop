package de.ait_tr.shop.service;

import de.ait_tr.shop.model.entity.User;
import de.ait_tr.shop.service.interfaces.ConfirmationCodeService;
import de.ait_tr.shop.service.interfaces.EmailService;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final Configuration mailConfiguration;
    private final ConfirmationCodeService codeService;

    private final static String HOST = "http://localhost:8080";

    public EmailServiceImpl(JavaMailSender mailSender, Configuration mailConfiguration, ConfirmationCodeService codeService) {
        this.mailSender = mailSender;
        this.mailConfiguration = mailConfiguration;
        this.codeService = codeService;

        // Настройка кодировки
        this.mailConfiguration.setDefaultEncoding("UTF-8");
        //указываем папку с шаблонами
        this.mailConfiguration.setTemplateLoader(new ClassTemplateLoader(this.getClass(), "/mail"));
    }

    @Override
    public void sendConfirmationEmail(User user) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            String emailText = generateEmailText(user);

            String fromAddress = System.getenv("MAIL_USERNAME");
            helper.setFrom(fromAddress);
            helper.setTo(user.getEmail());
            helper.setSubject("Registration Confirmation");
            helper.setText(emailText, true);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateEmailText(User user) {
        try {

            Template template = mailConfiguration.getTemplate("confirm_reg_mail (1).ftlh");
            String code = codeService.generateConfirmationCode(user);

//        http://localhost:8080/confirm?code=значение_кода
            String confirmationLink = HOST + "confirmation?code=" + code;

            Map<String, Object> model = new HashMap<>();
            model.put("name", user.getUserName());
            model.put("confirmationLink", confirmationLink);

            // генерируем текст письма
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        }catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
