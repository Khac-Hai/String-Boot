package baitap.ss10.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import baitap.ss10.dto.request.DoctorRequest;
import baitap.ss10.dto.response.DoctorResponse;
import baitap.ss10.entity.Doctor;
import baitap.ss10.exception.ResourceNotFoundException;
import baitap.ss10.repository.IDoctorRepository;
import baitap.ss10.service.IDoctorService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements IDoctorService {
    private final IDoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<DoctorResponse> getAllDoctors(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Doctor> doctorPage = doctorRepository.findAll(pageable);
        return doctorPage.map(doctor ->
                modelMapper.map(doctor, DoctorResponse.class)
        );
    }

    @Override
    public DoctorResponse getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor Not Found"));
        return modelMapper.map(doctor,DoctorResponse.class);
    }

    @Override
    public DoctorResponse createDoctor(DoctorRequest request) {
        if(doctorRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email Already Exists");
        }
        Doctor doctor = modelMapper.map(request,Doctor.class);
        doctorRepository.save(doctor);
        return modelMapper.map(doctor,DoctorResponse.class);
    }

    @Override
    public DoctorResponse updateDoctor(Long id, DoctorRequest request) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor Not Found"));
        doctor.setName(request.getName());
        doctor.setSpecialization(request.getSpecialization());
        doctor.setEmail(request.getEmail());

        Doctor updatedDoctor = doctorRepository.save(doctor);
        return modelMapper.map(updatedDoctor,DoctorResponse.class);
    }

    @Override
    public void deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor Not Found"));
        doctorRepository.delete(doctor);

    }
}
