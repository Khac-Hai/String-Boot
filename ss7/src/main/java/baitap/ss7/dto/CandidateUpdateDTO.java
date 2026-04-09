package baitap.ss7.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidateUpdateDTO {
    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;
    @NotNull
    @Size(max = 200, message = "Bio phải có độ dài tối đa 200 ký tự")
    private String bio;
}
