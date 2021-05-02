<template>
  <div>
  <div v-if ="state.isLoggedIn">
  <Navbar/>
  </div>
  <div class = "router-view">
  <router-view/>
  </div>
  </div>
</template>
<script lang="ts">
import { defineComponent, reactive, onMounted, computed, onUpdated } from 'vue';
import store from '@/store';
import router from '@/router';
import Navbar from '@/components/Navbar.vue';
export default defineComponent({
  name: 'App',
  setup() {
    const state = reactive({
      isLoggedIn: computed(() => store.getters.isLoggedIn),
      username: computed(() => store.getters.username),
      
    })
    onMounted(
      () => {
         if (!state.isLoggedIn) {
           router.push('/login');
         }
      }
      );
      return { state };
  },
  components: {
    Navbar
  }
});
</script>
<style>
@import url('https://fonts.googleapis.com/css2?family=Solway&display=swap');
#app {
  
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #fff;
}
* {
 font-family: 'Solway', sans-serif;
}
.router-view {
  margin-top: 15vh;
}
body {
  background-color: #892727;
}
</style>
