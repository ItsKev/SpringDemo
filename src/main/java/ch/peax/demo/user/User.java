package ch.peax.demo.user;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class User {

  @NotNull
  private String name;

  @Min(value = 18)
  private int age;

}
