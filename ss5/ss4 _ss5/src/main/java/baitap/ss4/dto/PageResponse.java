package baitap.ss4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PageResponse<T> {
    private List<T> items;
    private int page;
    private int size;
    private Long totalItems;
    private int totalPages;
    private boolean isLast;

}
