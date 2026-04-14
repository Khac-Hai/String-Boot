package baitap.ss9.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import baitap.ss9.dto.request.CandidateApplyDTO;
import baitap.ss9.dto.response.CandidateResponse;
import baitap.ss9.exception.DuplicateResourceException;
import baitap.ss9.exception.InvalidFileException;
import baitap.ss9.mapper.CandidateMapper;
import baitap.ss9.model.Candidate;
import baitap.ss9.repositories.ICandidateRepository;
import baitap.ss9.service.ICandidateService;
import baitap.ss9.validation.PdfValidator;

import java.io.IOException;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class CandidateServiceImpl implements ICandidateService {
    private final ICandidateRepository repository;
    private final CandidateMapper mapper;
    private final Cloudinary cloudinary;

    @Override
    public CandidateResponse apply(CandidateApplyDTO dto) {
        if (repository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("Email đã tồn tại");
        }

        MultipartFile file = dto.getCvFile();
        PdfValidator.validate(file);

        try {
            Map<?, ?> uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap(
                            "resource_type", "raw",
                            "folder", "cv"
                    )
            );

            String cvUrl = uploadResult.get("secure_url").toString();
            Candidate candidate = mapper.mapToEntity(dto, cvUrl);
            candidate.setCvUrl(cvUrl);
            Candidate saved = repository.save(candidate);
            return mapper.mapToResponse(saved);

        } catch (IOException e) {
            throw new InvalidFileException("Upload CV thất bại");
        }
    }
}
