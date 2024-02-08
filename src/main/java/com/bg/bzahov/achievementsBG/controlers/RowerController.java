package com.bg.bzahov.achievementsBG.controlers;

import com.bg.bzahov.achievementsBG.constants.ErrorConstants;
import com.bg.bzahov.achievementsBG.dto.auth.response.RowerResponseDto;
import com.bg.bzahov.achievementsBG.model.Rower;
import com.bg.bzahov.achievementsBG.services.RowerIDCardService;
import com.bg.bzahov.achievementsBG.services.RowerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bg.bzahov.achievementsBG.constants.PathConstants.BASE_URL;
import static com.bg.bzahov.achievementsBG.constants.PathConstants.PATH_ROWERS;
import static com.bg.bzahov.achievementsBG.utils.ControllersUtils.handleDeletion;
import static com.bg.bzahov.achievementsBG.utils.ModelUtils.getListResponseEntity;
import static com.bg.bzahov.achievementsBG.utils.ModelUtils.getRowerResponseDtoResponseEntity;

@RestController
@RequestMapping(BASE_URL + PATH_ROWERS)
@AllArgsConstructor
//@PreAuthorize("hasAuthority('ADMIN') or true")
public class RowerController {

    private RowerService rowerService;
    private RowerIDCardService cardService;

    // Create Mappings
    @PostMapping
    public ResponseEntity<RowerResponseDto> createRower(@RequestBody Rower rower) {
        Rower createdRower = rowerService.addRower(rower);
        return getRowerResponseDtoResponseEntity(createdRower);
    }

    // Get Mappings
    @GetMapping("/{id}")
    public ResponseEntity<RowerResponseDto> getRowerById(@PathVariable("id") Long id) {
        Rower fetchedRower = rowerService.getRowerById(id);
        return getRowerResponseDtoResponseEntity(fetchedRower);
    }

    @GetMapping
    public ResponseEntity<List<RowerResponseDto>> getAllRowers() {
        List<Rower> allRowers = rowerService.getAllRowers();
        return getListResponseEntity(allRowers);
    }

    @GetMapping("/byYearOfBirth/{yearOfBirth}")
    public ResponseEntity<List<RowerResponseDto>> getAllRowersByYearOfBirth(@PathVariable String yearOfBirth) {
        List<Rower> allRowers = rowerService.getAllRowersByYear(String.valueOf(yearOfBirth));
        return getListResponseEntity(allRowers);
    }
    // Update Mappings

    @PutMapping("update/{id}")
    public ResponseEntity<RowerResponseDto> updateRower(@PathVariable("id") Long id, @RequestBody Rower rower) {
        Rower updatedRower = rowerService.updateRower(id, rower);
        return getRowerResponseDtoResponseEntity(updatedRower);
    }

    // Delete mappings
    @DeleteMapping("/delete{id}")
    public ResponseEntity<String> deleteRowerByIdVariable(@PathVariable Long id) {
        return handleRowerDeletion(id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteRowerByIdParam(@RequestParam Long id) {
        return handleRowerDeletion(id);
    }

    private ResponseEntity<String> handleRowerDeletion(Long id) {
        return handleDeletion(
                () -> rowerService.deleteRower(id),
                id.toString(),
                ErrorConstants.ERROR_ROWER_WITH_IDENTIFIER_ID
        );
    }
}