package springboottools.controller;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboottools.domain.User;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/user")
    public User getUser() {
        User user = User.builder()
                .id(1L)
                .city(1)
                .phone("13433273327")
                .build();
        Map<String,Object> immutableMap = new ImmutableMap.Builder<String,Object>().build();

        return user;
    }
}
