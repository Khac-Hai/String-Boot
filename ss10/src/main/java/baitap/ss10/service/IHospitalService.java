package baitap.ss10.service;

import baitap.ss10.dto.request.PrescriptionRequest;
import baitap.ss10.dto.response.AppointmentResponse;
import baitap.ss10.dto.response.PrescriptionResponse;
import java.util.List;

public interface IHospitalService {
    List<AppointmentResponse> getAppointmentsByDoctor(Long doctorId);
    PrescriptionResponse getPrescription(Long patientId, Long prescriptionId);
    PrescriptionResponse createPrescription(Long patientId, PrescriptionRequest request);
}