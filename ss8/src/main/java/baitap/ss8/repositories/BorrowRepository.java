package baitap.ss8.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import baitap.ss8.model.BorrowTicket;

public interface BorrowRepository extends JpaRepository<BorrowTicket, Long> {
}
