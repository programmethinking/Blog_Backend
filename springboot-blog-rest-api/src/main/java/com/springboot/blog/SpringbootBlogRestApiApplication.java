package com.springboot.blog;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
		title = "Blog APP",
		version = "1.0",
		description = "Blog APP",
		contact=@Contact(
				name = "Blog APP",
				url = "https://github.com/programmethinking",
				email = "hnucmx1998@gmail.com"
		),
		license = @License(
				name = "Apache 2.0",
				url = "https://github.com/programmethinking/license"
		)
	)
)
public class SpringbootBlogRestApiApplication {
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringbootBlogRestApiApplication.class, args);
	}

}
