package com.bg.bzahov.achievementsBG.controlers;

import com.bg.bzahov.achievementsBG.dto.auth.response.RowerResponseDto;
import com.bg.bzahov.achievementsBG.model.Rower;
import com.bg.bzahov.achievementsBG.services.base.RowerIDCardService;
import com.bg.bzahov.achievementsBG.services.base.RowerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bg.bzahov.achievementsBG.constants.PathConstants.*;

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
    @GetMapping("/" + PATH_VARIABLE_ID)
    public ResponseEntity<RowerResponseDto> getRowerById(@PathVariable(PATH_VAR_ID) Long id) {
        return rowerService.getRowerByIdAndReturnResponse(id);
    }

    @GetMapping
    public ResponseEntity<List<RowerResponseDto>> getAllRowers() {
        return rowerService.getAllRowersAndReturnResponse();
    }

    @GetMapping("/byYearOfBirth/" + PATH_VARIABLE_YEAR_OF_BIRTH)
    public ResponseEntity<List<RowerResponseDto>> getAllRowersByYearOfBirth(@PathVariable Integer yearOfBirth) {
        return rowerService.getAllRowersByBirthYearAndReturnResponse(yearOfBirth);
    }

    // Update Mappings
    @PutMapping("update/" + PATH_VARIABLE_ID)
    public ResponseEntity<RowerResponseDto> updateRower(@PathVariable(PATH_VAR_ID) Long id, @RequestBody Rower rower) {
        return rowerService.updateRowerAndReturnResponse(id, rower);
    }

    // Delete mappings
    @DeleteMapping("/delete" + PATH_VARIABLE_ID)
    public ResponseEntity<String> deleteRowerByIdVariable(@PathVariable Long id) {
        return rowerService.deleteRowerByIdAndReturnResponse(id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteRowerByIdParam(@RequestParam Long id) {
        return rowerService.deleteRowerByIdAndReturnResponse(id);
    }

}