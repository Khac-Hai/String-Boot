package re.edu.controller;

import re.edu.model.dto.request.OrderItemRequest;
import re.edu.model.entity.OrderStatus;
import re.edu.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor

public class OrderController {

    private final OrderService orderService;

    private String getUsername() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
    }

    // CUSTOMER đặt hàng
    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> create(@RequestBody List<OrderItemRequest> items) {
        return ResponseEntity.ok(orderService.createOrder(items, getUsername()));
    }

    // CUSTOMER xem đơn của mình
    @GetMapping("/my")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> myOrders() {
        return ResponseEntity.ok(orderService.getMyOrders(getUsername()));
    }

    // STAFF + ADMIN xem tất cả
    @GetMapping
    @PreAuthorize("hasAnyRole('STAFF','ADMIN')")
    public ResponseEntity<?> allOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    // STAFF update trạng thái
    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<?> updateStatus(@PathVariable Long id,
                                          @RequestParam OrderStatus status) {
        return ResponseEntity.ok(orderService.updateStatus(id, status));
    }
}