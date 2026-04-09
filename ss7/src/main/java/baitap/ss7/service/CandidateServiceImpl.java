package baitap.ss7.service;

import org.springframework.stereotype.Service;
import baitap.ss7.dto.CandidateCreateDTO;
import baitap.ss7.dto.CandidateUpdateDTO;
import baitap.ss7.entity.Candidate;
import baitap.ss7.repository.CandidateRepository;

@Service
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository repository;
    public CandidateServiceImpl(CandidateRepository repository) {
        this.repository = repository;
    }

    @Override
    public Candidate createCandidate(CandidateCreateDTO dto) {
        Candidate candidate = new Candidate();
        candidate.setFullName(dto.getFullName());
        candidate.setEmail(dto.getEmail());
        candidate.setAge(dto.getAge());
        candidate.setYearOfExperience(dto.getYearOfExperience());
        candidate.setPhone(dto.getPhone());
        return repository.save(candidate);
    }

    @Override
    public Candidate updateCandidate(Integer id, CandidateUpdateDTO dto) {
        Candidate candidate = repository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy ứng viên với id: " + id));
        candidate.setAddress(dto.getAddress());
        candidate.setBio(dto.getBio());
        return repository.save(candidate);
    }


}