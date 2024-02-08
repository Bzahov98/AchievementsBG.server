package com.bg.bzahov.achievementsBG.utils;

import com.bg.bzahov.achievementsBG.dto.auth.response.RowerResponseDto;
import com.bg.bzahov.achievementsBG.exceptions.RowerNotFoundException;
import com.bg.bzahov.achievementsBG.model.Rower;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ModelUtils {
    // Rower Utils
    @NotNull
    public static ResponseEntity<RowerResponseDto> getRowerResponseDtoResponseEntity(Rower rower1) {
        RowerResponseDto retrievedRower = RowerResponseDto.fromRower(rower1);
        if (retrievedRower == null) {
            throw new RowerNotFoundException("RowerID: " + rower1.getId());
        }
        return new ResponseEntity<>(retrievedRower, HttpStatus.OK);
    }


    @NotNull
    public static ResponseEntity<List<RowerResponseDto>> getListResponseEntity(List<Rower> allRowers) {
        List<RowerResponseDto> rowerResponseDtoList = allRowers.stream()
                .map(RowerResponseDto::fromRower)
                .toList();
        return new ResponseEntity<>(rowerResponseDtoList, HttpStatus.OK);
    }
}
