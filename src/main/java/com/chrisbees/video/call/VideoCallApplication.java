package com.chrisbees.video.call;

import com.chrisbees.video.call.user.User;
import com.chrisbees.video.call.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VideoCallApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoCallApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserService service){
		return args -> {
			service.registerUser(User.builder()
							.email("bees@yahoo.com")
							.password("bees")
							.username("beesWorld")
					.build());

			service.registerUser(User.builder()
					.email("john@yahoo.com")
					.password("john")
					.username("johnWorld")
					.build());

			service.registerUser(User.builder()
					.email("anna@yahoo.com")
					.password("anna")
					.username("annaWorld")
					.build());

			System.out.println("Users registered successfully.");

		};
	}

}
