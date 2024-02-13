package oemer.aytteamforge.service

import oemer.aytteamforge.model.Player
import oemer.aytteamforge.repository.PlayerRepository
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.*
import org.mockito.Mock
import org.mockito.Mockito.mock

class TeamsBuilderServiceTest {

    @Mock
    private var playerRepository: PlayerRepository = mock(PlayerRepository::class.java)

    @Test
    fun make_Teams_with_same_Skillpoints() {
        //arrange
        val players = listOf(
                Player(name = "X", skill = 3.0),
                Player(name = "T", skill = 4.0),
                Player(name = "K", skill = 5.0),
                Player(name = "O", skill = 1.0),
                Player(name = "Y", skill = 2.0),
                Player(name = "A", skill = 4.0),
                Player(name = "B", skill = 4.0),
                Player(name = "C", skill = 5.0),
                Player(name = "D", skill = 1.0),
                Player(name = "E", skill = 1.0))

        val sut = TeamsBuilderService(playerRepository)
        val expectedAvgSkill = 15.0 / 5

        // act
        val match = sut.buildTeams(players)

        // assert
        assertThat(match.teams)
                .isNotEmpty
                .allSatisfy { team -> assertThat(team.avgSkill).isEqualTo(expectedAvgSkill) }
    }

    @Test
    fun make_Teams_with_same_Skillpoints_and_3_Players_per_Team() {
        //arrange
        val players = listOf(
                Player(name = "X", skill = 5.0),
                Player(name = "T", skill = 5.0),
                Player(name = "K", skill = 1.0),
                Player(name = "O", skill = 4.0),
                Player(name = "M", skill = 4.0),
                Player(name = "A", skill = 3.0), )
        val sut = TeamsBuilderService(playerRepository)
        val expectedTeamAvgSkill: Double = 11.0 / 3.0

        // act
        val match = sut.buildTeams(players)

        // assert
        assertThat(match.teams)
                .isNotEmpty
                .allSatisfy { team -> assertThat(team.avgSkill).isEqualTo(expectedTeamAvgSkill) }
    }

    @Test
    fun make_Teams_with_not_equal_Skillpoints_and_3_Players_per_Team() {
        //arrange
        val players = listOf(
                Player(name = "X", skill = 5.0),
                Player(name = "T", skill = 5.0),
                Player(name = "K", skill = 2.0),
                Player(name = "O", skill = 4.0),
                Player(name = "M", skill = 4.0),
                Player(name = "A", skill = 3.0), )
        val sut = TeamsBuilderService(playerRepository)
        val firstTeamAVGSkill = 12.0 / 3.0
        val secondTeamAVGSkill = 11.0 / 3.0

        // act
        val match = sut.buildTeams(players)

        // assert
        assertThat(match.teams)
                .isNotEmpty
                .anySatisfy { team -> assertThat(team.avgSkill).isEqualTo(firstTeamAVGSkill) }
                .anySatisfy { team -> assertThat(team.avgSkill).isEqualTo(secondTeamAVGSkill) }
    }

    @Test
    fun return_emptyList_if_players_size_split_is_not_equal() {
        //arrange
        val players = listOf(
                Player(name = "X", skill = 5.0),
                Player(name = "M", skill = 4.0),
                Player(name = "A", skill = 3.0), )
        val sut = TeamsBuilderService(playerRepository)

        // act
        val match = sut.buildTeams(players)

        // assert
        assertThat(match.teams).isEmpty()
    }
}