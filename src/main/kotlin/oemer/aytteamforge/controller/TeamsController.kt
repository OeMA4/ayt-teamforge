package oemer.aytteamforge.controller

import oemer.aytteamforge.model.Match
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class TeamsController {

    @GetMapping("/")
    fun calculateMatch(): Match {
        val match = Match(emptyList());
        return match;
    }
}