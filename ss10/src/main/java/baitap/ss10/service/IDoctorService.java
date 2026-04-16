package baitap.ss10.service;

import org.springframework.data.domain.Page;
import baitap.ss10.dto.request.DoctorRequest;
import baitap.ss10.dto.response.DoctorResponse;

public interface IDoctorService {
    Page<DoctorResponse> getAllDoctors(int page, int size);
    DoctorResponse getDoctorById(Long id);
    DoctorResponse createDoctor(DoctorRequest request);
    DoctorResponse updateDoctor(Long id, DoctorRequest request);
    void deleteDoctor(Long id);
}