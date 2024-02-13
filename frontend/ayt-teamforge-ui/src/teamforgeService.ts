import {Player} from "@/model/player";
import axios from "axios";


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
}
