export interface Player {
  name: string,
  numOfWins: number
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
