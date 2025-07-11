import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Error from "./Error";
import SignUp from "./SignUp";
import Login from "./Login";
import AuthProvider from "../security_context/AuthContext";
import Home from "./Home";

export default function TempoTrackApp() {
    return (
        <div className="TodoApp">

            <AuthProvider>
                <BrowserRouter>
                    <Routes>
                        {/* Routes without Header/Footer */}
                        <Route path="/" element={<SignUp />} />
                        <Route path="/login" element={<Login />} />

                        <Route path="/home" element={<Home />} />

                        {/* Routes with Header/Footer */}
                        {/* <Route element={<AppLayout />}>
                            <Route
                                path="/home/:username"
                                element={
                                    <AuthenticateRoute>
                                        <Welcome />
                                    </AuthenticateRoute>
                                }
                            />
                            <Route
                                path="/todos"
                                element={
                                    <AuthenticateRoute>
                                        <TodoList />
                                    </AuthenticateRoute>
                                }
                            />
                            <Route
                                path="/logout"
                                element={
                                    <AuthenticateRoute>
                                        <Logout />
                                    </AuthenticateRoute>
                                }
                            />
                            <Route
                                path="/todo/:id"
                                element={
                                    <AuthenticateRoute>
                                        <TodoDetail />
                                    </AuthenticateRoute>
                                }
                            />
                        </Route> */}

                        {/* Fallback */}
                        <Route path="*" element={<Error />} />
                    </Routes>
                </BrowserRouter>

            </AuthProvider>

        </div>
    );
}
