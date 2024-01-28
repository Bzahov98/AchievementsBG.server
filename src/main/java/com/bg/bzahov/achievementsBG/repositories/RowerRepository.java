package com.bg.bzahov.achievementsBG.repositories;

import com.bg.bzahov.achievementsBG.model.Rower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RowerRepository extends JpaRepository<Rower, Long> {
}