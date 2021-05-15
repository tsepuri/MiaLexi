import api from './apiService'
export const UserService = {
  async register (username:string, password:string):Promise<boolean> {
    
    const result:boolean = await api.post('/users/register', {
        username, password
    })
    return result
  },

  async login (username:string, password:string):Promise<boolean> {
    
    const result:boolean = await api.post('/users/login', {
        username, password
    })
    return result
  },

  async updateLevel (username:string, practiceLevel:string) {
    const result:boolean = await api.post('/users/updateLevel', {
      username, practiceLevel
    })
    return result;
  },
  async level (username:string):Promise<number> {
    const result:number = await api.get('/users/level', {
      params: {
        username
      }
    });
    return result;
  }

}