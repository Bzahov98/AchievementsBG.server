package com.bg.bzahov.achievementsBG.controlers;

import com.bg.bzahov.achievementsBG.dto.RowerIDCardDto;
import com.bg.bzahov.achievementsBG.model.RowerIDCard;
import com.bg.bzahov.achievementsBG.security.SecurityConstants;
import com.bg.bzahov.achievementsBG.services.RowerIDCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/" + SecurityConstants.API_VERSION + "/")
//@PreAuthorize("hasAuthority('ADMIN') or true")
public class RowerIDCardController {

    @Autowired
    private RowerIDCardService rowerIDCardService;

    @GetMapping("/rowers/id_cards/{rowerID}")
    public List<RowerIDCardDto> getAllRowerIDCardsByRower(@PathVariable Long rowerID/*, @RequestBody RowerIDCard rowerIDCard*/) {
        List<RowerIDCard> allRowerIDCardForRowerID = rowerIDCardService.getAllRowerIDCardForRowerID(rowerID);
        return allRowerIDCardForRowerID.stream().map(RowerIDCardDto::fromRowerIDCard).toList();
    }

    @PostMapping("/rowers/{rowerID}/id_cards")
    public RowerIDCard createRowerIDCard(@PathVariable Long rowerID, @RequestBody RowerIDCard rowerIDCard) {
        return rowerIDCardService.createRowerIDCard(rowerID, rowerIDCard);
    }
}