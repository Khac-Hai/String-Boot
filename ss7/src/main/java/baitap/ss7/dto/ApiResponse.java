package baitap.ss7.dto;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data

public class ApiResponse<T> {
    private String Status;
    private String Message;
    private T data;

}
