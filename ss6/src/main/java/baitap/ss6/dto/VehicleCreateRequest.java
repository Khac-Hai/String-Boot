package baitap.ss6.dto;
import lombok.Getter;
import lombok.Setter;
import baitap.ss6.entity.VehicleType;

@Getter
@Setter
public class VehicleCreateRequest {
    private String licensePlate;
    private String color;
    private VehicleType vehicleType;
}