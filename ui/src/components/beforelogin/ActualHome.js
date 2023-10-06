import React from "react";
import LoginForm from "../forms/LoginForm";
import RegistrationForm from "../forms/RegistrationForm";
import Header from "../common/Header";
import Footer from "../common/Footer";
import { Link } from "react-router-dom";

const ActualHome = ({ isLoggedIn, setIsLoggedIn }) => {
   
    const closeLoginForm = () => {
        const login = document.getElementById("loginid");
        login.style.display = "none";
    }

    const closeSignupForm = () => {
        const signup = document.getElementById("signupid");
        signup.style.display = "none";
    }

    const showLoginForm = () =>
    {
    const login = document.getElementById("loginid");
    console.log(isLoggedIn  + "" + "in head actual homeS");
    login.style.display = "block";
    }

    const showSignupForm = () =>
    {
    const signup = document.getElementById("signupid");
    signup.style.display = "block";
    }

    return(
        <>
            <Header isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn} />

            <div class="main-content">
                <h1 class="text">Badminton&nbsp;Tournament&nbsp;Management&nbsp;System</h1>
                <div class="buttons">
                    <button  onClick={showLoginForm}>Login</button>
                    <button onClick={showSignupForm}>Signup</button>
                    <Link to={"/ScoreCounter"}>jnijl</Link>
                </div>
            </div>
        
            <LoginForm
  closeLogin={closeLoginForm}
  closeRegister={closeSignupForm}
  openLogin={showLoginForm}
  openRegister={showSignupForm}
  isLoggedIn={true}// Pass setIsLoggedIn as a prop
/>
            <RegistrationForm 
             closeRegister={closeSignupForm} 
             closeLogin={closeLoginForm}
             openLogin={showLoginForm}
             openRegister={showSignupForm}
            />
            <Footer />
          
        </>
    );
}

export default ActualHome;