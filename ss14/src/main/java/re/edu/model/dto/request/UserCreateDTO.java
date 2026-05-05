package re.edu.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateDTO {
    @NotBlank(message = "Không được để trống")
    @Size(min = 5, max = 50, message = "Phải từ 5 đến 50 ký tự")
    private String username;
    @NotBlank(message = "Không được để trống")
    private String password;
}
