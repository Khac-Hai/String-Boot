package baitap.ss8.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import baitap.ss8.dto.request.BookCreateDTO;
import baitap.ss8.dto.request.BookUpdateStock;
import baitap.ss8.dto.response.BookResponse;
import baitap.ss8.exception.ResourceNotFoundException;
import baitap.ss8.model.Book;
import baitap.ss8.mapper.BookMapper;
import baitap.ss8.repositories.BookRepository;
import baitap.ss8.service.IBookService;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

    @Override
    public BookResponse createBook(BookCreateDTO request) {
        try {
            MultipartFile file = request.getCoverImage();

            if (file == null || file.isEmpty()) {
                throw new RuntimeException("File is empty");
            }

            File dir = new File(UPLOAD_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            File destination = new File(UPLOAD_DIR + fileName);
            file.transferTo(destination);

            Book book = bookMapper.mapToEntity(request, fileName);
            Book saved = bookRepository.save(book);

            return bookMapper.mapToResponse(saved);

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
    public BookResponse updateBook(Long id, BookUpdateStock dto) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        book.setStock(dto.getStock());

        Book updated = bookRepository.save(book);

        return bookMapper.mapToResponse(updated);
    }
}