package com.bg.bzahov.achievementsBG.utils;

import com.bg.bzahov.achievementsBG.exceptions.RowerIDCardNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.bg.bzahov.achievementsBG.constants.ErrorConstants.ERROR_DOES_NOT_EXIST;
import static com.bg.bzahov.achievementsBG.constants.StringConstants.DELETED_SUCCESSFULLY;

// Support methods for rest controllers
public class ControllersUtils {

    public static ResponseEntity<String> handleDeletion(Runnable deletionLogic, String identifier, String identifierStr) {
        try {
            deletionLogic.run();
            return ResponseEntity.ok(identifierStr + identifier + DELETED_SUCCESSFULLY);
        } catch (RowerIDCardNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(identifierStr + identifier + ERROR_DOES_NOT_EXIST);
        }
    }

    public static <Entity, Dto> List<Dto> mapAndConvertEntityToDto(List<Entity> entities, Function<Entity, Dto> converterFunc) {
        return entities.stream()
                .map(converterFunc)
                .collect(Collectors.toList());
    }
}
