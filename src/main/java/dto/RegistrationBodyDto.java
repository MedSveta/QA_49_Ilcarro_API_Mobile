package dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class RegistrationBodyDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
}
