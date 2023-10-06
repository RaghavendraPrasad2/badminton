import React, { useState,useEffect } from "react";

import { getCookie } from "../../afterlogin/HomeAfterSuccessfulLogin";
import { useNavigate } from "react-router-dom";
import manual from  '../../../images/manual.png'
import random from '../../../images/random.png'
import '../../../styles/SchedulerStyle.css'



const CreateSchedule = ({onClose, tournamentId}) => {
      // Assume isLoggedIn is a state variable that tracks the user's login state
  const navigate = useNavigate();

  

  const handleRandom = () => {
    // Implement the logout logic here (e.g., clear user session)
    navigate('/createScheduleRandom/' + tournamentId);  
  };
  const handleManual = () =>{
    navigate('/createScheduleManual/' + tournamentId); 
  }
    return(
        <>
          <div className="overlay-scheduler">
            <div className="overlay-content-scheduler">
              <button className='XBtn' onClick={onClose}>&times;</button> <br></br><br></br>
              <div className="Schedulercard" onClick={handleRandom}>
                <img className='SchedulerImage' src={random} alt="playersimg" />
                <div className="activity-details">
                  <h3>Random</h3>
                </div>
              </div>
              <div className="Schedulercard manualCard" onClick={handleManual}>
                <img className='SchedulerImage' src={manual} alt="playersimg" />
                <div className="activity-details">
                  <h3>Manual</h3>
                </div>
              </div>
            </div>
          </div>
        </>
    )
}
export default CreateSchedule;