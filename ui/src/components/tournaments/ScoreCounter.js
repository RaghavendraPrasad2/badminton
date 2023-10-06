import React, { useState } from 'react';

const ScoreCounter = () => {
  const backendData = {
    "teams": [
      {
        "name": "Team A",
        "players": ["John", "David", "Mary"]
      },
      {
        "name": "Team B",
        "players": ["Peter", "Susan", "Mike"]
      },
      {
        "name": "Team C",
        "players": ["Jane", "Bill", "Chris"]
      }
    ]
  };

  const [userTeam, setUserTeam] = useState('');
  const [opponentTeam, setOpponentTeam] = useState('');
  const [userPlayers, setUserPlayers] = useState([]);
  const [opponentPlayers, setOpponentPlayers] = useState([]);
  const [userScore, setUserScore] = useState(0);
  const [opponentScore, setOpponentScore] = useState(0);
  const [winner, setWinner] = useState('');
  const [matchStarted, setMatchStarted] = useState(false);
  const [gameType, setGameType] = useState('Singles'); 
  const [userPlayer1, setUserPlayer1] = useState('');
  const [userPlayer2, setUserPlayer2] =useState('');
  const [opponentPlayer1,setOpponentPlayer1] = useState('');
  const [opponentPlayer2, setOpponentPlayer2]=useState('');

  const handleUserTeamChange = (event) => {
    setUserTeam(event.target.value);
    setUserPlayers([]);
    setOpponentTeam(backendData.teams.find(team => team.name !== event.target.value)?.name || '');
    setOpponentPlayers([]);
  };

  const handleOpponentTeamChange = (event) => {
    setOpponentTeam(event.target.value);
    setOpponentPlayers([]);
  };

  const handleGameTypeChange = (event) => {
    setGameType(event.target.value);
    setUserPlayers([]);
    setOpponentPlayers([]);
  };

  const handleUserPlayerChange = (event) => {
    const selectedPlayers = Array.from(event.target.selectedOptions, option => option.value);
    if (gameType === "Singles") {
      setUserPlayers(selectedPlayers);
    } else {
      setUserPlayer1(selectedPlayers[0]);
      setUserPlayer2(selectedPlayers[1]);
    }
  };
  
  const handleOpponentPlayerChange = (event) => {
    const selectedPlayers = Array.from(event.target.selectedOptions, option => option.value);
    if (gameType === "Singles") {
      setOpponentPlayers(selectedPlayers);
    } else {
      setOpponentPlayer1(selectedPlayers[0]);
      setOpponentPlayer2(selectedPlayers[1]);
    }
  };

  const handleStartMatch = () => {
    if (userTeam && opponentTeam) {
      setWinner('');
      setUserScore(0);
      setOpponentScore(0);
      setMatchStarted(true);
    } else {
      alert('Please select both user team, opponent team, and at least one player for each team.');
    }
  };

  const handleScoreChange = (player, increment) => {
    if (player === 'user') {
      setUserScore(userScore + increment);
      checkWinner(userScore + increment, opponentScore);
    } else if (player === 'opponent') {
      setOpponentScore(opponentScore + increment);
      checkWinner(userScore, opponentScore + increment);
    }
  };

  const checkWinner = (score1, score2) => {
    if (score1 >= 21 && score1 - score2 >= 2) {
      setWinner({userTeam});
    } else if (score2 >= 21 && score2 - score1 >= 2) {
      setWinner({opponentTeam});
    }
  };

  return (
    <div>
      <h2>ScoreCounter</h2>
      <div>
        <label>User Team:</label>
        <select value={userTeam} onChange={handleUserTeamChange}>
          <option value="">Select Team</option>
          {backendData.teams.map(team => (
            <option key={team.name} value={team.name}>{team.name}</option>
          ))}
        </select>
        <label>Opponent Team:</label>
        <select value={opponentTeam} onChange={handleOpponentTeamChange}>
          <option value="">Select Team</option>
          {backendData.teams.map(team => (
            <option key={team.name} value={team.name}>{team.name}</option>
          ))}
        </select>
        <label>Game Type:</label>
        <select value={gameType} onChange={handleGameTypeChange}>
          <option value="Singles">Singles</option>
          <option value="Doubles">Doubles</option>
        </select>
      </div>
      <div>
        {(userTeam && opponentTeam) && (
          <div>
            <h3>{userTeam}</h3>
            {gameType === "Singles" ? (
              <select value={userPlayers[0]} onChange={handleUserPlayerChange}>
                <option value="">Select Player</option>
                {backendData.teams.find(team => team.name === userTeam).players.map(player => (
                  <option key={player} value={player}>{player}</option>
                ))}
              </select>
            ) : (
              <div>
                <select value={userPlayers[0]} onChange={handleUserPlayerChange}>
                  <option value="">Select Player 1</option>
                  {backendData.teams.find(team => team.name === userTeam).players.map(player => (
                    <option key={player} value={player}>{player}</option>
                  ))}
                </select>
                <select value={userPlayers[1]} onChange={handleUserPlayerChange}>
                  <option value="">Select Player 2</option>
                  {backendData.teams.find(team => team.name === userTeam).players.map(player => (
                    <option key={player} value={player}>{player}</option>
                  ))}
                </select>
              </div>
            )}
            <h3>{opponentTeam}</h3>
          {gameType === "Singles" ? (
            <select value={opponentPlayers[0]} onChange={handleOpponentPlayerChange}>
              <option value="">Select Player</option>
              {backendData.teams.find(team => team.name === opponentTeam).players.map(player => (
                <option key={player} value={player}>{player}</option>
              ))}
            </select>
          ) : (
            <div>
              <select value={opponentPlayers[0]} onChange={handleOpponentPlayerChange}>
                <option value="">Select Player 1</option>
                {backendData.teams.find(team => team.name === opponentTeam).players.map(player => (
                  <option key={player} value={player}>{player}</option>
                ))}
              </select>
              <select value={opponentPlayers[1]} onChange={handleOpponentPlayerChange}>
                <option value="">Select Player 2</option>
                {backendData.teams.find(team => team.name === opponentTeam).players.map(player => (
                  <option key={player} value={player}>{player}</option>
                ))}
              </select>
            </div>
          )}
          <button onClick={handleStartMatch}>Start Match</button>
        </div>
      )}
    </div>
    <div>
      {matchStarted && (
        <div>
          <div>
            <h4>{userPlayers.join(' - ')}</h4>
            <button onClick={() => handleScoreChange('user', 1)}>+</button>
            <span>{userScore}</span>
            <button onClick={() => handleScoreChange('user', -1)}>-</button>
          </div>
          <div>
            <h4>{opponentPlayers.join(' - ')}</h4>
            <button onClick={() => handleScoreChange('opponent', 1)}>+</button>
            <span>{opponentScore}</span>
            <button onClick={() => handleScoreChange('opponent', -1)}>-</button>
          </div>
          {winner && (
            <div>Congratulations! {winner} wins!</div>
          )}
        </div>
      )}
    </div>
  </div>
);
          };

export default ScoreCounter;