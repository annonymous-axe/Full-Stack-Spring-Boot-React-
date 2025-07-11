import { Divider, Grid } from "@mui/material";
import { AuthContext } from "../security_context/AuthContext";
import { useContext } from "react";

export default function Home(){

    const context = useContext(AuthContext)
    

    return (
        <Grid>

            Welocme,

            <Divider />

            Username : {context.fullName}

        </Grid>
    );
}