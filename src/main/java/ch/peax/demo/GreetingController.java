package ch.peax.demo;

import ch.peax.demo.exception.NotFoundException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class GreetingController {

  private static final String TEMPLATE = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @GetMapping("/test")
  @Secured(value = {"ROLE_USER", "ROLE_ADMIN"})
  public Greeting test() {
    throw new NullPointerException("This was null");
  }

  @GetMapping("/greeting/{name}")
  @Secured("ROLE_ADMIN")
  public Greeting greeting2(@Valid @Size(min = 2) @PathVariable String name) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    log.info(Arrays.toString(authentication.getAuthorities().toArray()));
    if (name.equalsIgnoreCase("kevin")) {
      return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE, name));
    }
    throw new NotFoundException("Name not found", name);
  }
}
