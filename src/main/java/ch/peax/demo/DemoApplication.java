package ch.peax.demo;

import io.sentry.spring.SentryExceptionResolver;
import io.sentry.spring.SentryServletContextInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerExceptionResolver;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  public HandlerExceptionResolver sentryExceptionResolver() {
    return new SentryExceptionResolver();
  }

  @Bean
  public ServletContextInitializer sentryServletContextInitializer() {
    return new SentryServletContextInitializer();
  }
}
