package baitap.ss8.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import baitap.ss8.dto.request.BorrowCreate;
import baitap.ss8.dto.response.BorrowResponse;
import baitap.ss8.model.*;
import baitap.ss8.exception.BookAlreadyReturnedException;
import baitap.ss8.exception.ResourceNotFoundException;
import baitap.ss8.mapper.BorrowMapper;
import baitap.ss8.repositories.*;
import baitap.ss8.service.IBorrowService;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BorrowService implements IBorrowService {

    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    private final BorrowMapper borrowMapper;

    @Override
    public BorrowResponse createBorrow(BorrowCreate dto) {

        BorrowTicket ticket = borrowMapper.toEntity(dto);

        BorrowTicket saved = borrowRepository.save(ticket);

        return borrowMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public BorrowResponse returnBook(Long ticketId) {

        BorrowTicket ticket = borrowRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Phiếu mượn không tồn tại"));

        if (ticket.getStatus() == BorrowStatus.RETURNED) {
            throw new BookAlreadyReturnedException("Sách này đã được trả rồi");
        }

        ticket.setStatus(BorrowStatus.RETURNED);
        ticket.setReturnDate(LocalDate.now());

        Book book = bookRepository.findById(ticket.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Sách không tồn tại"));

        book.setStock(book.getStock() + 1);

        return BorrowMapper.toResponse(ticket);
    }
}
