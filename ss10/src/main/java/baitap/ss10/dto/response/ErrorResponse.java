package baitap.ss10.dto.response;

import lombok.*;

import java.time.LocalDateTime;
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
