import React, { useState } from 'react';
import { getCookie } from '../../afterlogin/HomeAfterSuccessfulLogin';
const AddPlayers = ({ onClose, tournamentId }) => {
  const [playerName, setPlayerName] = useState('');
  const [playerGender, setPlayerGender] = useState('');
  const [playerRank, setPlayerRank] = useState();
  const handleSubmit = (e) => {
    e.preventDefault();
    const player = {
      playerName: playerName,
      playerGender: playerGender,
      rank: playerRank,
      tournamentId: tournamentId
    };
    // Send a POST request to a dummy URL (replace 'your-dummy-url' with the actual URL)
    fetch('http://localhost:8080/addPlayer', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(player), // Convert the player object to JSON
    })
      .then((response) => response.json())
      .then((data) => {
        // Handle the response from the server here if needed
        console.log('Server response:', data);
        alert("player entered successfully");
        onClose();
      })
      .catch((error) => {
        // Handle any errors that occurred during the fetch request
        console.error('Error:', error);
      });
  };
  return (
    <div className="overlay">
      <div className="overlay-content">
      <button className='XBtn' onClick={onClose}>&times;</button> <br></br>
      <form onSubmit={handleSubmit}>
    <label>Player Name:</label>
    <input type="text" name="playerName" value={playerName} onChange={(e) => setPlayerName(e.target.value)} />
    <br />
    <label>Player Gender:</label>
    <select name="playerGender" value={playerGender} onChange={(e) => setPlayerGender(e.target.value)}>
      <option value="">--Select the gender--</option>
      <option value="Male">Male</option>
      <option value="Female">Female</option>
    </select>
    <br />
    <label>Player Rank:</label>
    <select name="playerRank" value={playerRank} onChange={(e) => setPlayerRank(e.target.value)}>
      <option value="">--Select the rank--</option>
      <option value="1">1</option>
      <option value="2">2</option>
      <option value="3">3</option>
    </select>
    <br />
    <button type="submit">Save</button>
  </form>
      </div>
    </div>
  );
};
export default AddPlayers;






