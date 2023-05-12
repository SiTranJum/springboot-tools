package springboottools.unifyManage;

import java.lang.annotation.*;

/**
 * @author sitran
 * @version 1.0.0
 * @description 当类被修饰了@AustinResult注解，那这个类的方法都是返回BasicResultvo对象，不需要在方法定义下显式写了。
 * @date 2023/2/9 19:00
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResultAspect {
}
