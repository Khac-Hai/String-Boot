package baitap.ss8.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import baitap.ss8.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}