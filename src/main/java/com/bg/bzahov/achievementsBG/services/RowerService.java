package com.bg.bzahov.achievementsBG.services;

import com.bg.bzahov.achievementsBG.constants.ErrorConstants;
import com.bg.bzahov.achievementsBG.dto.auth.response.RowerResponseDto;
import com.bg.bzahov.achievementsBG.exceptions.RowerNotFoundException;
import com.bg.bzahov.achievementsBG.exceptions.ValidationFailedException;
import com.bg.bzahov.achievementsBG.model.Rower;
import com.bg.bzahov.achievementsBG.repositories.RowerIDCardRepository;
import com.bg.bzahov.achievementsBG.repositories.RowerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bg.bzahov.achievementsBG.constants.ErrorConstants.ERROR_ALREADY_EXISTS_EXTENSIONS;
import static com.bg.bzahov.achievementsBG.constants.StringConstants.ROWER_ID;
import static com.bg.bzahov.achievementsBG.constants.StringConstants.ROWER_WITH_NAME;
import static com.bg.bzahov.achievementsBG.utils.ModelUtils.getListResponseEntity;
import static com.bg.bzahov.achievementsBG.utils.ModelUtils.getRowerResponseDtoResponseEntity;
import static com.bg.bzahov.achievementsBG.utils.ServicesUtils.handleDeletion;
import static com.bg.bzahov.achievementsBG.utils.StringUtils.validateString;

@AllArgsConstructor
@Service
public class RowerService {

    private final RowerRepository rowerRepository;
    private final RowerIDCardRepository rowerIDCardRepository;

    public Rower addRower(Rower rower) {
        List<Rower> existingRower = rowerRepository.findAllByName(rower.getName());
        if (!existingRower.isEmpty()) {
            throw new ValidationFailedException(ROWER_WITH_NAME + rower.getName() + ERROR_ALREADY_EXISTS_EXTENSIONS);
        }
        return rowerRepository.save(rower);
    }

    public Rower getRowerById(Long id) {
        return rowerRepository.findById(id)
                .orElseThrow(() -> new RowerNotFoundException(ROWER_ID + id));
    }

    public Rower updateRower(Long id, Rower rower) {
        Rower existingRower = getRowerById(id);

        if (validateString(rower.getName())) {
            existingRower.setName(rower.getName());
        }

        Integer age = rower.getAge();
        if (age != null) {
            existingRower.setAge(age);
        }

        if (rower.getGender() != null) {
            existingRower.setGender(rower.getGender());
        }

        Integer yearOfBirth = rower.getYearOfBirth();
        if (yearOfBirth != null) {
            existingRower.setYearOfBirth(yearOfBirth);
        }

        return rowerRepository.save(existingRower);
    }

    public void deleteRower(Long id) {
        if (!rowerRepository.existsById(id)) {
            throw new RowerNotFoundException(ROWER_ID + id);
        }
        Rower rower = getRowerById(id);

        rower.getRowerIDCards().forEach(rowerIDCard -> {
            rowerIDCard.setRower(null);
            rowerIDCardRepository.save(rowerIDCard);
        });

        rowerRepository.delete(rower);
    }

    public List<Rower> getAllRowers() {
        return rowerRepository.findAll();
    }

    public List<Rower> getAllRowersByYear(String yearOfBirth) {
        return rowerRepository.findAllByYearOfBirth(yearOfBirth);
    }

    // Wrappers

    public ResponseEntity<RowerResponseDto> addRowerAndReturnResponse(Rower rower) {
        return getRowerResponseDtoResponseEntity(addRower(rower));
    }

    public ResponseEntity<RowerResponseDto> getRowerByIdAndReturnResponse(Long id) {
        return getRowerResponseDtoResponseEntity(getRowerById(id));
    }

    public ResponseEntity<RowerResponseDto> updateRowerAndReturnResponse(Long id, Rower rower) {
        return getRowerResponseDtoResponseEntity(updateRower(id, rower));
    }

    public ResponseEntity<List<RowerResponseDto>> getAllRowersAndReturnResponse() {
        return getListResponseEntity(getAllRowers());
    }

    public ResponseEntity<List<RowerResponseDto>> getAllRowersByBirthYearAndReturnResponse(String yearOfBirth) {
        return getListResponseEntity(getAllRowersByYear(yearOfBirth));
    }

    public ResponseEntity<String> deleteRowerByIdAndReturnResponse(Long id) {
        return handleDeletion(
                () -> deleteRower(id),
                id.toString(),
                ErrorConstants.ERROR_ROWER_WITH_IDENTIFIER_ID);
    }

}
