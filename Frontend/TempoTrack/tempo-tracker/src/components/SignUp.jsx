import {Box, Button, Grid, TextField} from '@mui/material';
import { useState } from 'react';
import { Link } from 'react-router-dom';
import { savedUserApi } from '../apis/saveApi';
import { useNavigate } from 'react-router-dom';

export default function SignUp(){

    const [username, setUsername] = useState()
    const [password, setPassword] = useState()
    const [fullName, setFullName] = useState()

    const navigate = useNavigate()

    function handleFormData(e){

        if(e.target.id === 'fullname') setFullName(e.target.value)
        if(e.target.id === 'username') setUsername(e.target.value)
        if(e.target.id === 'password') setPassword(e.target.value)

    }

    function savedUser(){

        savedUserApi({fullName, username, password}).then(
            response => {
                if(response.status === 201){
                    navigate('/login')
                }
            }
        ).catch(error => {
            console.log(error);
            
        })
        
    }

    return (

        <Grid container spacing={2}>

            <Box component="section" className="signup container">
                    <div className="row">
                        <TextField id="fullname" onChange={handleFormData} label="Full Name" variant="outlined" />                
                    </div>
                    <br />
                    <div className="row">
                        <TextField id="username" onChange={handleFormData} label="username" variant="outlined" />                
                    </div>
                    <br />
                    <div className="row">
                        <TextField type='password' onChange={handleFormData} id="password" label="password" variant="outlined" />                
                    </div>

                    <br />

                    <Button onClick={savedUser} variant='outlined'>Sign Up</Button>

                <hr />

                <Link to="/login" underline="hover">
                {"Already have an account?"}
                </Link>            

            </Box>
        </Grid>
    )
}