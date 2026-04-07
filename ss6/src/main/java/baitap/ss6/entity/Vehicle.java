package baitap.ss6.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "license_plate")
    private String licensePlate;
    @Column(name = "color")
    private String color;
    @Enumerated(EnumType.STRING)
    @Column(name ="type")
    private VehicleType type;
    @OneToMany(mappedBy = "vehicle")
    private List<ParkingTicket> parkingTickets;
}
