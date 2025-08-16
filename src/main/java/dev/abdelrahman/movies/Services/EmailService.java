package dev.abdelrahman.movies.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class EmailService {
    @Value("${app.name}")
    private String appName;

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendStandardEmail(String to, String name, String subject, String body) {
        String standardMessage = String.format(
            "Hello %s,\n\n%s\n\nBest regards,\n%s Team",
            name,
            body,
            this.appName
        );
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(standardMessage);
        mailSender.send(message);
    }

    public void sendPasswordResetEmail(String userEmail, String name, String resetLink) {
        sendStandardEmail(
            userEmail,
            name,
            "Password Reset Request",
            "We received a request to reset your password. Please click the link below to reset your password:\n" + resetLink +
            "\n\nIf you did not request this, please ignore this email. The link expires in 15 minutes."
        );
    }

    public void sendWelcomeEmail(String userEmail, String name, String username, String password) {
        sendStandardEmail(
            userEmail,
            name,
            String.format("Welcome to %s!", this.appName),
            String.format(
                "Your account has been created successfully.\n\nYour credentials: \nUsername: %s\nPassword: %s\n\nThank you for joining %s!",
                username, password, this.appName)
        );
    }
}
