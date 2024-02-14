import {Player, Team, Teams} from "@/model/player";
import axios from "axios";

const teamA: Team = {
  player: [
    { name: "Test0", skill: 2.0},
    { name: "Test1", skill: 3.0},
    { name: "Test2", skill: 4.0},
    { name: "Test3", skill: 5.0},
    { name: "Test4", skill: 6.0},
  ],
  avgSkill: 2.0
}
const teamB: Team = {
  player: [
    { name: "ATest0", skill: 2.0},
    { name: "ATest1", skill: 3.0},
    { name: "ATest2", skill: 4.0},
    { name: "ATest3", skill: 5.0},
    { name: "ATest4", skill: 6.0},
  ],
  avgSkill: 4.0
}
const mockTeams: Teams = { teamB: teamB, teamA: teamA }

export class TeamforgeService {

  public async loadPlayerRankings(): Promise<Player[]> {
    const url = "localhost:8080"
    try {
      const response = await axios.get(url)
      return response.data
    } catch (err) {
      console.log(err);
    }
    return [];
  }

  public generateTeams(mock: boolean): Teams {
    const url = "localhost:8080"

    if (mock) {
      return mockTeams
    }

    try {
      const response = axios.get(url)
      return response as any
    } catch (err) {
      console.log(err);
    }
    return mockTeams;
  }



}
