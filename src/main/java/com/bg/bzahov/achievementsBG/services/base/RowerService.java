package com.bg.bzahov.achievementsBG.services.base;

import com.bg.bzahov.achievementsBG.dto.auth.response.RowerResponseDto;
import com.bg.bzahov.achievementsBG.model.Rower;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RowerService {
    ResponseEntity<RowerResponseDto> addRowerAndReturnResponse(Rower rower);

    ResponseEntity<RowerResponseDto> getRowerByIdAndReturnResponse(Long id);

    ResponseEntity<RowerResponseDto> updateRowerAndReturnResponse(Long id, Rower rower);

    ResponseEntity<List<RowerResponseDto>> getAllRowersAndReturnResponse();

    ResponseEntity<List<RowerResponseDto>> getAllRowersByBirthYearAndReturnResponse(String yearOfBirth);
}
