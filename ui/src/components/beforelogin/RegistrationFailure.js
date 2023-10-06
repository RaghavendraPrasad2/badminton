import React from "react";
import "../../styles/RegistrationError.css";
import { useNavigate } from "react-router-dom";

const RegistrationFailure = () => {
    const navigate = useNavigate();
    const handleClick = () => {
        navigate("/");
      };
    return(
        <>
    <div className="content1">
        <div className="alert1">
            Something went wrong please try again, <p>Please try again</p>
        </div>
       <button class="buttonFail" onClick={handleClick} >Back</button> 
    </div>
        </>
    );
}

export default RegistrationFailure;