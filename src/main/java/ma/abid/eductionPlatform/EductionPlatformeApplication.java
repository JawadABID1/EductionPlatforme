package ma.abid.eductionPlatform;

import ma.abid.eductionPlatform.dto.role.RoleDto;
import ma.abid.eductionPlatform.dto.user.GetUserDto;
import ma.abid.eductionPlatform.dto.user.PostPutUserDto;
import ma.abid.eductionPlatform.services.role.RoleService;
import ma.abid.eductionPlatform.services.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class EductionPlatformeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EductionPlatformeApplication.class, args);
	}

}
