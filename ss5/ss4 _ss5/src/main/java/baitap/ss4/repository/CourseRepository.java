package baitap.ss4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import baitap.ss4.entity.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import baitap.ss4.dto.CourseResponseV2;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM  Course c WHERE c.status= :status")
    Page<Course> findAllByStatus(@Param("status")CourseStatus status, Pageable pageable);

    @Query("""
SELECT new baitap.ss4.dto.CourseResponseV2(
c.id,
c.title,
c.status)
FROM Course c WHERE c.status = :status
""")
    Page<CourseResponseV2> findAllByStatusV2(
            @Param("status") CourseStatus status,
            Pageable pageable
    );

}
