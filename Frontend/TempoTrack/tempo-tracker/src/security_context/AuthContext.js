import { baseUrl } from "../apis/baseApi";
import {homeApi} from "../apis/homeApi";
import { authentication } from "../apis/loginApi";

const {createContext, useState} = require('react');

//create context
export const AuthContext = createContext();

export default function AuthProvider({children}){

    const [token, setToken] = useState()
    const [fullName, setFullname] = useState()

    async function login(user){

        try{

            const response = await authentication(user);

            if(response.status == 202){

                const authToken = "Bearer "+response.data

                setToken(authToken)

                baseUrl.interceptors.request.use(
                    (config) => {
                        config.headers.Authorization = authToken
                        return config
                    }
                )

                homeApi().then((response) => {
                    setFullname(response.data)
                })

                return true;
            }

        }catch(error){
            console.log(error)

            return false
        }

    }

    function logout(){
        setToken(null);
        setFullname(null);
    }

    return(
        <AuthContext.Provider value={ {login, logout, token, fullName} }>
            {children}
        </AuthContext.Provider>
    )    

}