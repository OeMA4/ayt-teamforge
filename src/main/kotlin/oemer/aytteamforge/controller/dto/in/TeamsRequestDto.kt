package oemer.aytteamforge.controller.dto.`in`

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class TeamsRequestDto @JsonCreator constructor(
    @JsonProperty("players") val players: List<PlayerDto>)

data class PlayerDto(val name: String, val skill: Int?)
