package org.example.birthdaybook.data.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {

    //@Min(value = 1, message = "Name should consist of min one char")
    @NotBlank(message = "Name should not be blank")
    private String name;

    //@Min(value = 1, message = "Surname should consist of min one char")
    @NotBlank(message = "Surname should not be blank")
    private String surname;

    @NotNull
    private String dateOfBirth;

}
