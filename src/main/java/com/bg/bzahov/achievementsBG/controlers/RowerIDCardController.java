package com.bg.bzahov.achievementsBG.controlers;

import com.bg.bzahov.achievementsBG.dto.RowerIDCardDto;
import com.bg.bzahov.achievementsBG.model.RowerIDCard;
import com.bg.bzahov.achievementsBG.services.base.RowerIDCardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bg.bzahov.achievementsBG.constants.PathConstants.BASE_URL;
import static com.bg.bzahov.achievementsBG.constants.PathConstants.PATH_ROWERS_ID_CARDS;

/**
 * RowerIDCardController is a REST controller that provides endpoints for managing RowerIDCard entities.
 * It uses the RowerIDCardService to perform operations on the RowerIDCard entities.
 */
@RestController
@RequestMapping(BASE_URL + PATH_ROWERS_ID_CARDS)
@AllArgsConstructor
//@PreAuthorize("hasAuthority('ADMIN') or true")
public class RowerIDCardController {

    private RowerIDCardService rowerIDCardService;

    @GetMapping("")
    public List<RowerIDCardDto> getAllRowerIDCards() {
        return rowerIDCardService.getAllRowerIDCardsDto();
    }

    /**
     * Endpoint to get all RowerIDCard entities associated with a specific Rower.
     *
     * @param rowerID The ID of the Rower.
     * @return A list of RowerIDCardDto objects.
     */
    @GetMapping("/{rowerID}")
    public List<RowerIDCardDto> getAllRowerIDCardsByRower(@PathVariable Long rowerID) {
        return rowerIDCardService.getAllRowerIDCardsByRowerDto(rowerID);
    }

    /**
     * Endpoint to create a new RowerIDCard entity and associate it with a Rower.
     *
     * @param rowerID     The ID of the Rower.
     * @param rowerIDCard The RowerIDCard entity to be created.
     * @return The created RowerIDCard entity.
     */
    @PostMapping("")
    public RowerIDCardDto createRowerIDCard(@RequestParam() Long rowerID, @RequestBody RowerIDCard rowerIDCard) {
        return rowerIDCardService.createRowerIDCardDto(rowerID, rowerIDCard);
    }

    // Update rowerIDCard by cardNumber

    /**
     * Endpoint to update a RowerIDCard entity by its card number.
     *
     * @param cardNumber    The card number of the RowerIDCard entity to be updated.
     * @param newCardNumber The new card number to be set.
     * @return The updated RowerIDCard entity.
     */
    @PutMapping("/update")
    public RowerIDCardDto updateRowerIDCard(@RequestParam String cardNumber, @RequestParam String newCardNumber) {
        return rowerIDCardService.updateRowerIDCardDto(cardNumber, newCardNumber);
    }

    /**
     * Endpoint to delete a RowerIDCard entity by its ID.
     *
     * @param id The ID of the RowerIDCard entity to be deleted.
     * @return A ResponseEntity with a message indicating the result of the operation.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRowerIDCardById(@PathVariable Long id) {
        return rowerIDCardService.deleteRowerIDCard(id);
    }

    /**
     * Endpoint to delete a RowerIDCard entity by its card number.
     *
     * @param cardNumber The card number of the RowerIDCard entity to be deleted.
     * @return A ResponseEntity with a message indicating the result of the operation.
     */
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteRowerIDCardByCardNumber(@RequestParam String cardNumber) {
        return rowerIDCardService.deleteRowerIDCard(cardNumber);
    }
}