<template>
  <v-container class="fill-height">
    <v-responsive class="align-center text-center fill-height">
      <v-row class="d-flex align-center justify-center">
        <v-col cols="auto">
          <router-link to="/playerSelection"
                       custom
                       v-slot="{ navigate }">
          <v-btn
            color="primary"
            min-width="228"
            @click="navigate"
            rel="noopener noreferrer"
            size="x-large">
            <v-icon
              icon="mdi-speedometer"
              size="large"
              start/>
            Teams erstellen!
          </v-btn>
          </router-link>
        </v-col>

        <v-col cols="auto">
          <router-link to="/playerRankings"
                       custom
                       v-slot="{ navigate }">
          <v-btn
            color="secondary"
            min-width="228"
            rel="noopener noreferrer"
            @click="navigate"
            role="link"
            size="x-large">
            <v-icon
              icon="mdi-speedometer"
              size="large"
              start/>
            Spiel Historie anschauen
          </v-btn>
          </router-link>
        </v-col>
      </v-row>
    </v-responsive>
  </v-container>
</template>

<script setup lang="ts">
</script>
