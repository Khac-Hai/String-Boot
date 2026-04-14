package baitap.ss8.mapper;

import org.springframework.stereotype.Component;
import baitap.ss8.dto.request.BorrowCreate;
import baitap.ss8.dto.response.BorrowResponse;
import baitap.ss8.model.BorrowStatus;
import baitap.ss8.model.BorrowTicket;

@Component
public class BorrowMapper {
    public BorrowTicket toEntity(BorrowCreate dto) {
        if (dto == null) return null;

        BorrowTicket ticket = new BorrowTicket();
        ticket.setUsername(dto.getUsername());
        ticket.setBookId(dto.getBookId());
        ticket.setStatus(BorrowStatus.BORROWING);

        return ticket;
    }

    public static BorrowResponse toResponse(BorrowTicket ticket) {
        return BorrowResponse.builder()
                .id(ticket.getId())
                .username(ticket.getUsername())
                .bookId(ticket.getBookId())
                .status(ticket.getStatus())
                .returnDate(ticket.getReturnDate())
                .build();
    }
}
