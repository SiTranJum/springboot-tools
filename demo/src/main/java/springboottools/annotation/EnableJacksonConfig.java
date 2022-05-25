package springboottools.annotation;

import org.springframework.context.annotation.Import;
import springboottools.config.JacksonConfig;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Import(JacksonConfig.class)
public @interface EnableJacksonConfig {
}
