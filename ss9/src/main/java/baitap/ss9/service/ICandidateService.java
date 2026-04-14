package baitap.ss9.service;

import baitap.ss9.dto.request.CandidateApplyDTO;
import baitap.ss9.dto.response.CandidateResponse;

public interface ICandidateService {
    CandidateResponse apply(CandidateApplyDTO dto) ;
}
