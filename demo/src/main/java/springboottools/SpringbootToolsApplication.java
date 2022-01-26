package springboottools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import springboottools.config.JacksonConfig;

@SpringBootApplication
public class SpringbootToolsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootToolsApplication.class, args);
	}

}
