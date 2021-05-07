<template>
  <div>
    <div v-if="pastLogin">
  <Navbar/>
  </div>
  <div class = "router-view">
  <router-view/>
  </div>
  </div>
</template>
<script lang="ts">
import { defineComponent, reactive, onMounted, computed, onUpdated } from 'vue';
import { useRoute } from 'vue-router';
import Navbar from '@/components/Navbar.vue';
export default defineComponent({
  name: 'App',
  setup() {
    const pastLogin = computed(() => {
      let name = useRoute().name;
      if (name != null) {
        console.log(name);
        return !name.toString().includes("Register") && !name.toString().includes("Login")
      }
      return false;
    })
    return { pastLogin };
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
  margin-top: 7rem;
}
body {
  background-color: #892727;
  overflow-x: hidden;
  overflow-y: scroll;
}
</style>
