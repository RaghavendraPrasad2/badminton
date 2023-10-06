import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";
import { getCookie } from "../afterlogin/HomeAfterSuccessfulLogin";
import '../../styles/RecentTournamentComponent.css';
import HeaderAfterLogin from "../common/HeaderAfterLogin"
import Footer from "../common/Footer";

const RecentTournament = () => {
    const { tournamentId } = useParams();
    const navigate = useNavigate();
    const [tournamentData, setTournamentData] = useState(null);

    useEffect(() => {
        const apiUrl = `http://localhost:8080/getRecentTournamentData/${tournamentId}`;
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
                setTournamentData(data);
            })
            .catch((error) => {
                console.log("Error: ", error);
                navigate("/sessionFailed");
            });
    }, [tournamentId]);

    return (
        <div>
            <HeaderAfterLogin/>
            <h1 style={{color:'white'}}>{tournamentData?.tournamentName || "Not Available"}</h1>
            <div className="winners-runners-container">
    <div className="winners">
        <h2>Winners</h2>
        <h4>{tournamentData && tournamentData.winnerTeamName}</h4>
        <ul>
            {tournamentData && tournamentData.winnerTeamNames.map((team, index) => (
                <li key={index}>{team}</li>
            ))}
        </ul>
    </div>
    <div className="runners">
        <h2>Runners</h2>
        <h4>{tournamentData && tournamentData.runnerTeamName}</h4>
        <ul>
            {tournamentData && tournamentData.runnerTeamNames.map((team, index) => (
                <li key={index}>{team}</li>
            ))}
        </ul>
    </div>
</div>
            <table>
    <thead>
        <tr>
            <th>Game Format</th>
            <th>Team A</th> {/* Added column for Team A */}
            <th>A-Player 1</th>
            <th>A-Player 2</th>
            <th>Team B</th> {/* Added column for Team B */}
            <th>B-Player 1</th>
            <th>B-Player 2</th>
            <th>Team A Points</th>
            <th>Team B Points</th>
            <th>Winners</th>
        </tr>
    </thead>
    <tbody>
        {tournamentData && tournamentData.gameDetails.map((game, index) => (
            <tr key={index}>
                <td>{game.gameFormat}</td>
                <td>{game.team1Name}</td> {/* Display Team A name */}
                <td>{game.team1Players[0]}</td>
                <td>{game.team1Players[1]}</td>
                <td>{game.team2Name}</td> {/* Display Team B name */}
                <td>{game.team2Players[0]}</td>
                <td>{game.team2Players[1]}</td>
                <td>{game.team1Points}</td>
                <td>{game.team2Points}</td>
                <td>{game.team1Points > game.team2Points ? game.team1Name : game.team2Name}</td>
            </tr>
        ))}
    </tbody>
</table>

            <table>
                <thead>
                    <tr>
                        <th>Team Name</th>
                        <th>Total Net Points</th>
                        <th>Total Team Points</th>
                    </tr>
                </thead>
                <tbody>
                    {tournamentData && tournamentData.allTeamNames.map((team, index) => (
                        <tr key={index}>
                            <td>{team}</td>
                            <td>{tournamentData.teamsTotalPoints[index]}</td>
                            <td>{tournamentData.teamPoints[index]}</td>
                            {/* You'll need to add the total net points data from your JSON here */}
                        </tr>
                    ))}
                </tbody>
            </table>

            <Footer />
        </div>
    );
};

export default RecentTournament;