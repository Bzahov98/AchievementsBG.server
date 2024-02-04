package com.bg.bzahov.achievementsBG.controlers.utils;

import com.bg.bzahov.achievementsBG.dto.auth.response.RowerResponseDto;
import com.bg.bzahov.achievementsBG.exceptions.RowerIDCardNotFoundException;
import com.bg.bzahov.achievementsBG.exceptions.RowerNotFoundException;
import com.bg.bzahov.achievementsBG.model.Rower;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

// Support methods for rest controllers
public class ControllersUtils {

    public static ResponseEntity<String> handleDeletion(Runnable deletionLogic, String identifier, String identifierStr) {
        try {
            deletionLogic.run();
            return ResponseEntity.ok(identifierStr + identifier + " was DELETED successfully!");
        } catch (RowerIDCardNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(identifierStr + identifier + " DOES NOT EXIST!!");
        }
    }

    public static <Entity, Dto> List<Dto> mapAndConvertToDto(List<Entity> entities, Function<Entity, Dto> converterFunc) {
        return entities.stream()
                .map(converterFunc)
                .collect(Collectors.toList());
    }
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
