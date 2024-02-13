package oemer.aytteamforge.service

import oemer.aytteamforge.model.Match
import oemer.aytteamforge.model.Player
import oemer.aytteamforge.model.Team
import oemer.aytteamforge.repository.PlayerRepository
import org.springframework.stereotype.Service
import kotlin.math.abs
import kotlin.math.absoluteValue

@Service
class TeamsBuilderService(val playerRepository: PlayerRepository) {

    fun buildTeams(players: List<Player>): Match {
        if (players.size % 2 != 0){
            return Match(emptyList())
        }
        val playersWithSkills = fillSkillsOfPlayers(players)

        val bestTeams = mutableListOf<Pair<Team, Team>>()
        generateTeams(playersWithSkills, mutableListOf(), mutableListOf(), 0, bestTeams, Double.MAX_VALUE)

        val filteredBestTeams = findBestTeamCombination(bestTeams)
        val teamA = filteredBestTeams.first
        val teamB = filteredBestTeams.second
        return Match(listOf(teamA,teamB))
    }

    private fun findBestTeamCombination(teams: MutableList<Pair<Team, Team>>): Pair<Team, Team>{
        val evenTeams = teams.filter { pair -> pair.first.avgSkill == pair.second.avgSkill }
        return if (evenTeams.isNotEmpty()){
            evenTeams.random()
        } else {
            teams.minByOrNull { abs(it.first.avgSkill - it.second.avgSkill) }!!
        }
    }

    fun generateTeams(players: List<Player>, team1: MutableList<Player>, team2: MutableList<Player>, index: Int, bestTeams: MutableList<Pair<Team,Team>>, bestDiff: Double) {
        if (index == players.size) {
            val totalSkillTeam1 = team1.sumOf { it.skill!! }
            val totalSkillTeam2 = team2.sumOf { it.skill!! }
            val diff = abs(totalSkillTeam1 - totalSkillTeam2)

            if (diff < bestDiff) {
                bestTeams.clear()
                bestTeams.add(Pair(Team(team1.toList(), calculateAverageTeamSkill(team1)) , Team(team2.toList(),calculateAverageTeamSkill(team2) )))
            } else if (diff == bestDiff) {
                bestTeams.add(Pair(Team(team1.toList(), calculateAverageTeamSkill(team1)) , Team(team2.toList(),calculateAverageTeamSkill(team2) )))
            }
            return
        }

        val player = players[index]

        // Try adding player to team 1
        team1.add(player)
        generateTeams(players, team1, team2, index + 1, bestTeams, bestDiff.coerceAtMost(abs(team1.sumOf { it.skill!! } - team2.sumOf { it.skill!! })))
        team1.remove(player)

        // Try adding player to team 2
        team2.add(player)
        generateTeams(players, team1, team2, index + 1, bestTeams, bestDiff.coerceAtMost(abs(team1.sumOf { it.skill!! } - team2.sumOf { it.skill!! })))
        team2.remove(player)
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

    private fun calculateAverageTeamSkill(team: List<Player>): Double {
        return team.sumOf { it.skill!! } / team.size
    }
}