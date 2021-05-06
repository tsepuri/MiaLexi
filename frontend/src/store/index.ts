import { createStore } from 'vuex'
import { UserService } from "@/services";
// import { postService } from "@/services";
export default createStore({
  state: {
      user: localStorage.getItem('user-token') || ''
  },
  mutations: {
      logout(state) {
          state.user = '';
          localStorage.removeItem('user-token');
      },
      login(state, user) {
          localStorage.setItem('user-token', user);
          state.user = user;
      }
  },
  getters: {
      isLoggedIn: state.user != '',
      username: state.user
  }
})
