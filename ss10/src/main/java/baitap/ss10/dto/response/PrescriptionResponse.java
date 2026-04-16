package baitap.ss10.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrescriptionResponse {
    private Long id;
    private String medication;
    private String dosage;
}