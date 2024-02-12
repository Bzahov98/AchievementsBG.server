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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.bg.bzahov.achievementsBG.constants.ErrorConstants.*;
import static com.bg.bzahov.achievementsBG.constants.StringConstants.*;
import static com.bg.bzahov.achievementsBG.utils.ServicesUtils.handleDeletion;
import static com.bg.bzahov.achievementsBG.utils.ServicesUtils.mapAndConvertEntitiesToDto;

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
            throw new ValidationFailedException(
                    ERROR_ROWER_ID_CARD_INVALID_CARD_NUMBER + rowerIDCard.getCardNumber() + ERROR_ALREADY_EXISTS_EXTENSIONS
            );
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
                        () -> new RowerIDCardNotFoundException(PARAM_ID_CARD + id)
                );
    }

    public RowerIDCard getRowerIDCardByCardNumb(String cardNumber) {
        return rowerIDCardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new RowerIDCardNotFoundException(CARD_NUMBER + cardNumber));
    }

    public List<RowerIDCard> getAllRowerIDCardForRowerID(Long rowerId) {
        // Check does rower exist or throw exception
        getRowerOrThrowException(rowerId);

        return rowerIDCardRepository.findAllByRowerId(rowerId)
                .orElseThrow(() -> new RowerIDCardNotFoundException(PARAM_ROWER_ID + rowerId));
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
            throw new ValidationFailedException(ERROR_NEW_CARD_NUMBER_IS_INVALID);
        }
        return rowerIDCardRepository.save(existingRowerIDCard);
    }

    public ResponseEntity<String> deleteRowerIDCard(Long id) {
        RowerIDCard rowerIDCard = getRowerIDCardById(id);
        return handleDeletion(
                () -> deleteRowerIdCardAndItsRelations(rowerIDCard),
                CARD_ID + id.toString(),
                ERROR_IDENTIFIER_ROWER_ID_CARD
        );
    }

    public ResponseEntity<String> deleteRowerIDCard(String cardNumber) {
        RowerIDCard rowerIDCard = getRowerIDCardByCardNumb(cardNumber);
        return handleDeletion(
                () -> deleteRowerIdCardAndItsRelations(rowerIDCard),
                CARD_NUMBER + cardNumber,
                ERROR_IDENTIFIER_ROWER_ID_CARD
        );
    }

    private void deleteRowerIdCardAndItsRelations(RowerIDCard rowerIDCard) {
        Rower rower = rowerIDCard.getRower();
        rower.getRowerIDCards().remove(rowerIDCard);
        rowerRepository.save(rower);
        rowerIDCardRepository.delete(rowerIDCard);
    }

    // Dto's
    public List<RowerIDCardDto> getAllRowerIDCardsDto() {
        return mapAndConvertEntitiesToDto(getAllRowerIDCards(), RowerIDCardDto::fromRowerIDCard);
    }

    public List<RowerIDCardDto> getAllRowerIDCardsByRowerDto(Long rowerID) {

        return mapAndConvertEntitiesToDto(
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

    // StringUtils
    private Rower getRowerOrThrowException(Long rowerID) {
        return rowerRepository.findById(rowerID)
                .orElseThrow(() -> new RowerNotFoundException(ROWER_ID + rowerID));
    }
}
