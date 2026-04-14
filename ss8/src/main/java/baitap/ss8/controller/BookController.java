package baitap.ss8.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import baitap.ss8.dto.request.BookCreateDTO;
import baitap.ss8.dto.request.BookUpdateStock;
import baitap.ss8.dto.response.BookResponse;
import baitap.ss8.service.IBookService;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final IBookService bookService;

    @PostMapping
    public ResponseEntity<?> createBook(
            @Valid @ModelAttribute BookCreateDTO request
    ) {
        try {
            BookResponse response = bookService.createBook(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStock(
            @PathVariable Long id,
            @Valid @RequestBody BookUpdateStock dto
    ) {
        BookResponse response = bookService.updateBook(id, dto);
        return ResponseEntity.ok(response);
    }
}
