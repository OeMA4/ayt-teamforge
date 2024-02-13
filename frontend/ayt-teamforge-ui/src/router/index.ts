/**
 * router/index.ts
 *
 * Automatic routes for `./src/pages/*.vue`
 */

// Composables
import { createRouter, createWebHistory } from 'vue-router/auto'
import PlayerRankings from "@/pages/PlayerRankings.vue";
import Home from "@/components/Home.vue";

const routes = [
  {
    path: "/playerRankings",
    name: "playerrankings",
    component: PlayerRankings,
  },
  {
    path: "/",
    name: "Home",
    component: Home,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
