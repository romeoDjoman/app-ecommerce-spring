package com.romeoDjoman.app_ecommerce_spring;

import com.romeoDjoman.app_ecommerce_spring.entity.Role;
import com.romeoDjoman.app_ecommerce_spring.entity.User;
import com.romeoDjoman.app_ecommerce_spring.enums.RoleType;
import com.romeoDjoman.app_ecommerce_spring.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;


@AllArgsConstructor
@EnableScheduling
@SpringBootApplication
public class AppEcommerceSpringApplication implements CommandLineRunner {
	UserRepository userRepository;
	PasswordEncoder passwordEncoder;


	public static void main(String[] args) {
		SpringApplication.run(AppEcommerceSpringApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		createUserIfNotExists("admin@gmail.com", "admin", "admin", RoleType.ADMIN);
		createUserIfNotExists("author@gmail.com", "author", "author", RoleType.AUTHOR);
		createUserIfNotExists("reviewer@gmail.com", "reviewer", "reviewer", RoleType.REVIEWER);
	}

	private void createUserIfNotExists(String email, String name, String password, RoleType roleType) {
		this.userRepository.findByEmail(email)
				.orElseGet(() -> {
					User user = User.builder()
							.active(true)
							.name(name)
							.pwd(passwordEncoder.encode(password))
							.email(email)
							.role(Role.builder().label(roleType).build())
							.build();
					return this.userRepository.save(user);
				});
	}


	/*@Override
	public void run(String... args) throws Exception {
		User admin = User.builder()
				.active(true)
				.name("admin")
				.pwd(passwordEncoder.encode("admin"))
				.email("admin@gmail.com")
				.role(
						Role.builder()
								.label(RoleType.ADMIN)
								.build()
				)
				.build();
		this.userRepository.findByEmail("admin@gmail.com")
				.orElse(this.userRepository.save(admin));

		User author = User.builder()
				.active(true)
				.name("author")
				.pwd(passwordEncoder.encode("author"))
				.email("author@gmail.com")
				.role(
						Role.builder()
								.label(RoleType.AUTHOR)
								.build()
				)
				.build();
		this.userRepository.findByEmail("author@gmail.com")
				.orElse(this.userRepository.save(author));


		User reviewer = User.builder()
				.active(true)
				.name("reviewer")
				.pwd(passwordEncoder.encode("reviewer"))
				.email("reviewer@gmail.com")
				.role(
						Role.builder()
								.label(RoleType.REVIEWER)
								.build()
				)
				.build();
		this.userRepository.findByEmail("reviewer@gmail.com")
				.orElse(this.userRepository.save(reviewer));
	}*/
}