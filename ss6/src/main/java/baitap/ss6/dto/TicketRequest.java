package baitap.ss6.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketRequest {
    private Long vehicleId;
    private Long zoneId;
}
