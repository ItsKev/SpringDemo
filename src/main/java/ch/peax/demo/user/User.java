package ch.peax.demo.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class User {

    @NotNull
    private String name;

    @Min(value = 18)
    private int age;

}
