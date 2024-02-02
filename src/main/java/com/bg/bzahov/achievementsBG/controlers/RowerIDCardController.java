package com.bg.bzahov.achievementsBG.controlers;

import com.bg.bzahov.achievementsBG.model.RowerIDCard;
import com.bg.bzahov.achievementsBG.security.SecurityConstants;
import com.bg.bzahov.achievementsBG.services.RowerIDCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/" + SecurityConstants.API_VERSION + "/rowers/{rowerID}/id_cards")
//@PreAuthorize("hasAuthority('ADMIN') or true")
public class RowerIDCardController {

    @Autowired
    private RowerIDCardService rowerIDCardService;

    @PostMapping
    public RowerIDCard createRowerIDCard(@PathVariable Long rowerID, @RequestBody RowerIDCard rowerIDCard) {
        return rowerIDCardService.createRowerIDCard(rowerID, rowerIDCard);
    }
}