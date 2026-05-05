package re.edu.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class EmployeeCreateDTO {
    @NotBlank(message = "Tên không được để trống")
    private String fullName;
    @NotNull(message = "Lương không được để trống")
    private Double salary;
    private MultipartFile avatar;
}
