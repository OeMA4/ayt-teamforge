package oemer.aytteamforge.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class TeamsController {

    @GetMapping("/")
    fun calculateTeams(): String {
        return "servus"
    }
}