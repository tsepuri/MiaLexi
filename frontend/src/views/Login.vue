<template>
    <div class="full">
  <div class="login">
    <h1 class="header main-header">Mia Lexi</h1>
    <h2>Login</h2>
        <input id="username" v-model="state.username" placeholder="Username">
        <br>
        <input id="password" type="password" v-model="state.password" placeholder="Password">
        <br>
        <button class="button button-login" type="submit" @click="checkLogin">Log in</button>
    <div v-if="state.wrongInfo" id="wrong">
        <p>Wrong username or password try again</p>
    </div>
        <li>
        <a><router-link to="/register"><u>Don't have an account? Create one.</u></router-link></a>
        </li>
        <li>
        <a><router-link to="/"><u>Continue without logging in.</u></router-link></a>
        </li>
  </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive, onMounted } from 'vue';
import router from '@/router';
import store from '@/store';
import { UserService } from "@/services";
export default defineComponent({
  name: 'Login',
  setup() {
      const state = reactive({
          wrongInfo: false,
          username: '',
          password: ''
      })
      const checkLogin = async () => {
          let loggedIn:boolean = await UserService.login(state.username, state.password);
          if (loggedIn) {
                store.commit('login', state.username);
                router.push('/'); 
          }
          else {
              state.wrongInfo = true;
          }
      }
      onMounted(() => {
          if (store.getters.isLoggedIn) {
              router.push('/');
          }
      });
      return { checkLogin, state };
  }
});
</script>
<style scoped>
    #wrong {
        color: red;
    }
    form {
        width: 100%;
        padding: 2rem;
        align-items: center;
        color: #fff;
    }
    .button-login {
        font-size: 1.3rem;
        margin: 1rem;
    }
    .main-header {
        font-size: 3rem;
        width: 50%;
        margin: auto;
    }
    .login {
        display: inline-block;
        align-items: center;   
    }
    .login a {
        color: #fff;
        
    }
    .login li {
        list-style-type: none;
        font-size: 1.2rem;
        padding: 0.5rem;
    }
    .full {
        align-items: center;
        display: inline-block;
    }
    
</style>
