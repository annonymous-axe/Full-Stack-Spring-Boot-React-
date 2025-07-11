import {Box, Button, Grid, TextField} from '@mui/material';
import { useContext, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { AuthContext } from '../security_context/AuthContext';
import { useNavigate } from 'react-router-dom';

export default function Login(){

    const navigate = useNavigate()

    const context = useContext(AuthContext)

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    function handleFormData(e){

        if(e.target.id === 'username') setUsername(e.target.value);
        if(e.target.id === 'password') setPassword(e.target.value);
        
    }

    async function submitFormData(){

        if(await context.login({username, password})){
            navigate('/home')
            console.log('success');
        }else{
            console.log('fail');
        }


    }

    return (

        <Grid container spacing={2}>

            <Box component="section" className="signup container">

                    <div className="row">
                        <TextField id="username" onChange={handleFormData} label="username" variant="outlined" />                
                    </div>
                    <br />
                    <div className="row">
                        <TextField type='password' id="password" onChange={handleFormData} label="password" variant="outlined" />                
                    </div>

                    <br />

                    <Button onClick={submitFormData} variant='outlined'>Login</Button>

                <hr />

                <Link to="/" underline="hover">
                {"Don't have an account?"}
                </Link>            

            </Box>
        </Grid>
    )
}