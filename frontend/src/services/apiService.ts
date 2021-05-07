import axios from 'axios'
const api = axios.create({
  baseURL: 'http://localhost:8080',
  headers: {
    withCredentials: false,
    headers: {
      'Access-Control-Allow-Origin': 'http://localhost:8081'
    }
  }
});
api.interceptors.response.use(
  res => {
    /*
    if("Error" in res.data){
      throw new Error(res.data);
    }
    */
    return res.data;
  }
)
export default api;