<template>
  <v-container class="mt-12">
    <v-responsive class="align-center text-center fill-height pa-0">
      <h1>Trage alle Namen, für neue Spieler sollten ebenfalls Skills eingegeben werden.</h1>
      <br>
      <v-row>
        <v-col>
          <v-text-field label="Spieler 1" variant="outlined"></v-text-field>
          <v-text-field label="Spieler 2" variant="outlined"></v-text-field>
          <v-text-field label="Spieler 3" variant="outlined"></v-text-field>
          <v-text-field label="Spieler 4" variant="outlined"></v-text-field>
          <v-text-field label="Spieler 5" variant="outlined"></v-text-field>
          <v-text-field label="Spieler 6" variant="outlined"></v-text-field>
          <v-text-field label="Spieler 7" variant="outlined"></v-text-field>
          <v-text-field label="Spieler 8" variant="outlined"></v-text-field>
          <v-text-field label="Spieler 9" variant="outlined"></v-text-field>
          <v-text-field label="Spieler 10" variant="outlined"></v-text-field>
        </v-col>
        <v-col>
          <h1> Alle vorhandenen und gespeicherten Spieler mit Skills!</h1>
          <div v-for="player in availablePlayers" :key="player.id" class="d-flex">
            <v-col cols="7">
              <v-card :text="player.name" class="mt-5 mr-8"></v-card>
            </v-col>
            <v-btn icon="mdi-plus" color="green" class="mt-8" @click="addPlayer(player)"></v-btn>
          </div>
        </v-col>
      </v-row>
      <v-btn
        color="primary"
        min-width="228"
        size="x-large"
        @click="generateTeams()"
      >
        <v-icon
          icon="mdi-account-group"
          size="large"
          start/>
        Teams generieren
      </v-btn>

      <v-row class="mt-12" v-if="generatedTeams">
        <v-col>
          <ul>
            <li v-for="player in generatedTeams.teamA.player">
              {{ player.name }}
            </li>
          </ul>
        </v-col>
        <v-col>
          <ol>
            <li v-for="player in generatedTeams.teamB.player">
              {{ player.name }}
            </li>
          </ol>
        </v-col>
      </v-row>
      <v-row v-if="generatedTeams.teamA.avgSkill == generatedTeams.teamB.avgSkill">
        <v-col>
          <h1>Die Teams sind perfekt ausgeglichen!</h1>
        </v-col>
      </v-row>
    </v-responsive>
  </v-container>
</template>

<script lang="ts">

  import {TeamforgeService} from "@/teamforgeService";
  import {Player, TeamPlayer, Teams} from "@/model/player";
  import {defineComponent, ref} from "vue";
  import {pl} from "vuetify/locale";

  export default defineComponent({
    name: 'PlayerSelection',
    computed: {
      pl() {
        return pl
      }
    },
    setup() {
      const availablePlayers = ref<Player[]>([]);
      const selectedPlayers = ref<Player[]>([]);
      let generatedTeams =  ref<Teams>({
        teamA: {player: [], avgSkill: 0},
        teamB: {player: [], avgSkill: 0}});

      const fetchPlayers = async () => {
        // Simuliere den Backend-Aufruf
        const response = await TeamforgeService.prototype.loadPlayerRankings();
        availablePlayers.value = [
          {id: 0, isSelected: false, name: "Ahmet", numOfWins: 0},
          {id: 1, isSelected: false, name: "Mahmut", numOfWins: 0},
          {id: 2, isSelected: false, name: "Ömer", numOfWins: 0},
          {id: 3, isSelected: false, name: "Ali", numOfWins: 0},
          {id: 4, isSelected: false, name: "Mustafa", numOfWins: 0},
          {id: 5, isSelected: false, name: "Enes", numOfWins: 0},
          {id: 6, isSelected: false, name: "Koray", numOfWins: 0},
          {id: 7, isSelected: false, name: "Muhammed", numOfWins: 0},
        ]; //response.data;
      };

      const addPlayer = (player: Player) => {
        if (player.isSelected) {
          const selectedPlayer: Player = {
            id: player.id,
            name: player.name,
            isSelected: false,
            numOfWins: 0
          };
          selectedPlayers.value.push(selectedPlayer);
          availablePlayers.value = availablePlayers.value.filter(p => p.id !== player.id);
        } else {
          selectedPlayers.value = selectedPlayers.value.filter(p => p.id !== player.id);
          availablePlayers.value.push(player);
        }
      };

      const removePlayer = (index: number) => {
        const player = selectedPlayers.value[index];
        availablePlayers.value.push(player);
        selectedPlayers.value.splice(index, 1);
      };

      const generateTeams =  async () => {
        generatedTeams.value = TeamforgeService.prototype.generateTeams(true);
      };

      fetchPlayers();
      generateTeams();

      return {
        availablePlayers,
        selectedPlayers,
        generatedTeams,
        generateTeams,
        addPlayer,
        removePlayer,
      };
    },
  });





</script>

