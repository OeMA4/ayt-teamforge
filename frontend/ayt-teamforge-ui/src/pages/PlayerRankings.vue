<template>
  <v-container class="mt-12">
    <v-responsive class="align-center text-center fill-height">
      <h1> Spieler Rangliste</h1>
      <v-data-table :items="items" :headers="headers"></v-data-table>
    </v-responsive>
  </v-container>
</template>

<script setup lang="ts">

const headers =  [
  {
    title: '#',
    align: 'center',
    sortable: true,
  },
  {
    title: 'Name',
    align: 'center',
    sortable: false,
    value: 'name',
  },

  {
    title: 'Anzahl der Siege',
    align: 'center',
    sortable: false,
    value: 'numOfWins',
  },  {
    title: 'Form',
    align: 'center',
    sortable: false,
    value: 'form',
  },
];
const items = [
  {
    name: 'Ömer',
    numOfWins: 10,
    form: 'good',
  },
  {
    name: 'Ömer',
    numOfWins: 10,
    form: 'good',
  },  {
    name: 'Ömer',
    numOfWins: 10,
    form: 'good',
  },  {
    name: 'Ömer',
    numOfWins: 10,
    form: 'good',
  },  {
    name: 'Ömer',
    numOfWins: 10,
    form: 'good',
  },
];

</script>

