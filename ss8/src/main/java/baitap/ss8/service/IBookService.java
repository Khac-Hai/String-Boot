package baitap.ss8.service;

import baitap.ss8.dto.request.BookCreateDTO;
import baitap.ss8.dto.request.BookUpdateStock;
import baitap.ss8.dto.response.BookResponse;
public interface IBookService {
    BookResponse createBook (BookCreateDTO request);
    BookResponse updateBook(Long id, BookUpdateStock dto);
}
