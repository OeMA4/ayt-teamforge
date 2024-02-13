package oemer.aytteamforge.controller

import oemer.aytteamforge.model.MatchHistory
import oemer.aytteamforge.service.MatchHistoryService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping

@Controller("history")
class MatchHistoryController(private val matchHistoryService: MatchHistoryService) {

    @GetMapping("allRanks")
    fun getAllRanks(): MatchHistory {
        return matchHistoryService.getHistory()
    }

    @PutMapping("adaptRanks")
    fun adaptRanks(winningTeam: List<String>){
        matchHistoryService.addWinningTeam(winningTeam)
    }

}