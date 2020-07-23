package ch.peax.demo;

import ch.peax.demo.exception.NotFoundException;
import ch.peax.demo.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/test")
    public Greeting test() {
        throw new NullPointerException("This was null");
    }

    @GetMapping("/greeting/{name}")
    public Greeting greeting2(@Valid @Size(min = 2) @PathVariable String name) {
        if (name.equalsIgnoreCase("kevin")) {
            return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE, name));
        }
        throw new NotFoundException("Name not found", name);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        return ResponseEntity.accepted().build();
    }
}
