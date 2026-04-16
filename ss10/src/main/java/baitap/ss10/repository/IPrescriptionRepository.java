package baitap.ss10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import baitap.ss10.entity.Prescription;

import java.util.Optional;

public interface IPrescriptionRepository extends JpaRepository<Prescription, Long> {
    Optional<Prescription> findByIdAndPatientId(Long id, Long patientId);
}
