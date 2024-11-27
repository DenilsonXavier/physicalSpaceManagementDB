package com.ucsal.physicalSpaceManagement.user.dto;

import com.ucsal.physicalSpaceManagement.user.Enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {

    @NotBlank(message = "O nome é obrigatório", groups = { Signup.class })
    private String name;

    @NotBlank(message = "O email é obrigatório", groups = { Signup.class, Signin.class })
    @Email(message = "Email inválido", groups = { Signup.class, Signin.class })
    private String email;

    @NotBlank(message = "A senha é obrigatória", groups = { Signup.class, Signin.class })
    private String password;

    private UserType userType;

    public interface Signup {
    }

    public interface Signin {
    }
}
