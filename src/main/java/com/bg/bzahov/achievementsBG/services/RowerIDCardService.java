package com.bg.bzahov.achievementsBG.services;

import com.bg.bzahov.achievementsBG.exceptions.RowerIDCardNotFoundException;
import com.bg.bzahov.achievementsBG.exceptions.RowerNotFoundException;
import com.bg.bzahov.achievementsBG.model.Rower;
import com.bg.bzahov.achievementsBG.model.RowerIDCard;
import com.bg.bzahov.achievementsBG.repositories.RowerIDCardRepository;
import com.bg.bzahov.achievementsBG.repositories.RowerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor

@Service
public class RowerIDCardService {

    @Autowired
    private RowerIDCardRepository rowerIDCardRepository;
    @Autowired
    private RowerRepository rowerRepository;

    public RowerIDCard createRowerIDCard(Long rowerID, RowerIDCard rowerIDCard) {
        Rower rower = rowerRepository.findById(rowerID)
                .orElseThrow(
                        () -> new RowerNotFoundException(rowerID.toString())
                );
        rowerIDCard.setRower(rower);
        return rowerIDCardRepository.save(rowerIDCard);
    }

    public RowerIDCard getRowerIDCardById(Long id) {
        return rowerIDCardRepository.findById(id)
                .orElseThrow(
                        () -> new RowerIDCardNotFoundException(id.toString())
                );
    }

    public List<RowerIDCard> getAllRowerIDCardByRowerID(Long rowerId) {
        Rower rower = rowerRepository.findById(rowerId)
                .orElseThrow(() -> new RowerNotFoundException(rowerId.toString()));
        return rower.getRowerIDCards();
    }
//    public RowerIDCard getAllRowerIDCardByRowerID(Long rowerId ) {
//        return rowerRepository.findByRowerId(rowerId)
//                .orElseThrow(
//                        () -> new RowerIDCardNotFoundException(rowerId.toString())
//                );
//    }

    public RowerIDCard updateRowerIDCard(Long id, RowerIDCard rowerIDCard) {
        RowerIDCard existingRowerIDCard = getRowerIDCardById(id);

        existingRowerIDCard.setCardNumber(rowerIDCard.getCardNumber());
        return rowerIDCardRepository.save(existingRowerIDCard);
    }

    public void deleteRowerIDCard(Long id) {
        RowerIDCard rowerIDCard = getRowerIDCardById(id);
        rowerIDCardRepository.delete(rowerIDCard);
    }
}
