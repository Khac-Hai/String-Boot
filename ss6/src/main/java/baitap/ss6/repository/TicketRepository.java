package baitap.ss6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import baitap.ss6.entity.ParkingTicket;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<ParkingTicket,Long> {
    @Query("""
        SELECT t FROM ParkingTicket t
        WHERE t.vehicle.id = :vehicleId
        AND t.checkOutTime IS NULL
        ORDER BY t.checkInTime DESC
        LIMIT 1
    """)
    Optional<ParkingTicket> findActiveTicket(@Param("vehicleId") Long vehicleId);
}
