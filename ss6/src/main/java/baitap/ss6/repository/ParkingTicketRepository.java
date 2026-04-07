package baitap.ss6.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import baitap.ss6.dto.TicketSummaryResponse;
import baitap.ss6.entity.ParkingTicket;
import java.time.LocalDateTime;
import java.util.List;

public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long> {
    @Query("""
    SELECT new baitap.ss6.dto.TicketSummaryResponse(
        t.id,
        v.licensePlate,
        z.name,
        t.checkInTime,
        t.checkOutTime
    )
    FROM ParkingTicket t
    JOIN t.vehicle v
    JOIN t.zone z
    WHERE t.checkInTime >= :startOfDay
      AND t.checkInTime < :endOfDay
    ORDER BY t.checkInTime DESC
""")
    List<TicketSummaryResponse> getTodayTickets(
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay
    );

    @Query("""
SELECT pt FROM ParkingTicket pt
JOIN pt.vehicle v
WHERE (:licensePlate IS NULL OR v.licensePlate = :licensePlate)
AND pt.checkInTime >= COALESCE(:fromDate, pt.checkInTime)
AND pt.checkInTime <= COALESCE(:toDate, pt.checkInTime)
""")
    Page<ParkingTicket> searchTickets(
            @Param("licensePlate") String licensePlate,
            @Param("fromDate") LocalDateTime fromDate,
            @Param("toDate") LocalDateTime toDate,
            Pageable pageable
    );

}

