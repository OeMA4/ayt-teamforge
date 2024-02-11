package oemer.aytteamforge.model

data class Player(val name: String, val skill: Double)

data class Team(val players: List<Player>, val avgSkill: Double)

data class Match(val teams: List<Team>)


