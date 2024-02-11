package oemer.aytteamforge.service

import oemer.aytteamforge.model.Player
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.*

class TeamsBuilderServiceTest {

    @Test
    fun make_Teams_with_same_Skillpoints() {
        //arrange
        val players = listOf(
                Player("X", skill = 3.0),
                Player("T", skill = 4.0),
                Player("K", skill = 5.0),
                Player("O", skill = 1.0),
                Player("Y", skill = 2.0),
                Player("A", skill = 4.0),
                Player("B", skill = 4.0),
                Player("C", skill = 5.0),
                Player("D", skill = 1.0),
                Player("E", skill = 1.0))
        val sut = TeamsBuilderService()
        val expectedAvgSkill = 15.0

        // act
        val match = sut.buildTeams(players)

        // assert
        assertThat(match.teams)
                .isNotEmpty
                .allSatisfy { team -> assertThat(team.avgSkill).isEqualTo(expectedAvgSkill) }
    }
}