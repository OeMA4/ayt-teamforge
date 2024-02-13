package oemer.aytteamforge.controller

import oemer.aytteamforge.controller.dto.`in`.TeamsRequestDto
import oemer.aytteamforge.model.Match
import oemer.aytteamforge.service.TeamsBuilderService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class TeamsController(private val teamsBuilderService: TeamsBuilderService) {

    @GetMapping("/")
    fun calculateMatch(teamsRequest: TeamsRequestDto): Match {
        return teamsBuilderService.buildTeams(teamsRequest)
    }
}