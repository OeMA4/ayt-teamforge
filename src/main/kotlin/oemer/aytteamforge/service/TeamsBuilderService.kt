package oemer.aytteamforge.service

import oemer.aytteamforge.model.Match
import oemer.aytteamforge.model.Player
import org.springframework.stereotype.Service

@Service
class TeamsBuilderService {

    fun buildTeams(players: List<Player>): Match {
        // TODO calculate Teams with skill
        val playersWithSkills = fillSkillsOfPlayers(players)

        return Match(emptyList())
    }

    private fun fillSkillsOfPlayers(players: List<Player>): List<Player> {
        return players.map {
            if (it.skill == null) {
                it.copy(skill = getSkillOfPlayer(it.name))
            } else
                it
        }
    }

    private fun getSkillOfPlayer(name: String): Double{
        // TODO return value from database
        return 0.0
    }
}