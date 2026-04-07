package baitap.ss6.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import baitap.ss6.dto.*;
import baitap.ss6.entity.*;
import baitap.ss6.repository.*;

import java.time.LocalDateTime;

@Service
@Transactional
public class ParkingService {
    private TicketRepository ticketRepo;
    private VehicleRepository vehicleRepo;
    private ZoneRepository zoneRepo;

    public ParkingService (TicketRepository ticketRepo, VehicleRepository vehicleRepo, ZoneRepository zoneRepo) {
        this.ticketRepo = ticketRepo;
        this.vehicleRepo = vehicleRepo;
        this.zoneRepo = zoneRepo;
    }

    //CHECK-IN
    public TicketResponse checkIn(TicketRequest req) {
        Vehicle vehicle = vehicleRepo.findById(req.getVehicleId()).orElseThrow(() -> new RuntimeException("vehicle id not found"));

        Zone zone = zoneRepo.findById(req.getZoneId())
                .orElseThrow(() -> new RuntimeException("Zone not found"));

        // check chỗ trống
        if (zone.getOccupiedSpots() >= zone.getCapacity()) {
            throw new RuntimeException("Zone is full");
        }
        // tạo ticket
        ParkingTicket ticket = new ParkingTicket();
        ticket.setVehicle(vehicle);
        ticket.setZone(zone);
        ticket.setCheckInTime(LocalDateTime.now());

        // update zone
        zone.setOccupiedSpots(zone.getOccupiedSpots() + 1);

        ticketRepo.save(ticket);

        return new TicketResponse(
                ticket.getId(),
                vehicle.getLicensePlate(),
                zone.getName(),
                ticket.getCheckInTime(),
                null
        );
    }

    //CHECK-OUT
    public TicketResponse checkOut(Long vehicleId) {
        ParkingTicket ticket = ticketRepo
                .findActiveTicket(vehicleId)
                .orElseThrow(() -> new RuntimeException("No active ticket"));

        ticket.setCheckOutTime(LocalDateTime.now());

        Zone zone = ticket.getZone();
        zone.setOccupiedSpots(zone.getOccupiedSpots() - 1);

        return new TicketResponse(
                ticket.getId(),
                ticket.getVehicle().getLicensePlate(),
                zone.getName(),
                ticket.getCheckInTime(),
                ticket.getCheckOutTime()
        );
    }

}