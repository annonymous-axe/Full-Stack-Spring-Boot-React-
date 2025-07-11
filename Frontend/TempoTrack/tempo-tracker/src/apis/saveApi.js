import { baseUrl } from './baseApi'

export const savedUserApi = (user) => {
    return baseUrl.post('/saveUser', user)
}