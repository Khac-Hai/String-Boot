package baitap.ss8.mapper;

import org.springframework.stereotype.Component;
import baitap.ss8.dto.request.BookCreateDTO;
import baitap.ss8.dto.response.BookResponse;
import baitap.ss8.model.Book;

@Component
public class BookMapper {
    public BookResponse mapToResponse(Book entity){
        BookResponse response = new BookResponse();
        response.setId(entity.getId());
        response.setTitle(entity.getTitle());
        response.setAuthor(entity.getAuthor());
        response.setStock(entity.getStock());
        response.setCoverUrl("/uploads/" + entity.getCoverUrl());
        return response;
    }
    public Book mapToEntity(BookCreateDTO request, String filename){
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setStock(request.getStock());
        book.setCoverUrl(filename);
        return book;
    }
}
