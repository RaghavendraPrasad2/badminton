import React, { useEffect, useState } from 'react';
import '../../../styles/CreateTeam.css';
import { getCookie } from '../../afterlogin/HomeAfterSuccessfulLogin';


const CreateTeam = ({ onClose, tournamentId }) => {
  const [teamName, setTeamName] = useState('');
  const [numTeamMembers, setNumTeamMembers] = useState(0);
  const [selectedPlayers, setSelectedPlayers] = useState([]);
  const [availablePlayers, setAvailablePlayers] = useState();

  useEffect(() => {
    const apiUrl = `http://localhost:8080/getUpcomingPlayersData/${tournamentId}`;
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
            console.log(data);
            setAvailablePlayers(data);
        })
        .catch((error) => {
            console.log("Error: ", error);
            //navigate("/sessionFailed");
        });
}, [tournamentId]);

  const handleAddPlayer = (player) => {
    if (selectedPlayers.length < numTeamMembers) {
      setSelectedPlayers([...selectedPlayers, player]);
      setAvailablePlayers(availablePlayers.filter(p => p.playerName !== player.playerName));
    } else {
      alert(`You can only select ${numTeamMembers} players.`);
    }
  };

  const handleRemovePlayer = (player) => {
    setSelectedPlayers(selectedPlayers.filter(p => p.playerName !== player.playerName));
    setAvailablePlayers([...availablePlayers, player]);
  };

  const handleSave = () => {
    // Check if the number of selected players is equal to numTeamMembers
    if (selectedPlayers.length === numTeamMembers && (numTeamMembers !== 0 ) ) {
      const teamInfo = {
        name: teamName,
        players: selectedPlayers.map(player => player.playerName).join(', '),
        tournamentId: tournamentId
      };
      console.log(JSON.stringify(teamInfo));
      alert('Team created successfully');
      fetch('http://localhost:8080/addTeam', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(teamInfo),
      })
        .then((response) => response.json())
        .then((data) => {
          // Handle the response from the server here if needed
          console.log('Server response:', data);
          onClose();
        })
        .catch((error) => {
          // Handle any errors that occurred during the fetch request
          console.error('Error:', error);
        });
    } else {
      // Display an alert if the number of selected players is not equal to numTeamMembers
      alert(`You must select exactly ${numTeamMembers} players and players should not be 0`);
    }
  };

  return (
    <div className="overlay4">
      <div className="overlay-content4">
      <button className='XBtn' onClick={onClose}>&times;</button> <br></br>
      <div className="container">
      <h2>Create Team</h2>
      <label htmlFor="teamName">Team Name: </label>
      <input type="text" id="teamName" value={teamName} onChange={(e) => setTeamName(e.target.value)} />
      <br />
      <label htmlFor="numTeamMembers">Number of Team Members: </label>
      <input type="number" id="numTeamMembers" value={numTeamMembers} onChange={(e) => setNumTeamMembers(Number(e.target.value))} />
      <br />
      <label htmlFor="selectPlayers">Select Players: </label>
      <select id="selectPlayers" onChange={(e) => handleAddPlayer(JSON.parse(e.target.value))}>
        <option value="">Select a player</option>
        {availablePlayers?.map(player => (
          <option key={player.playerName} value={JSON.stringify(player)}>{player.playerName}</option>
        ))}
      </select>
      <br />
      <table>
        <thead>
          <tr>
            <th colSpan="3" className='TeamName'>{teamName}</th>
          </tr>
          <tr>
            <th>Player Name</th>
            <th>Player Rank</th>
            <th>Player Gender</th>
            <th>Remove</th>
          </tr>
        </thead>
        <tbody>
          {selectedPlayers.map(player => (
            <tr key={player.playerName}>
              <td>{player.playerName}</td>
              <td>{player.playerRank}</td>
              <td>{player.playerGender}</td>
              <td><button onClick={() => handleRemovePlayer(player)}>Remove</button></td>
            </tr>
          ))}
        </tbody>
      </table>
      <br />
      <button onClick={handleSave}>Save</button>
    </div>
      </div>
    </div>
  );
};

export default CreateTeam;