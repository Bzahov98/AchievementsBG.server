package com.bg.bzahov.achievementsBG.repositories;

import com.bg.bzahov.achievementsBG.model.RowerIDCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RowerIDCardRepository extends JpaRepository<RowerIDCard, Long> {
}