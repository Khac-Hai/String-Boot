package baitap.ss6.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import baitap.ss6.entity.*;
import baitap.ss6.dto.*;
import baitap.ss6.repository.*;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public PageResponse<VehicleResponse> getPagedVehicles(
            int page, int size,
            String sortBy,
            String direction,
            String keyword) {
        if (page < 0) page = 0;

        Sort sort = Sort.unsorted();
        if (sortBy != null && direction != null) {
            sort = direction.equalsIgnoreCase("DESC")
                    ? Sort.by(sortBy).descending()
                    : Sort.by(sortBy).ascending();
        }

        if (keyword != null && !keyword.isBlank()) {
            keyword = "%" + keyword.toLowerCase() + "%";
        } else {
            keyword = null;
        }
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<VehicleResponse> pageData =
                vehicleRepository.findAllByKeyword(keyword, pageable);
        return new PageResponse<>(
                pageData.getContent(),
                pageData.getNumber(),
                pageData.getSize(),
                pageData.getTotalElements(),
                pageData.getTotalPages(),
                pageData.isLast()
        );
    }
    public void createVehicle(VehicleCreateRequest request) {
        Vehicle v = new Vehicle();
        v.setLicensePlate(request.getLicensePlate());
        v.setColor(request.getColor());
        v.setType(request.getVehicleType());

        vehicleRepository.save(v);
    }


}