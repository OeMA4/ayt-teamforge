package oemer.aytteamforge.model


data class MatchHistory(val playerRanks: List<PlayerInformation> )

data class PlayerInformation(val name: String, val numOfWins: Int)