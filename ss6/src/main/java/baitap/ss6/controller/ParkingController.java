package baitap.ss6.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import baitap.ss6.dto.TicketRequest;
import baitap.ss6.dto.TicketResponse;
import baitap.ss6.entity.ParkingTicket;
import baitap.ss6.service.ParkingService;

@RestController
@RequestMapping("/api/v1/tickets")
public class ParkingController {
    private final ParkingService parkingService;
    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    //Check-in
    @PostMapping("/check-in")
    public ResponseEntity<TicketResponse> checkIn(@RequestBody TicketRequest req){
        return ResponseEntity.ok(parkingService.checkIn(req));
    }

    //Check-out
    @PutMapping("/check-out/{vehicleId}")
    public ResponseEntity<TicketResponse> checkOut(@PathVariable Long vehicleId){
        return ResponseEntity.ok(parkingService.checkOut(vehicleId));
    }
}
