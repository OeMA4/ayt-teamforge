package oemer.aytteamforge.controller.dto.`in`

data class TeamsRequestDto(val players: List<PlayerDto>)

data class PlayerDto(val name: String, val skill: Double?)
