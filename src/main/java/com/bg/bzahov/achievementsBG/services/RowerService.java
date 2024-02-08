package com.bg.bzahov.achievementsBG.services;

import com.bg.bzahov.achievementsBG.exceptions.RowerNotFoundException;
import com.bg.bzahov.achievementsBG.exceptions.ValidationFailedException;
import com.bg.bzahov.achievementsBG.model.Rower;
import com.bg.bzahov.achievementsBG.repositories.RowerIDCardRepository;
import com.bg.bzahov.achievementsBG.repositories.RowerRepository;
import com.bg.bzahov.achievementsBG.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bg.bzahov.achievementsBG.constants.StringConstants.ALREADY_EXISTS;
import static com.bg.bzahov.achievementsBG.constants.StringConstants.A_ROWER_WITH_NAME;

@AllArgsConstructor
@Service
public class RowerService {

    private final RowerRepository rowerRepository;
    private final RowerIDCardRepository rowerIDCardRepository;

    public Rower addRower(Rower rower) {
        List<Rower> existingRower = rowerRepository.findAllByName(rower.getName());
        if (!existingRower.isEmpty()) {
            throw new ValidationFailedException(A_ROWER_WITH_NAME + rower.getName() + ALREADY_EXISTS);
        }
        return rowerRepository.save(rower);
    }

    public Rower getRowerById(Long id) {
        return rowerRepository.findById(id)
                .orElseThrow(() -> new RowerNotFoundException("RowerID: " + id));
    }

    public Rower updateRower(Long id, Rower rower) {
        Rower existingRower = getRowerById(id);

        if (Utils.validateString(rower.getName())) {
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
        if (yearOfBirth !=  null) {
            existingRower.setYearOfBirth(yearOfBirth);
        }

        return rowerRepository.save(existingRower);
    }

    public void deleteRower(Long id) {
        if (!rowerRepository.existsById(id)) {
            throw new RowerNotFoundException("RowerID: " + id);
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
}
