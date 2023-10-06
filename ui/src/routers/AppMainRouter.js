import React , { useState } from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import ActualHome from "../components/beforelogin/ActualHome";
import HomeAfterSuccessfulLogin from "../components/afterlogin/HomeAfterSuccessfulLogin";
import RegistrationSuccessful from "../components/beforelogin/RegistrationSuccessful";
import RegistrationFailure from "../components/beforelogin/RegistrationFailure";
import SessionInvalidComponent from "../components/common/SessionInvalidComponent";
import RecentTournament from "../components/tournaments/RecentTournament";
import LeaguesComponent from "../components/tournaments/UpComing/LeaguesComponent";
import CreateScheduleManual from "../components/tournaments/UpComing/CreateScheduleManual";
import CreateScheduleRandom from "../components/tournaments/UpComing/CreateScheduleRandom";
import BeforeStartMatch from "../components/tournaments/OnGoing/BeforeStartMatch";
import ScoreCounter from "../components/tournaments/OnGoing/ScoreCounter";

const AppMainRouter = () => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    return(
        <>
            <Router>
                <Routes>
                    <Route path="/" element={<ActualHome isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn} />} />
                    <Route path="/home" element={<HomeAfterSuccessfulLogin isLoggedIn={isLoggedIn} />} />
                    <Route exact path="/registrationSuccess" element={<RegistrationSuccessful />} />
                    <Route exact path="/registrationFailed" element={<RegistrationFailure />}/>
                    <Route exact path="/sessionFailed" element={<SessionInvalidComponent />} />
                    <Route exact path="/recent-tournaments/:tournamentId" element={<RecentTournament />} />
                    <Route exact path="/upcoming-tournaments/:tournamentId" element={<LeaguesComponent />} />
                    <Route exact path="/ongoing-tournaments/:tournamentId" element={<BeforeStartMatch />} />
                    <Route exact path="/createScheduleManual/:tournamentId" element={<CreateScheduleManual />} />
                    <Route exact path="/createScheduleRandom/:tournamentId" element={<CreateScheduleRandom />} />
                    <Route exact path="/start-match/:identifier" element={<ScoreCounter />} />
                    <Route exact path="/ScoreCounter" element={<ScoreCounter />} />
                </Routes>
            </Router>
        </>
    );
}

export default AppMainRouter;