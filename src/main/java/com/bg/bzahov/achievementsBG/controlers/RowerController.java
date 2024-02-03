package com.bg.bzahov.achievementsBG.controlers;

import com.bg.bzahov.achievementsBG.dto.auth.response.RowerResponseDto;
import com.bg.bzahov.achievementsBG.model.Rower;
import com.bg.bzahov.achievementsBG.security.SecurityConstants;
import com.bg.bzahov.achievementsBG.services.RowerIDCardService;
import com.bg.bzahov.achievementsBG.services.RowerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/" + SecurityConstants.API_VERSION + "/rowers")
@AllArgsConstructor
//@PreAuthorize("hasAuthority('ADMIN') or true")
public class RowerController {

    @Autowired
    private RowerService rowerService;
    private RowerIDCardService cardService;

    @PostMapping
    public Rower createRower(@RequestBody Rower rower) {
        return rowerService.addRower(rower);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RowerResponseDto> getRowerById(@PathVariable("id") Long id) {
        Rower rower = rowerService.getRowerById(id);
//        List<RowerIDCard> rowerIDCard = rowerService.getRowerIDCardByRowerID(id);
//        rower.setRowerIDCards(List.of(rowerIDCard));
        return new ResponseEntity<>(
                RowerResponseDto.fromRower(rower),
                HttpStatus.OK
        );
    }

    @PutMapping("update/{id}")
    public Rower updateRower(@PathVariable("id") Long id, @RequestBody Rower rower) {
        return rowerService.updateRower(id, rower);
    }

    @GetMapping
    public List<Rower> getAllRowers() {
        return rowerService.getAllRowers();
    }

    @GetMapping("/ByYearOfBirth/{yearOfBirth}")
    public List<Rower> getAllRowersByYearOfBirth(@PathVariable String yearOfBirth) {
        return rowerService.getAllRowersByYear(String.valueOf(yearOfBirth));
    }

    @DeleteMapping("/{id}")
    public void deleteRower(@PathVariable("id") Long id) {
        rowerService.deleteRower(id);
    }
}