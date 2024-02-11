package com.bg.bzahov.achievementsBG.services;

import com.bg.bzahov.achievementsBG.dto.RowerIDCardDto;
import com.bg.bzahov.achievementsBG.exceptions.RowerIDCardNotFoundException;
import com.bg.bzahov.achievementsBG.exceptions.RowerNotFoundException;
import com.bg.bzahov.achievementsBG.exceptions.ValidationFailedException;
import com.bg.bzahov.achievementsBG.model.Rower;
import com.bg.bzahov.achievementsBG.model.RowerIDCard;
import com.bg.bzahov.achievementsBG.repositories.RowerIDCardRepository;
import com.bg.bzahov.achievementsBG.repositories.RowerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.bg.bzahov.achievementsBG.constants.ErrorConstants.ERROR_CARD_NUMBER_IS_INVALID;
import static com.bg.bzahov.achievementsBG.constants.StringConstants.ROWER_ID;
import static com.bg.bzahov.achievementsBG.utils.ServicesUtils.mapAndConvertEntityToDto;

@AllArgsConstructor

@Service
public class RowerIDCardService {

    private RowerIDCardRepository rowerIDCardRepository;
    private RowerRepository rowerRepository;

    public List<RowerIDCard> getAllRowerIDCards() {
        return rowerIDCardRepository.findAll();
    }

    public RowerIDCard createRowerIDCard(Long rowerID, RowerIDCard rowerIDCard) {
        Rower rower = getRowerOrThrowException(rowerID);

        // Check if a RowerIDCard with the same card_number already exists
        Optional<RowerIDCard> existingCard = rowerIDCardRepository.findByCardNumber(rowerIDCard.getCardNumber());
        if (existingCard.isPresent()) {
            throw new ValidationFailedException("A RowerIDCard with card_number " + rowerIDCard.getCardNumber() + " already exists.");
        }

        rower.getRowerIDCards().removeIf(card -> card.getCardNumber() == null || card.getCardNumber()
                .equals(rowerIDCard.getCardNumber()));
        rower.getRowerIDCards().add(rowerIDCard);
        rowerIDCard.setRower(rower);
        rowerRepository.save(rower);
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
        // Check does rower exist or throw exception
        getRowerOrThrowException(rowerId);

        return rowerIDCardRepository.findAllByRowerId(rowerId)
                .orElseThrow(() -> new RowerIDCardNotFoundException("RowerID: " + rowerId));
    }

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
            throw new ValidationFailedException(ERROR_CARD_NUMBER_IS_INVALID);
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


    public List<RowerIDCardDto> getAllRowerIDCardsDto() {
        return mapAndConvertEntityToDto(getAllRowerIDCards(), RowerIDCardDto::fromRowerIDCard);
    }

    public List<RowerIDCardDto> getAllRowerIDCardsByRowerDto(Long rowerID) {

        return mapAndConvertEntityToDto(
                getAllRowerIDCardForRowerID(rowerID),
                RowerIDCardDto::fromRowerIDCard
        );
    }

    public RowerIDCardDto createRowerIDCardDto(Long rowerID, RowerIDCard rowerIDCard) {
        return RowerIDCardDto.fromRowerIDCard(createRowerIDCard(rowerID, rowerIDCard));
    }

    public RowerIDCardDto updateRowerIDCardDto(String cardNumber, String newCardNumber) {
        RowerIDCard updatedCard = updateRowerIDCardByCardNumber(cardNumber, newCardNumber);
        return RowerIDCardDto.fromRowerIDCard(updatedCard);
    }

    // Utils
    private Rower getRowerOrThrowException(Long rowerID) {
        return rowerRepository.findById(rowerID)
                .orElseThrow(() -> new RowerNotFoundException(ROWER_ID + rowerID));
    }
}
