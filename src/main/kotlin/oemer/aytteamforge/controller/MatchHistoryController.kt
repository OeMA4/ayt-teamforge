package oemer.aytteamforge.controller

import oemer.aytteamforge.model.MatchHistory
import oemer.aytteamforge.service.MatchHistoryService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping

@Controller("history")
class MatchHistoryController(private val matchHistoryService: MatchHistoryService) {

    @GetMapping("allranks")
    fun getAllRanks(): MatchHistory {
        return matchHistoryService.getHistory()
    }

    @PutMapping("adaptranks")
    fun adaptRanks(winningTeam: List<String>){
        matchHistoryService.addWinningTeam(winningTeam)
    }

}