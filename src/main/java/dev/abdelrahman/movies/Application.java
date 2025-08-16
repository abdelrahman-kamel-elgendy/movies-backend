package dev.abdelrahman.movies;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import dev.abdelrahman.movies.Models.User.Role;
import dev.abdelrahman.movies.Models.User.User;
import dev.abdelrahman.movies.Repositories.UserRepository;


@SpringBootApplication
public class Application {

	@Value("${admin.email}")
	private String adminEmail;
	@Value("${admin.password}")
	private String adminPassword;	
	@Value("${admin.firstName}")
	private String adminFirstName;	
	@Value("${admin.lastName}")
	private String adminLastName;	
	@Value("${admin.username}")
	private String adminUsername;	
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
    public CommandLineRunner createAdminUser(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder
    ) {
        return args -> {
            if (!userRepository.existsByEmail(adminEmail)) {
                User admin = new User();
                admin.setEmail(adminEmail);
                admin.setUsername(adminUsername);
                admin.setPassword(passwordEncoder.encode(adminPassword));
                admin.setRole(Role.ADMIN);
                admin.setFitstName(adminFirstName);
                admin.setLastName(adminLastName);
                userRepository.save(admin);
                System.out.println("Admin user created! ");
            }
			else {
				System.out.println("Admin user already exists.");
			}
        };
    }
}
