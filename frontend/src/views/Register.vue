<template>
    <div class="full">
  <div class="login">
    <h1 class="header main-header">Mia Lexi</h1>
    <h2>New User?</h2>
    <li>
        <a><router-link to="/login"><u>Already have an account? Log in.</u></router-link></a>
        </li>
        <input id="username" v-model="state.username" placeholder="Username">
        <br>
        <input id="password" type="password" v-model="state.password" placeholder="Password">
        <br>
        <button class="button button-login" type="submit" @click="goRegister">Create Account</button>
    <div v-if="state.usernameExists" id="wrong">
        <p>Username already exists</p>
    </div>
    <div v-if="state.usernameWrong" id="wrong">
        <p>Username needs to be at least 4 letters</p>
    </div>
    <div v-if="state.passwordWrong" id="wrong">
        <p>Password needs to be at least 6 letters</p>
    </div>
        
  </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive, onMounted } from 'vue';
import router from '@/router';
import store from '@/store';
import { UserService } from '@/services';
export default defineComponent({
  name: 'Login',
  setup() {
      const state = reactive({
          username: '',
          password: '',
          usernameExists: false,
          usernameWrong: false,
          passwordWrong: false
      })
      const goRegister = async () => {
          state.usernameExists = false;
          state.usernameWrong = false;
          state.passwordWrong = false;
          if (state.username.length < 4) {
              state.usernameWrong = true;
          }
          else if (state.password.length < 6) {
              state.passwordWrong = true;
          }
          else {
              let registered:boolean = await UserService.register(state.username, state.password);
              if (registered) {
                  router.push('/login');
              }
              else {
                    state.usernameExists = true;
              }
          }
      }
      onMounted(() => {
          if (store.getters.isLoggedIn) {
              router.push('/');
          }
      });
      return { goRegister, state };
  }
});
</script>
<style scoped>
    input {
        padding: 1.2rem;
        margin: 5px;
        font-size: 1.3rem;
    }
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
