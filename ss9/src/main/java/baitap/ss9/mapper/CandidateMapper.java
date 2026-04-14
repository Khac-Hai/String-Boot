package baitap.ss9.mapper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import baitap.ss9.dto.request.CandidateApplyDTO;
import baitap.ss9.dto.response.CandidateResponse;
import baitap.ss9.model.Candidate;

@Component
public class CandidateMapper {
    public static CandidateResponse mapToResponse(Candidate entity) {
        CandidateResponse response = new CandidateResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setEmail(entity.getEmail());
        response.setCvUrl(entity.getCvUrl());
        return response;
    }
    public static Candidate mapToEntity(CandidateApplyDTO dto, String cvUrl) {
        Candidate entity = new Candidate();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setCvUrl(cvUrl);
        return entity;
    }

}
