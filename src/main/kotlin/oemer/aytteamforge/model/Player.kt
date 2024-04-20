package oemer.aytteamforge.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id


@Entity
data class Player(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
                  val name: String = "default Name",
                  val skill: Int = 0,
                  val numOfWins: Int = 0)


