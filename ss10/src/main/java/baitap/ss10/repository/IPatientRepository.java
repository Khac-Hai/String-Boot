package baitap.ss10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import baitap.ss10.entity.Patient;

public interface IPatientRepository extends JpaRepository<Patient, Long> {}
