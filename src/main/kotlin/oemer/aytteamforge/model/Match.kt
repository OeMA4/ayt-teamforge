package oemer.aytteamforge.model

data class Match(val teams: List<Team>){
    override fun toString(): String {

        val teamA = teams[0]
        val teamB = teams[1]
        val representation = StringBuilder()
        representation.append("Team A VS Team B \n")
        representation.append("--------------------\n")
        for (i in 0 until teamA.players.size)
            representation.append("${teamA.players[i].name} vs. ${teamB.players[i].name} \n")

        if (teamA.avgSkill == teamB.avgSkill){
            representation.append("--------------------\n")
            representation.append("Teams sind laut Rechnung FAIR")
        }

        return representation.toString()
    }
}