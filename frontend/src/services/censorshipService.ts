import api from './apiService'
import store from '@/store'
export const CensorshipService = {
  async add (username:string, censoredWord:string):Promise<boolean> {
    
    const result:boolean = await api.post('/censorship/add', {
        username, censoredWord
    })
    return result
  },

  async remove (username:string, censoredWord:string):Promise<boolean> {
    
    const result:number = await api.post('/censorship/remove', {
        username, censoredWord
    })
    console.log(result);
    return result > 0;
  },

  async getList (username:string):Promise<string[]> {
      const result:string[] = await api.get(`/censorship/${username}`);
      return result;
  },

  async addListToStore() {
    if (store.getters.censoredWords.length === 0) {
        const censoredList:string[] = await CensorshipService.getList(store.getters.username);
        store.commit('setCensoredWords', censoredList);
      }
  }
}