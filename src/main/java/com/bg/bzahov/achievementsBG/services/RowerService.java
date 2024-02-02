package com.bg.bzahov.achievementsBG.services;

import com.bg.bzahov.achievementsBG.exceptions.RowerNotFoundException;
import com.bg.bzahov.achievementsBG.model.Rower;
import com.bg.bzahov.achievementsBG.repositories.RowerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RowerService {

    @Autowired
    private final RowerRepository rowerRepository;

    public Rower addRower(Rower rower) {
        return rowerRepository.save(rower);
    }

    public Rower getRowerById(Long id) {
        return rowerRepository.findById(id)
                .orElseThrow(() -> new RowerNotFoundException(id.toString()));
    }

    public Rower updateRower(Long id, Rower rower) {
        Rower existingRower = getRowerById(id);
        existingRower.setName(rower.getName());
        existingRower.setAge(rower.getAge());
        existingRower.setGender(rower.getGender());
        return rowerRepository.save(existingRower);
    }

    public void deleteRower(Long id) {
        if(!rowerRepository.existsById(id)) {
            throw new RowerNotFoundException(
                    "Rower with id " + id + " does not exists");
        }
        Rower rower = getRowerById(id);
        rowerRepository.delete(rower);
    }

    public List<Rower> getAllRowers() {
        return rowerRepository.findAll();
    }
    public List<Rower> getAllRowersByYear(String yearOfBirth) {
        return rowerRepository.findAllByYearOfBirth(yearOfBirth);
    }
}
