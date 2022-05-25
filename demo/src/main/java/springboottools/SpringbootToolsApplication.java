package springboottools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;
import springboottools.annotation.EnableJacksonConfig;
import springboottools.config.JacksonConfig;

@SpringBootApplication
@EnableJacksonConfig
public class SpringbootToolsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootToolsApplication.class, args);
	}

}
