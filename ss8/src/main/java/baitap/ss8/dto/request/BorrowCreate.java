package baitap.ss8.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import baitap.ss8.validation.ExistingBookId;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowCreate {
    @NotBlank(message = "username không được bỏ trống")
    private String username;
    @ExistingBookId
    private Long bookId;
}