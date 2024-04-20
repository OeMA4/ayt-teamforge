package oemer.aytteamforge.service

import oemer.aytteamforge.controller.dto.`in`.TeamsRequestDto
import oemer.aytteamforge.model.Match
import oemer.aytteamforge.model.Player
import oemer.aytteamforge.model.Team
import oemer.aytteamforge.repository.PlayerRepository
import org.springframework.stereotype.Service
import kotlin.math.abs

@Service
class TeamsBuilderService(val playerRepository: PlayerRepository) {

    fun buildTeams(playersDto: TeamsRequestDto): Match {

        val players = mapDtoToPlayers(playersDto)
        if (players.size % 2 != 0){
            return Match(emptyList())
        }
        val bestTeams = mutableListOf<Pair<Team, Team>>()

        generateTeams(players, mutableListOf(), mutableListOf(), 0, bestTeams, Double.MAX_VALUE)
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
            createBestTeamsPair(team1, team2, bestDiff, bestTeams)
            return
        }

        val player = players[index]

        // Try adding player to team 1
        team1.add(player)
        generateTeams(players, team1, team2, index + 1, bestTeams,
            bestDiff.coerceAtMost(abs(team1.sumOf { it.skill.toDouble() } - team2.sumOf { it.skill })))
        team1.remove(player)

        // Try adding player to team 2
        team2.add(player)
        generateTeams(players, team1, team2, index + 1, bestTeams,
            bestDiff.coerceAtMost(abs(team1.sumOf { it.skill.toDouble() } - team2.sumOf { it.skill })))
        team2.remove(player)
    }

    private fun createBestTeamsPair(
        team1: MutableList<Player>,
        team2: MutableList<Player>,
        bestDiff: Double,
        bestTeams: MutableList<Pair<Team, Team>>
    ) {
        val totalSkillTeam1 = team1.sumOf { it.skill }
        val totalSkillTeam2 = team2.sumOf { it.skill }
        val diff = abs(totalSkillTeam1 - totalSkillTeam2).toDouble()

        if (diff < bestDiff) {
            bestTeams.clear()
            bestTeams.add(
                Pair(
                    Team(team1.toList(), calculateAverageTeamSkill(team1)),
                    Team(team2.toList(), calculateAverageTeamSkill(team2))
                )
            )
        } else if (diff == bestDiff) {
            bestTeams.add(
                Pair(
                    Team(team1.toList(), calculateAverageTeamSkill(team1)),
                    Team(team2.toList(), calculateAverageTeamSkill(team2))
                )
            )
        }
        return
    }

    private fun mapDtoToPlayers(requestDto: TeamsRequestDto): List<Player>{
        return requestDto.players.map { dto ->
            val savedPlayer = playerRepository.getByName(dto.name)
            savedPlayer?.let { Player(it.id,it.name, it.skill) } ?: Player(name = dto.name, skill = dto.skill ?: 0)
             }
    }

    private fun calculateAverageTeamSkill(team: List<Player>): Double {
        return team.sumOf { it.skill.toDouble() } / team.size
    }
}