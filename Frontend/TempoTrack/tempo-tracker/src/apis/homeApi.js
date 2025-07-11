import { baseUrl } from "./baseApi";

export const homeApi = () => { 
    return baseUrl.get("/home")
}