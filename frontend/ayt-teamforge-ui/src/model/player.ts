export interface Player {
  id: number;
  name: string;
  numOfWins: number;
  isSelected: boolean;
}

export interface TeamPlayer {
  name: string,
  skill: number
}

export interface Team {
  player: TeamPlayer[]
  avgSkill: number
}

export interface Teams {
  teamA: Team
  teamB: Team
}
