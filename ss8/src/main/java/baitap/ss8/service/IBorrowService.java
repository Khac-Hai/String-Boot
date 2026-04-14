package baitap.ss8.service;

import baitap.ss8.dto.request.BorrowCreate;
import baitap.ss8.dto.response.BorrowResponse;

public interface IBorrowService {
    BorrowResponse createBorrow(BorrowCreate dto);
    BorrowResponse returnBook(Long ticketId);
}
