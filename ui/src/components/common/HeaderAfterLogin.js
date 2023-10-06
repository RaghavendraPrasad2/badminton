import logo from "../../images/logo.jpg";
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { useNavigate } from "react-router-dom";

const Header = (props) => {
  // Assume isLoggedIn is a state variable that tracks the user's login state
  const navigate = useNavigate();


  const handleLogout = () => {

    const confirmLogout = window.confirm("Are you sure you want to logout?");
    if (confirmLogout) {
      navigate('/');
    }
  };
  const handleGoHome = () =>{
    navigate('/home');
  }
  return (
    <>
      <header>
        <div className="header-logo">
          <img src={logo} alt="Logo" />
        </div>
        <div className="header-title">
          <h2>RALLY_RUSH</h2>
          {/* <p>Advarra Integrated Solutions enable the safe, ethical, compliant, and efficient development of life-changing therapies.</p> */}
        </div>
        <div className="headerContainer">
          
            <>
            < button className='HomeButton' onClick={handleGoHome}>Home</button>
           < button className='LogOutButton' onClick={handleLogout}>logout</button>
            </>
          
        </div>
      </header>
    </>
  );
}

export default Header;