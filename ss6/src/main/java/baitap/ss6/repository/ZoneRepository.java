package baitap.ss6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import baitap.ss6.dto.ZoneStatisticsResponse;
import baitap.ss6.entity.Zone;

import java.util.List;

public interface ZoneRepository extends JpaRepository<Zone,Long> {
    @Query("""
    SELECT new baitap.ss6.dto.ZoneStatisticsResponse(
        z.id,
        z.name,
        z.capacity,
        z.occupiedSpots,
        (z.capacity - z.occupiedSpots)
    )
    FROM Zone z
""")
    List<ZoneStatisticsResponse> getZoneStatsV2();
}
