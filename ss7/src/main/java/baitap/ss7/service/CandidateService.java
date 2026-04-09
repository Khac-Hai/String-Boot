package baitap.ss7.service;

import baitap.ss7.dto.CandidateCreateDTO;
import baitap.ss7.dto.CandidateUpdateDTO;
import baitap.ss7.entity.Candidate;

public interface CandidateService {
    Candidate createCandidate(CandidateCreateDTO dto);
    Candidate updateCandidate(Integer id, CandidateUpdateDTO dto);
}
