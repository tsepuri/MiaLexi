import { createStore } from 'vuex'
import { UserService } from "@/services";
// import { postService } from "@/services";
export default createStore({
  state: {
      user: localStorage.getItem('user-token') || '',
      censoredWords: [''],
      censorWords: false
  },
  mutations: {
      logout(state) {
          state.user = '';
          localStorage.removeItem('user-token');
      },
      login(state, user) {
          localStorage.setItem('user-token', user);
          state.user = user;
      },
      setCensoredWords(state, censoredWords) {
          state.censoredWords = censoredWords;
      },
      addCensoredWord(state, censoredWord) {
        console.log(censoredWord);
        state.censoredWords.push(censoredWord);
      },
      removeCensoredWord(state, censoredWord) {
        const index = state.censoredWords.indexOf(censoredWord);
        if (index > -1) {
            console.log("remove");
            state.censoredWords.splice(index, 1);
        }
    },
        toggleCensor(state) {
            state.censorWords = !state.censorWords;
        }
  },
  getters: {
      isLoggedIn: state => state.user != '',
      username: state => state.user,
      censoredWords: state => state.censoredWords,
      censorWords: state => state.censorWords
  }
})
