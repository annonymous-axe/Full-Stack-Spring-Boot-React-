// Layout.js
import Header from "./HeaderComponent";
import Footer from "./FooterComponent";
import { Outlet } from "react-router-dom";

export default function AppLayout() {
    return (
        <>
            <Header />
            <Outlet />
            <Footer />
        </>
    );
}
