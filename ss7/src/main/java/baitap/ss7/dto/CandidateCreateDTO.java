package baitap.ss7.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidateCreateDTO{
    @NotBlank(message = "Full name không được để trống")
    @Size(min= 5,max = 50 , message = "Full name phải có độ dài từ 5 đến 50 ký tự")
    private String fullName;
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;
    @NotNull(message = "Age không được để trống")
    @Min(value = 18, message = "Age phải lớn hơn hoặc bằng 18")
    private Integer age;
    @NotNull(message = "Year of experience không được để trống")
    @Min(value = 0, message = "Year of experience phải lớn hơn hoặc bằng 0")
    private Integer yearOfExperience;
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(
            regexp = "^0[35789][0-9]{8}$",
            message = "Số điện thoại không hợp lệ. Phải bắt đầu bằng 0"
    )
    private String phone;
}
