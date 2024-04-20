package oemer.aytteamforge.service

import oemer.aytteamforge.controller.dto.`in`.PlayerDto
import oemer.aytteamforge.controller.dto.`in`.TeamsRequestDto
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
        val players = TeamsRequestDto(listOf(
                PlayerDto(name = "X", skill = 3.0),
                PlayerDto(name = "T", skill = 4.0),
                PlayerDto(name = "K", skill = 5.0),
                PlayerDto(name = "O", skill = 1.0),
                PlayerDto(name = "Y", skill = 2.0),
                PlayerDto(name = "A", skill = 4.0),
                PlayerDto(name = "B", skill = 4.0),
                PlayerDto(name = "C", skill = 5.0),
                PlayerDto(name = "D", skill = 1.0),
                PlayerDto(name = "E", skill = 1.0)))

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
    fun make_Teams_with_one_black_sheep() {
        //arrange
        val players = TeamsRequestDto(listOf(
                PlayerDto(name = "X", skill = 5.0),
                PlayerDto(name = "T", skill = 5.0),
                PlayerDto(name = "K", skill = 5.0),
                PlayerDto(name = "K", skill = 5.0),
                PlayerDto(name = "K", skill = 5.0),
                PlayerDto(name = "K", skill = 5.0),
                PlayerDto(name = "K", skill = 5.0),
                PlayerDto(name = "E", skill = 1.0)))

        val sut = TeamsBuilderService(playerRepository)
        val expectedAvgSkill1 = 20.0 / 4.0
        val expectedAvgSkill2 = 16.0 / 4.0

        // act
        val match = sut.buildTeams(players)

        // assert
        assertThat(match.teams)
                .isNotEmpty
                .anySatisfy { team -> assertThat(team.avgSkill).isEqualTo(expectedAvgSkill1) }
                .anySatisfy { team -> assertThat(team.avgSkill).isEqualTo(expectedAvgSkill2) }
    }

    @Test
    fun make_Teams_with_same_Skillpoints_and_3_Players_per_Team() {
        //arrange
        val players = TeamsRequestDto(listOf(
                PlayerDto(name = "X", skill = 5.0),
                PlayerDto(name = "T", skill = 5.0),
                PlayerDto(name = "K", skill = 1.0),
                PlayerDto(name = "O", skill = 4.0),
                PlayerDto(name = "M", skill = 4.0),
                PlayerDto(name = "A", skill = 3.0), ))
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
        val players = TeamsRequestDto(listOf(
                PlayerDto(name = "X", skill = 5.0),
                PlayerDto(name = "T", skill = 5.0),
                PlayerDto(name = "K", skill = 2.0),
                PlayerDto(name = "O", skill = 4.0),
                PlayerDto(name = "M", skill = 4.0),
                PlayerDto(name = "A", skill = 3.0), ))
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
        val players = TeamsRequestDto(listOf(
                PlayerDto(name = "X", skill = 5.0),
                PlayerDto(name = "M", skill = 4.0),
                PlayerDto(name = "A", skill = 3.0), ))
        val sut = TeamsBuilderService(playerRepository)

        // act
        val match = sut.buildTeams(players)

        // assert
        assertThat(match.teams).isEmpty()
    }

    @Test
    fun just_because_post_Is_not_working() {
        //arrange
        val players = TeamsRequestDto(listOf(
            PlayerDto(name = "Ömer", skill = 3.0),
            PlayerDto(name = "Büny", skill = 4.0),
            PlayerDto(name = "Enes", skill = 4.0),
            PlayerDto(name = "Sinan", skill = 6.0),
            PlayerDto(name = "Serkan", skill = 7.0),
            PlayerDto(name = "Ali", skill = 1.0),
            PlayerDto(name = "Mustafa", skill = 4.0),
            PlayerDto(name = "Fatih", skill = 3.0),
            PlayerDto(name = "Yusuf", skill = 3.0),
            PlayerDto(name = "Ibrahim Abi", skill = 3.0), ))
        val sut = TeamsBuilderService(playerRepository)

        // act
        val match = sut.buildTeams(players)
        println(match)

        // assert
        assertThat(match.teams).allSatisfy { team -> assertThat(team.avgSkill).isEqualTo(3.6) }
    }
}