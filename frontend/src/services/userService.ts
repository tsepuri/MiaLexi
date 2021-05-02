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
  }

}
