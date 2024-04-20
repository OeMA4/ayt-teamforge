package oemer.aytteamforge.controller

import oemer.aytteamforge.controller.dto.`in`.TeamsRequestDto
import oemer.aytteamforge.model.Match
import oemer.aytteamforge.service.TeamsBuilderService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TeamsController(private val teamsBuilderService: TeamsBuilderService) {

    @PostMapping("/")
    fun calculateMatch(@RequestBody teamsRequest: TeamsRequestDto): Match {
        return teamsBuilderService.buildTeams(teamsRequest)
    }
}