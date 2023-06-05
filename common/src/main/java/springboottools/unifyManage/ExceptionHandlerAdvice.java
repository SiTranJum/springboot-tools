package springboottools.unifyManage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;


/**
 * @author sitran
 * @version 1.0.0
 * @description 拦截异常统一返回
 * @date 2023/2/9 19:00
 */
@ControllerAdvice(basePackages = "springboottools.controller")
@ResponseBody
public class ExceptionHandlerAdvice {
    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    public ExceptionHandlerAdvice() {
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public R exceptionResponse(Exception e) {
        R result = R.fail(RespStatusEnum.ERROR_500, "\r\n" + e.getMessage() + "\r\n");
        log.error(e.getMessage());
        return result;
    }

    @ExceptionHandler({CommonException.class})
    @ResponseStatus(HttpStatus.OK)
    public R commonResponse(CommonException ce) {
        log.error(ce.getMessage());
        return new R(ce.getCode(), ce.getMessage(), ce.getRespStatusEnum());
    }
}
