<template>
  <v-container class="mt-12">
    <v-responsive class="align-center text-center fill-height pa-0">
      <h1>Trage alle Namen, für neue Spieler sollten ebenfalls Skills eingegeben werden.</h1>
      <br>
      <v-row>
        <v-col>
          <v-row>
            <v-col cols="3">
              <h1> Team 1 </h1>
            </v-col>
            <v-col>
              <v-text-field label="Team Name" variant="outlined"></v-text-field>
            </v-col>
          </v-row>
          <v-text-field label="T1: Spieler 1" variant="outlined"></v-text-field>
          <v-text-field label="T1: Spieler 2" variant="outlined"></v-text-field>
          <v-text-field label="T1: Spieler 3" variant="outlined"></v-text-field>
          <v-text-field label="T1: Spieler 4" variant="outlined"></v-text-field>
          <v-text-field label="T1: Spieler 5" variant="outlined"></v-text-field>
        </v-col>
        <v-col>
          <v-row>
            <v-col cols="3">
              <h1> Team 2 </h1>
            </v-col>
            <v-col>
              <v-text-field label="Team Name" placeholder="Optional" variant="outlined"></v-text-field>
            </v-col>
          </v-row>
          <v-text-field label="T2: Spieler 1" variant="outlined"></v-text-field>
          <v-text-field label="T2: Spieler 2" variant="outlined"></v-text-field>
          <v-text-field label="T2: Spieler 3" variant="outlined"></v-text-field>
          <v-text-field label="T2: Spieler 4" variant="outlined"></v-text-field>
          <v-text-field label="T2: Spieler 5" variant="outlined"></v-text-field>
        </v-col>
      </v-row>
      <v-btn
        color="primary"
        min-width="228"
        size="x-large"
        @click="generiereTeams()"
      >
        <v-icon
          icon="mdi-account-group"
          size="large"
          start/>
        Teams generieren
      </v-btn>

      <v-row v-if="generatedTeams">
        <v-col>
          <ol>
            <li v-for="p in generatedTeams.teamA.player">
              {{ p.name }}
            </li>
          </ol>
        </v-col>
        <v-col>
          <ol>
            <li v-for="p in generatedTeams.teamB.player">
              {{ p.name }}
            </li>
          </ol>
        </v-col>
      </v-row>
      <v-row v-if="generatedTeams !== null && generatedTeams?.teamA.avgSkill == generatedTeams?.teamB.avgSkill">
        <v-col>
          <h1>Die Teams sind perfekt ausgeglichen!</h1>
        </v-col>
      </v-row>
    </v-responsive>
  </v-container>
</template>

<script setup lang="ts">

  import {TeamforgeService} from "@/teamforgeService";
  import {Teams} from "@/model/player";

  const teamforgeService = TeamforgeService.prototype

  let generatedTeams: Teams | null = null

  async function generiereTeams(){
    generatedTeams = teamforgeService.generateTeams(true)
    console.log(generatedTeams)
  }


</script>

