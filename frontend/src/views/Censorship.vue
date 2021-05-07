<template>
  <div class="home">
    <h2 class="head"><u>Censor Hub</u></h2> 
    <br>
    <br>
    <div v-if="state.passwordGiven || !state.user" class="censorToggleDiv">
      <h1 class="censor-word">Censor  <input type="checkbox" v-model="state.checked" class="checkbox" @change="censorToggle" /></h1>
      </div>
    <div v-if="state.user">
      <div v-if="state.passwordGiven">
        <ul class="page">
        <li class="list-head">Censored words added by user</li>
        <li class="list" @click="wordPressed(index)" :class="{ clicked: state.activeIndex === index }" v-for="(word, index) in state.censoredList" :key="index">{{ word }}</li>
        </ul>
        <div v-if="state.inputting">
          <input v-model="state.newWord" type="text"/><br><button class="button button-two" @click="addNewWord">Submit</button><button class="button button-two" @click="state.inputting = false">Cancel</button>
          </div>
        <div v-else><button class="button button-two" @click="state.inputting = true">Add</button><button class="button button-two" :disabled="state.activeIndex === -1" @click="tryDelete">Delete</button></div>
        <div v-if="!state.addWork" class="red">Could not add this word</div>
        <div v-if="!state.removeWork" class="red">Could not remove this word</div>
      </div>
      <div v-else>
        <h2>Enter Password to access this page</h2>
        <input id="password" type="password" v-model="state.password" placeholder="Password">
        <br>
        <button class="button button-two" @click="passwordInput">Access</button>
        <div v-if="state.wrongPassword" class="red">Wrong password</div>
        </div>
      </div>
      <div v-else id="logInToAdd">
        <p>Log in to add custom censored words</p>
        </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive, computed } from 'vue';
import { CensorshipService, UserService } from '@/services';
import store from '../store';
export default defineComponent({
  name: 'Censorship',
  setup() {
    const state = reactive({
      censoredList: computed(() => store.getters.censoredWords),
      user: store.getters.isLoggedIn,
      passwordGiven: false,
      wrongPassword: false,
      password: '',
      inputting: false,
      newWord: '',
      addWork: true,
      removeWork: true,
      activeIndex: -1,
      checked: computed(() => store.getters.censorWords)
    })
    const passwordInput = async() => {
      state.passwordGiven = await UserService.login(store.getters.username, state.password)
      if (state.passwordGiven) {
        await CensorshipService.addListToStore();
      }
      else {
        state.wrongPassword = true;
      }
    }
    const addNewWord = async() => {
      state.removeWork = true;
      state.addWork = await CensorshipService.add(store.getters.username, state.newWord);
      console.log(state.addWork);
      if (state.addWork) {
        console.log("here");
        store.commit('addCensoredWord', state.newWord);
        state.newWord = '';
        state.inputting = false;
      }
    }
    const tryDelete = async() => {
      state.addWork = true;
      if (state.activeIndex !== -1) {
        let word = state.censoredList[state.activeIndex]
        console.log(word);
        state.removeWork = await CensorshipService.remove(store.getters.username, word);
        state.activeIndex = -1;
        store.commit('removeCensoredWord', word);
      }
      else {
        state.removeWork = false;
      }
    }
    const wordPressed = (index:number) => {
      console.log(index);
      if (state.activeIndex === index) {
        state.activeIndex = -1;
      }
      else {
        state.activeIndex = index;
      }
    }
    const censorToggle = () => {
      store.commit('toggleCensor')
      };
    
    return { state, passwordInput, censorToggle, addNewWord, wordPressed, tryDelete };
  },
});
</script>
<style scoped type="text/css">

ul {
  list-style: none;
}
li {
  text-decoration: none;
  padding: 0.2rem;
}
.head {
  margin-left: 0;
  float: left;
  margin-top: 0;
}
.home {
  padding: 2rem;
  background-color: #e5e5e5;
  color: #000;
  padding-bottom: 10rem;
}
.censor-word {
  font-size: 3rem;
}

#logInToAdd {
  margin: auto;
  font-size: 1.2rem;
  padding: 2rem;
}


</style>
