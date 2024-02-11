package oemer.aytteamforge.controller

import oemer.aytteamforge.controller.dto.`in`.PlayerDto
import oemer.aytteamforge.controller.dto.`in`.TeamsRequestDto
import oemer.aytteamforge.model.Match
import oemer.aytteamforge.model.Player
import oemer.aytteamforge.service.TeamsBuilderService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class TeamsController(private val teamsBuilderService: TeamsBuilderService) {


    @GetMapping("/")
    fun calculateMatch(teamsRequest: TeamsRequestDto): Match {
        return teamsBuilderService.buildTeams(mapPlayerDtoToPlayer(teamsRequest.players))
    }

    private fun mapPlayerDtoToPlayer(players: List<PlayerDto>): List<Player> {
        return players.map { Player(it.name, it.skill) }
    }
}