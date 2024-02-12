package oemer.aytteamforge.service

import oemer.aytteamforge.model.Match
import oemer.aytteamforge.model.Player
import oemer.aytteamforge.model.Team
import oemer.aytteamforge.repository.PlayerRepository
import org.springframework.stereotype.Service
import kotlin.math.absoluteValue

@Service
class TeamsBuilderService(val playerRepository: PlayerRepository) {

    fun buildTeams(players: List<Player>): Match {
        // TODO calculate Teams with skill
        val playersWithSkills = fillSkillsOfPlayers(players)
        val teams = calculateTeams(playersWithSkills)
        return Match(teams)
    }

    private fun calculateTeams(players: List<Player>): List<Team> {
        val skillSum = players.sumOf { it.skill ?: 0.0}
        if (players.size % 2 != 0){
            return emptyList()
        }
        val numOfPlayersInTeam = players.size / 2

        val orderedPlayers = players.sortedByDescending { it.skill }.toMutableList()
        val teamA = arrayListOf<Player>()

        val skillGoalOfTeam = skillSum / 2

        do {
            teamA.add(orderedPlayers.first())
            orderedPlayers.removeFirst()
            if (!isPossibleToAddMorePlayers(teamA.size,numOfPlayersInTeam)){
                break
            }
            teamA.add(orderedPlayers.last())
            orderedPlayers.removeLast()

        }
        while (teamA.size != numOfPlayersInTeam && teamA.sumOf { it.skill ?: 0.0 } < skillGoalOfTeam)
        val finalTeamA = Team(teamA, teamA.sumOf { it.skill!! } / numOfPlayersInTeam)
        val finalTeamB = Team(orderedPlayers, orderedPlayers.sumOf { it.skill!! } / numOfPlayersInTeam)

        return listOf(finalTeamA, finalTeamB)
    }

    private fun isPossibleToAddMorePlayers(teamSize: Int, numOfPlayersInTeam: Int): Boolean {
        return teamSize < numOfPlayersInTeam
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
        return playerRepository.getByName(name)?.skill ?: 0.0
    }
}