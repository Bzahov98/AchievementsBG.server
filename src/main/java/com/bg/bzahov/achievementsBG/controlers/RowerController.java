package com.bg.bzahov.achievementsBG.controlers;

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
        return rowerService.addRowerAndReturnResponse(rower);
    }

    // Get Mappings
    @GetMapping("/{id}")
    public ResponseEntity<RowerResponseDto> getRowerById(@PathVariable("id") Long id) {
        return rowerService.getRowerByIdAndReturnResponse(id);
    }

    @GetMapping
    public ResponseEntity<List<RowerResponseDto>> getAllRowers() {
        return rowerService.getAllRowersAndReturnResponse();
    }

    @GetMapping("/byYearOfBirth/{yearOfBirth}")
    public ResponseEntity<List<RowerResponseDto>> getAllRowersByYearOfBirth(@PathVariable String yearOfBirth) {
        return rowerService.getAllRowersByBirthYearAndReturnResponse(yearOfBirth);
    }
    // Update Mappings

    @PutMapping("update/{id}")
    public ResponseEntity<RowerResponseDto> updateRower(@PathVariable("id") Long id, @RequestBody Rower rower) {
        return rowerService.updateRowerAndReturnResponse(id, rower);
    }

    // Delete mappings
    @DeleteMapping("/delete{id}")
    public ResponseEntity<String> deleteRowerByIdVariable(@PathVariable Long id) {
        return rowerService.deleteRowerByIdAndReturnResponse(id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteRowerByIdParam(@RequestParam Long id) {
        return rowerService.deleteRowerByIdAndReturnResponse(id);
    }

}