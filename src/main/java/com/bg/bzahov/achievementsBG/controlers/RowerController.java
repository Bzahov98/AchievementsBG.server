package com.bg.bzahov.achievementsBG.controlers;

import com.bg.bzahov.achievementsBG.model.Rower;
import com.bg.bzahov.achievementsBG.security.SecurityConstants;
import com.bg.bzahov.achievementsBG.services.RowerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/" + SecurityConstants.API_VERSION + "/rowers")
@AllArgsConstructor
//@PreAuthorize("hasAuthority('ADMIN') or true")
public class RowerController {

    @Autowired
    private RowerService rowerService;

    @PostMapping
    public Rower createRower(@RequestBody Rower rower) {
        return rowerService.addRower(rower);
    }

    @GetMapping("/{id}")
    public Rower getRowerById(@PathVariable("id") Long id) {
        return rowerService.getRowerById(id);
    }

    @GetMapping
    public List<Rower> getAllStudents() {
        return rowerService.getAllRowers();
    }

    @PutMapping("/{id}")
    public Rower updateRower(@PathVariable("id") Long id, @RequestBody Rower rower) {
        return rowerService.updateRower(id, rower);
    }

    @DeleteMapping("/{id}")
    public void deleteRower(@PathVariable("id") Long id) {
        rowerService.deleteRower(id);
    }
}