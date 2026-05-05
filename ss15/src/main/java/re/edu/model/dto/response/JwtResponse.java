package re.edu.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Getter
@Setter
public class JwtResponse {
    private String accessToken;
    private String refreshToken;
    private final String type = "Bearer";
    private String username;
    private final LocalDateTime timestamp = LocalDateTime.now();
    private Date expirationDate;
}
