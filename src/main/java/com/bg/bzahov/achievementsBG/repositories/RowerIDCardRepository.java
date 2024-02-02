package com.bg.bzahov.achievementsBG.repositories;

import com.bg.bzahov.achievementsBG.model.RowerIDCard;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RowerIDCardRepository extends JpaRepository<RowerIDCard, Long> {
    @NotNull
    Optional<RowerIDCard> findById(@NotNull Long id);
    void deleteById(@NotNull Long id);
}