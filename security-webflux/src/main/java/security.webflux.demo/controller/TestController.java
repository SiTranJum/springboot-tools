package security.webflux.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/test")
    public Mono<String> test() {
        System.out.println("13");
        return Mono.just("1swad");
    }
}
