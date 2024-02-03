package com.bg.bzahov.achievementsBG.controlers;

import com.bg.bzahov.achievementsBG.dto.RowerIDCardDto;
import com.bg.bzahov.achievementsBG.exceptions.RowerIDCardNotFoundException;
import com.bg.bzahov.achievementsBG.model.RowerIDCard;
import com.bg.bzahov.achievementsBG.security.SecurityConstants;
import com.bg.bzahov.achievementsBG.services.RowerIDCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RowerIDCardController is a REST controller that provides endpoints for managing RowerIDCard entities.
 * It uses the RowerIDCardService to perform operations on the RowerIDCard entities.
 */
@RestController
@RequestMapping("api/" + SecurityConstants.API_VERSION + "/")
//@PreAuthorize("hasAuthority('ADMIN') or true")
public class RowerIDCardController {

    @Autowired
    private RowerIDCardService rowerIDCardService;

    /**
     * Endpoint to get all RowerIDCard entities associated with a specific Rower.
     *
     * @param rowerID The ID of the Rower.
     * @return A list of RowerIDCardDto objects.
     */
    @GetMapping("/rowers/id_cards/{rowerID}")
    public List<RowerIDCardDto> getAllRowerIDCardsByRower(@PathVariable Long rowerID/*, @RequestBody RowerIDCard rowerIDCard*/) {
        List<RowerIDCard> allRowerIDCardForRowerID = rowerIDCardService.getAllRowerIDCardForRowerID(rowerID);
        return allRowerIDCardForRowerID.stream().map(RowerIDCardDto::fromRowerIDCard).toList();
    }

    /**
     * Endpoint to create a new RowerIDCard entity and associate it with a Rower.
     *
     * @param rowerID     The ID of the Rower.
     * @param rowerIDCard The RowerIDCard entity to be created.
     * @return The created RowerIDCard entity.
     */
    @PostMapping("/rowers/{rowerID}/id_cards")
    public RowerIDCard createRowerIDCard(@PathVariable Long rowerID, @RequestBody RowerIDCard rowerIDCard) {
        return rowerIDCardService.createRowerIDCard(rowerID, rowerIDCard);
    }

    // Update rowerIDCard by cardNumber
    /**
     * Endpoint to update a RowerIDCard entity by its card number.
     *
     * @param cardNumber     The card number of the RowerIDCard entity to be updated.
     * @param newCardNumber  The new card number to be set.
     * @return The updated RowerIDCard entity.
     */
    @PutMapping("/rowers/id_cards/update")
    public RowerIDCardDto updateRowerIDCard(@RequestParam String cardNumber, @RequestParam String newCardNumber) {
        return RowerIDCardDto.fromRowerIDCard(
                rowerIDCardService.updateRowerIDCardByCardNumber(cardNumber, newCardNumber)
        );
    }
    /**
     * Endpoint to delete a RowerIDCard entity by its ID.
     * @param id The ID of the RowerIDCard entity to be deleted.
     * @return A ResponseEntity with a message indicating the result of the operation.
     */
    @DeleteMapping("/rowers/id_cards/delete/{id}")
    public ResponseEntity<String> deleteRowerIDCardById(@PathVariable Long id) {
        return handleRowerIDCardDeletion(() -> rowerIDCardService.deleteRowerIDCard(id), id.toString());
    }
    /**
     * Endpoint to delete a RowerIDCard entity by its card number.
     * @param cardNumber The card number of the RowerIDCard entity to be deleted.
     * @return A ResponseEntity with a message indicating the result of the operation.
     */
    @DeleteMapping("/rowers/id_cards/delete")
    public ResponseEntity<String> deleteRowerIDCardByCardNumber(@RequestParam String cardNumber) {
        return handleRowerIDCardDeletion(() -> rowerIDCardService.deleteRowerIDCard(cardNumber), cardNumber);
    }

    /**
     * Private method to handle the deletion of a RowerIDCard entity.
     * It is used to reduce code duplication in the deleteRowerIDCardById and deleteRowerIDCardByCardNumber methods.
     * @param deletionLogic A Runnable that encapsulates the deletion logic.
     * @param identifier The identifier (either ID or card number) of the RowerIDCard entity to be deleted.
     * @return A ResponseEntity with a message indicating the result of the operation.
     */
    private ResponseEntity<String> handleRowerIDCardDeletion(Runnable deletionLogic, String identifier) {
        String identifierStr = "RowerIDCard with identifier: ";
        try {
            deletionLogic.run();
            return ResponseEntity.ok(identifierStr + identifier + " was DELETED successfully!");
        } catch (RowerIDCardNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(identifierStr + identifier + " DOES NOT EXIST!!");
        }
    }
}