package baitap.ss7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import baitap.ss7.entity.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
}
