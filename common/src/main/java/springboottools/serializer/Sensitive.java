package springboottools.serializer;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import springboottools.enums.SensitiveEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author FANWENKUI771
 * @create 2019-06-24
 * @description 脱敏注解
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotation
public @interface Sensitive {
    SensitiveEnum type() default SensitiveEnum.NAME;
}
