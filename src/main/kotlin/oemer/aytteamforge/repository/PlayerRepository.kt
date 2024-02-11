package oemer.aytteamforge.repository

import oemer.aytteamforge.model.Player
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlayerRepository : JpaRepository<Player, Long> {
    fun getByName(name: String): Player?
}