import api from './apiService'
export const UserService = {
  async register (username:string, password:string):Promise<boolean> {
    
    const result:boolean = await api.post('/users/register', {
        username, password
    })
    console.log(result);
    console.log("wow");
    return result
  },

  async login (username:string, password:string):Promise<boolean> {
    
    const result:boolean = await api.post('/users/login', {
        username, password
    })
    console.log(result);
    return result
  },

  async updateLevel (username:string, level:string) {
    const result:boolean = await api.post('/users/updateLevel', {
      username, level
    })
    console.log(result);
    return result;
  },
  async level (username:string):Promise<number> {
    const result:number = await api.get('/users/level', {
      params: {
        username
      }
    });
    console.log(result);
    return result;
  }

}