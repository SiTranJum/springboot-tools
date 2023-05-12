package springboottools.unifyManage;

import java.lang.annotation.*;


/**
 * @author sitran
 * @version 1.0.0
 * @description 统一入口打日志
 * @date 2023/2/9 19:00
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAspect {

}