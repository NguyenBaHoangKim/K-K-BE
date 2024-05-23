package com.KKApplication.KK;

import com.KKApplication.KK.entity.User;
import com.KKApplication.KK.repository.UserRepo;
import com.KKApplication.KK.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class KKApplication {

	public static void main(String[] args) {
		SpringApplication.run(KKApplication.class, args);
	}

	@Bean
	@Autowired
	public CommandLineRunner commandLineRunner(UserServiceImpl userService){
		return runner -> {
			//creatUser(userService);
		};
	}

	private void creatUser(UserServiceImpl userService) throws IOException {
//		User user = new User("hoangkim","kim@gmail","mkhau" );
		User user = new User();
		user.setEmail("test@gmail.com");
		user.setUsername("test");
		user.setPassword("password");

//		System.out.println("hihiii");
		System.out.println(user);
//		Path path = Paths.get("F:\\meo.jpg");
//		byte[] avatar = Files.readAllBytes(path);
//		System.out.println("byte img: " + avatar);
//		user.setAvatar(avatar);

		//userService.saveUser(user, "F:\\meo.jpg");
	}
}

