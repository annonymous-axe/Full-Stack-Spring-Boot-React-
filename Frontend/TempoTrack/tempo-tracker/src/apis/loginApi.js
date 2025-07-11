import { baseUrl } from "./baseApi";

export const authentication = (user) => {
    return baseUrl.post('/loginUser', user)   
}