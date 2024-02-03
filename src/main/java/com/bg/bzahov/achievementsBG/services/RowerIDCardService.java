package com.bg.bzahov.achievementsBG.services;

import com.bg.bzahov.achievementsBG.exceptions.RowerIDCardNotFoundException;
import com.bg.bzahov.achievementsBG.exceptions.RowerNotFoundException;
import com.bg.bzahov.achievementsBG.exceptions.ValidationFailedException;
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
                        () -> new RowerNotFoundException("RowerID: " + rowerID.toString())
                );
        rowerIDCard.setRower(rower);
        return rowerIDCardRepository.save(rowerIDCard);
    }

    public RowerIDCard getRowerIDCardById(Long id) {
        return rowerIDCardRepository.findById(id)
                .orElseThrow(
                        () -> new RowerIDCardNotFoundException("Id Card: " + id)
                );
    }

    private RowerIDCard getRowerIDCardByCardNumb(String cardNumber) {
        return rowerIDCardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new RowerIDCardNotFoundException("CardNumber: " + cardNumber));
    }

    public List<RowerIDCard> getAllRowerIDCardForRowerID(Long rowerId) {
        return rowerIDCardRepository.findAllByRowerId(rowerId)
                .orElseThrow(() -> new RowerIDCardNotFoundException("RowerID: " + rowerId));
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

    public RowerIDCard updateRowerIDCardByCardNumber(String cardNumber, String newCardNumber) {
        RowerIDCard existingRowerIDCard = getRowerIDCardByCardNumb(cardNumber);

        if (newCardNumber != null && !newCardNumber.trim().isEmpty()) {
            existingRowerIDCard.setCardNumber(newCardNumber);
        } else {
            throw new ValidationFailedException("New card number is empty or invalid!");
        }
        return rowerIDCardRepository.save(existingRowerIDCard);
    }

    public void deleteRowerIDCard(Long id) {
        RowerIDCard rowerIDCard = getRowerIDCardById(id);
        deleteRowerIdCardAndItsRelations(rowerIDCard);
    }

    public void deleteRowerIDCard(String cardNumber) {
        RowerIDCard rowerIDCard = getRowerIDCardByCardNumb(cardNumber);
        deleteRowerIdCardAndItsRelations(rowerIDCard);
    }

    private void deleteRowerIdCardAndItsRelations(RowerIDCard rowerIDCard) {
        Rower rower = rowerIDCard.getRower();
        rower.getRowerIDCards().remove(rowerIDCard);
        rowerRepository.save(rower);
        rowerIDCardRepository.delete(rowerIDCard);
    }
}
