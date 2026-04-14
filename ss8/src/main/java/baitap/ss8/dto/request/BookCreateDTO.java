package baitap.ss8.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookCreateDTO {
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @NotNull
    @Min(0)
    private Integer stock;
    private MultipartFile coverImage;
}
