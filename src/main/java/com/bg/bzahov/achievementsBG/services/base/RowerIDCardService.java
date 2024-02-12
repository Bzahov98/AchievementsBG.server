package com.bg.bzahov.achievementsBG.services.base;

import com.bg.bzahov.achievementsBG.dto.RowerIDCardDto;
import com.bg.bzahov.achievementsBG.model.RowerIDCard;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RowerIDCardService {
    // Dto's
    List<RowerIDCardDto> getAllRowerIDCardsDto();

    List<RowerIDCardDto> getAllRowerIDCardsByRowerDto(Long rowerID);

    RowerIDCardDto createRowerIDCardDto(Long rowerID, RowerIDCard rowerIDCard);

    RowerIDCardDto updateRowerIDCardDto(String cardNumber, String newCardNumber);

    ResponseEntity<String> deleteRowerIDCard(Long id);

    ResponseEntity<String> deleteRowerIDCard(String cardNumber);
}
