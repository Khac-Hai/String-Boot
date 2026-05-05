package re.edu.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO {
    @NotBlank(message = "Không được để trống")

    private String username;
    @NotBlank(message = "Không được để trống")
    private String password;
}
