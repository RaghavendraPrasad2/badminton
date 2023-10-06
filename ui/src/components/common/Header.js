import logo from "../../images/logo.jpg";
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { useNavigate } from "react-router-dom";

const Header = (props) => {





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
        </div>
      </header>
    </>
  );
}

export default Header;