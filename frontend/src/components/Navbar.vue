<template>
  <div>
  <div id="nav">
      <div>
    <div class = "leftSide">
    <a><span class="header">Mia Lexi</span></a>
        </div>
    <div class="rightSide">
    <li v-if="state.loggedIn" class="username"><span>{{ state.username }} </span></li>
    <li v-else class="create"><a class="regLog"><router-link to="/register"><span><u>Create an account</u></span></router-link></a><u><a class="regLog"><router-link to="/login" class="regLog"><span class="loginSpan"><u>Log in</u></span></router-link></a></u></li>
    <li id="burger" :class="{'active': state.droppedDown}" @click="toggleDropdown">
        <button type="button" class="burger-button">
            <span class="burger-bar burger-bar--1"></span>
            <span class="burger-bar burger-bar--2"></span>
            <span class="burger-bar burger-bar--3"></span>
        </button>
    </li>
      <ul class="dropdown rightSide" v-if="state.droppedDown">
      <li><a><router-link to="/">One Word</router-link></a></li>
      <hr>
      <li><a><router-link to="/practice">Practice Mode</router-link></a></li>
      <hr>
      <li><a><router-link to="/censorship">Censorship</router-link></a></li>
      <hr>
      <a><router-link to="/about">About Us</router-link></a>
      <hr v-if="state.loggedIn">
      <li v-if="state.loggedIn"><a @click="logout">Log out</a></li>
    </ul>
    </div>
    </div>
  </div>
 
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive, computed } from 'vue';
import store from '@/store';
import router from '@/router';
export default defineComponent({
  name: 'Navbar',
  props: {
    closeDropdown: Boolean
  },
  setup(props) {
    const state = reactive({
      username: computed(() => store.getters.username),
      fullPage: false,
      droppedDown: false,
      loggedIn: computed(() => store.getters.isLoggedIn)
    });
    router.beforeEach((to, from) => {
      state.droppedDown = true;
    })
    const logout = () => {
      store.commit('logout');
      router.push('/login');
    }
    const toggleDropdown = () => {state.droppedDown = !state.droppedDown;};
    return { state, toggleDropdown, logout };
  }
});
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


.regLog {
  padding: 0rem !important;
  margin: 0 !important;
}

.leftSide {
  float: left;
}
.leftSide a {
  color: #fff;
}
.rightSide {
  float: right;
  height: 100%;
  width: 20%;
}
.rightSide li {
  display: inline-block;
}
#nav .dropdown .router-link-active{
  background-color: #c4c4c4;
}
#nav {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  padding: 1.5rem 2rem;
  margin: 0;
  text-decoration: none;
  width: 100%;
  height: 5%;
  font-size: 1.2rem;
  font-weight: bold;
}

#nav a {
  font-weight: bold;
  cursor: pointer;
  text-decoration: none;
  padding: 0.5rem;
  color: #fff;
  display: inline-block;
}
#nav .dropdown a:hover{
   background-color: #C4C4C4;
    transition: 200ms;
    transition-timing-function: ease-in;
}
hr {
  color: #892727 !important;
}

.dropdown {
  z-index: 4;
  background-color: #E9E6E6;
  color: #892727;
  text-decoration: none;
  list-style-type: none;
  padding: 0.5rem;
  padding-top: 0rem;
  margin-right: 1.2rem;
  width: 100%;
}
.dropdown a {
  color: #892727 !important;
}
.dropdown li {
  padding: 0.5rem;
}
    .burger-button {
        background-color: transparent;
        position: absolute;
        height: 1.5rem;
        top: 2rem;
        right: 6rem;
        width: 2.5rem;
        display: block;
        z-index: 99;
        border: 0;
        border-radius: 0;
        pointer-events: all;
        transition: transform .6s cubic-bezier(.165, .84, .44, 1);
    }
    #burger {
      padding: 0rem 1rem;
      margin-right: 2rem;
    }
    .burger-bar {
        position: absolute;
        background-color: #fff;
        right: 7px;
        left: 6px;
        height: 2px;
        width: auto;
        transition: transform .6s cubic-bezier(1, 0, 0, 1);
    }
    .burger-bar--1 {
        -webkit-transform: translateY(-6px);
        transform: translateY(-6px);
        top: 40%;
    }
    .burger-bar--3 {
        transform: translateY(6px);
        top: 60%;
    }
    #burger.active .burger-button {
        transform: rotate(-180deg);
    }
    #burger.active .burger-bar--1 {
        transform: rotate(45deg);
        top: 50%;
    }
    #burger.active .burger-bar--2 {
        opacity: 0;
    }
    #burger.active .burger-bar--3 {
        transform: rotate(-45deg);
        top: 50%;
    }
    .username {
      margin-top: 0.5rem;
    }
    .create {
      width: 50%;
      margin-top: -1rem;
    }
    .loginSpan {
      font-size: 0.8rem;
      padding: 0rem;
    }


</style>
