package baitap.ss6.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ZoneStatisticsResponse {
    private Long id;
    private String name;
    private int capacity;
    private int occupiedSlots;
    private int availableSlots;
}
