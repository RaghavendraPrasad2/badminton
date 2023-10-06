import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import HeaderAfterLogin from "../common/HeaderAfterLogin";
import Footer from "../common/Footer";
import LeagueForm from "../forms/LeagueForm";
import ActivitiesComponent from "./ActivitiesComponent";

export const getCookie = () => {
  const cookies = document.cookie.split(";");
  for (let i = 0; i < cookies.length; i++) {
      const cookie = cookies[i].trim();
      if (cookie.startsWith('sessionKey=')) {
        return cookie.substring('sessionKey'.length + 1);
      }
    }
    return null;
}

const HomeAfterSuccessfulLogin = () => {

    const navigate = useNavigate();

    const [tournaments, setTournaments] = useState({
        onGoingTournaments: [],
        upcomingTournaments: [],
        recentTournaments: [],
    });

  //   
  useEffect(() => {
    const apiUrl = 'http://localhost:8080/getLeagues';
    const requestOptions = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'sessionId': getCookie()
        },
    };
    fetch(apiUrl, requestOptions)
        .then((response) => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then((data) => {
            setTournaments(prevTournaments => ({
                ...prevTournaments,
                onGoingTournaments: data.onGoingTournaments,
                upcomingTournaments: data.upcomingTournaments,
                recentTournaments: data.recentTournaments,
            }));
        })
        .catch((error) => {
            console.log("Error: ", error);
            navigate("/sessionFailed");
        });
    }, []);

    const closeLeagueForm = () => {
        const league = document.getElementById("signupid");
        league.style.display = "none";
    }

    const showLeagueForm = () => {
        const league = document.getElementById("signupid");
        league.style.display = "block";
    }


    return(
        <>
            <HeaderAfterLogin />
            <main>
                <div style={{marginLeft: '80%' }} className="create-league-container">
                    <button onClick={showLeagueForm} class="btn btn-primary add-league-btn">Create League</button>
                </div>
                <div class="activities-container" id="activities-container">
                    {console.log(tournaments)}
                    <ActivitiesComponent tournaments={tournaments}/>
                </div>
                <LeagueForm closeLeagueForm={closeLeagueForm}/>
            </main>
            {/* <Footer />   */}
        </>
    );
}

export default HomeAfterSuccessfulLogin;