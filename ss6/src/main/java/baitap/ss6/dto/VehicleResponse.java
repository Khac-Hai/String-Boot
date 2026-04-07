package baitap.ss6.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import baitap.ss6.entity.VehicleType;

@Getter
@Setter
@Builder

public class VehicleResponse {
    private Long id;
    private String licensePlate;
    private String color;
    private VehicleType vehicleType;

}