package oemer.aytteamforge.service

import oemer.aytteamforge.model.MatchHistory
import oemer.aytteamforge.model.PlayerInformation
import oemer.aytteamforge.repository.PlayerRepository
import org.springframework.stereotype.Service

@Service
class MatchHistoryService(val playerRepository: PlayerRepository) {

    fun getHistory(): MatchHistory {
        val allPlayers = playerRepository.findAll()
        return MatchHistory(allPlayers.map { PlayerInformation(it.name, it.numOfWins) })
    }

    fun addWinningTeam(winningTeam: List<String>){
        val player = winningTeam.map { playerRepository.getByName(it) }
        val adaptedPlayers = player.map { it?.copy(numOfWins = it.numOfWins + 1)}
        adaptedPlayers.forEach { it?.let { playerRepository.save(it) } }
    }

}