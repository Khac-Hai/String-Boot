package baitap.ss10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import baitap.ss10.entity.Appointment;
import java.util.List;

public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorId(Long doctorId);
}
