package baitap.ss9.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import baitap.ss9.model.Candidate;

public interface ICandidateRepository extends JpaRepository<Candidate, Long> {
    boolean existsByEmail(String email);
}
