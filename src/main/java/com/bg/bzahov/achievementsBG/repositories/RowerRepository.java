package com.bg.bzahov.achievementsBG.repositories;

import com.bg.bzahov.achievementsBG.model.Rower;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RowerRepository extends JpaRepository<Rower, Long> {
    @NotNull
    Optional<Rower> findById(@NotNull Long id);
    Optional<Rower> findByName(String name);
    void deleteById(Long id);

    List<Rower> findAllByYearOfBirth(Integer yearOfBirth);
    List<Rower> findAllByName(String yearOfBirth);

}