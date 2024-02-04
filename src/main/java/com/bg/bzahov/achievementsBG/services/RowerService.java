package com.bg.bzahov.achievementsBG.services;

import com.bg.bzahov.achievementsBG.Utils;
import com.bg.bzahov.achievementsBG.exceptions.RowerNotFoundException;
import com.bg.bzahov.achievementsBG.exceptions.ValidationFailedException;
import com.bg.bzahov.achievementsBG.model.Rower;
import com.bg.bzahov.achievementsBG.repositories.RowerIDCardRepository;
import com.bg.bzahov.achievementsBG.repositories.RowerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@AllArgsConstructor
@Service
public class RowerService {

    private final RowerRepository rowerRepository;
    private final RowerIDCardRepository rowerIDCardRepository;

    public Rower addRower(Rower rower) {
        List<Rower> existingRower = rowerRepository.findAllByName(rower.getName());
        if (existingRower.size() > 0) {
            throw new ValidationFailedException("A Rower with name:  " + rower.getName() + " already exists.");
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

        int age = rower.getAge();
        if (age >= 1 && age <= 100) {
            existingRower.setAge(age);
        }

        if (rower.getGender() != null) {
            existingRower.setGender(rower.getGender());
        }

        int yearOfBirth = rower.getYearOfBirth();
        if (yearOfBirth >= 1900 && yearOfBirth <= Calendar.getInstance().get(Calendar.YEAR) - 1) {
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
