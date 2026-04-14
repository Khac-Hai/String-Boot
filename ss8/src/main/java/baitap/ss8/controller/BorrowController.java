package baitap.ss8.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import baitap.ss8.dto.request.BorrowCreate;
import baitap.ss8.dto.response.BorrowResponse;
import baitap.ss8.model.*;
import baitap.ss8.repositories.BorrowRepository;
import baitap.ss8.service.IBorrowService;

@RestController
@RequestMapping("/api/borrows")
@RequiredArgsConstructor
public class BorrowController {

    private final IBorrowService borrowService;
    private final BorrowRepository borrowRepository;

    @PostMapping
    public ResponseEntity<?> createBorrow(@Valid @RequestBody BorrowCreate request) {

        BorrowTicket ticket = BorrowTicket.builder()
                .username(request.getUsername())
                .bookId(request.getBookId())
                .status(BorrowStatus.BORROWING)
                .build();

        borrowRepository.save(ticket);

        return ResponseEntity.ok("Tạo phiếu mượn thành công");
    }

    @PatchMapping("/{id}/return")
    public ResponseEntity<BorrowResponse> returnBook(@PathVariable Long id) {
        return ResponseEntity.ok(borrowService.returnBook(id));
    }
}
