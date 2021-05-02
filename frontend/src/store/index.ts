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
      login(state) {
          localStorage.setItem('user-token', 'Tarun');
          state.user = 'Tarun';
      }
  },
  getters: {
      isLoggedIn: state => state.user != '',
      username: state => state.user
  }
})
