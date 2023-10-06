import React, { useEffect, useState } from 'react';
import "../../../styles/ScoreCounter.css"
import { useParams } from 'react-router-dom';
import '../../../styles/CreateTeam.css';
import '../../../styles/SchedulerStyle.css';
import HeaderAfterLogin from "../../common/HeaderAfterLogin";


const ScoreCounter = () => {
  const {identifier} = useParams();

  const identifiers = identifier.split("-")

  var matchId1 = ((identifiers[0]-1)/8)-20;
  var tournamentId1=((identifiers[1]-1)/8)-20;

  const urlData = matchId1+ "-" +tournamentId1;
  console.log(urlData);


  const [backendData] = useState({
    matchId: 1,
    Team1: [
      { name: 'prasad', gender: 'Male' },
      { name: 'women1', gender: 'Female' },
      { name: 'men', gender: 'Male' },
      { name: 'women2', gender: 'Female' },
    ],
    Team2: [
      { name: 'Srinivas', gender: 'Male' },
      { name: 'men1', gender: 'Male' },
      { name: 'women1', gender: 'Female' },
      { name: 'women2', gender: 'Female' },
    ],
    gameFormat: ['Mens Singles', 'Women Singles', 'Mens Doubles', 'Women Doubles', 'Mixed Doubles'],
  });

  useEffect(() => {
    const apiUrl = `http://localhost:8080/getPlayersForMatches/${urlData}`;
    const requestOptions = {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
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
      })
      .catch((error) => {
        console.log("Error: ", error);
      });
  }, );
  
  const [selectedGameFormat, setSelectedGameFormat] = useState('');
  const [selectedPlayers, setSelectedPlayers] = useState({ team1: '', team2: '' });
  const [selectedPlayers2, setSelectedPlayers2] = useState({ team1: '', team2: '' });
  const [isStartMatch,setIsStartMatch] = useState(false);
  const [userScore, setUserScore] = useState(0);
  const [opponentScore, setOpponentScore] = useState(0);
  const [winner, setWinner] = useState('');
  const team1key = Object.keys(backendData)[1];
  const team2key = Object.keys(backendData)[2];
  const [isOverlayVisible, setIsOverlayVisible] = useState(false); 
  const isSingles = selectedGameFormat.includes('Singles');
  const isDoubles = selectedGameFormat.includes('Doubles');
  const [numberOfMatchPoints, setNumberOfMatchPoints] = useState(21);
  const isMixedDoubles = selectedGameFormat === 'Mixed Doubles';
  const handleGameFormatChange = (e) => {
    setSelectedGameFormat(e.target.value);
    setSelectedPlayers({ team1: '', team2: '' });
    setSelectedPlayers2({team1 : '',team2:''});
    setIsStartMatch(false);
    setUserScore(0);
    setOpponentScore(0);
    setWinner('');
  };

  const showOverlay = () => {
    setIsOverlayVisible(true);
  };
 
  const hideOverlay = () => {
    console.log("hide");
    setIsOverlayVisible(false);
  };
  const handlePlayerSelect = (e, team, playerNumber) => {
    const playerName = e.target.value;
    if (playerNumber === 1) {
      setSelectedPlayers((prevSelected) => ({
        ...prevSelected,
        [team]: playerName,
      }));
    } else if (playerNumber === 2) {
      setSelectedPlayers2((prevSelected) => ({
        ...prevSelected,
        [team]: playerName,
      }));
    }
    setIsStartMatch(false);
    setUserScore(0);
    setOpponentScore(0);
    setWinner('');
  };
  const handleScoreChange = (player, increment) => {
    if (player === team1key) {
      const newUserScore = userScore + increment;
       setUserScore(newUserScore);
      checkWinner(newUserScore, opponentScore);
    } else if (player === team2key) {
      const newOpponentScore = opponentScore + increment;
      setOpponentScore(newOpponentScore);
      checkWinner(userScore, newOpponentScore);
    }
  };
  const checkWinner = (score1, score2) => {
    if (score1 >= numberOfMatchPoints && score1 - score2 >= 2) {
      setWinner(team1key);
    } else if (score2 >= numberOfMatchPoints && score2 - score1 >= 2) {
      setWinner(team2key);
    }
  };
  const handleSave = () => {
    showOverlay();
    const selectedData = {
      selectedGameFormat,
      selectedPlayers: {
        team1: selectedPlayers.team1,
        team2: selectedPlayers.team2,
      },
      selectedPlayers2: {
        team1: selectedPlayers2.team1,
        team2: selectedPlayers2.team2,
      }
    };
    setIsStartMatch(true);
    setUserScore(0);
    setOpponentScore(0);
    setWinner('');
    console.log(JSON.stringify(selectedData, null, 2)); // Log the data in JSON format
  };
  return (
    <>
      <HeaderAfterLogin />
    
    <div>
      <h1 className='startmatchtitle'>Select Game Format:</h1>
      <select className='manualSelect' value={selectedGameFormat} onChange={handleGameFormatChange}>
        <option value="">Select a Game Format</option>
        {backendData.gameFormat.map((format, index) => (
          <option key={index} value={format}>
            {format}
          </option>
        ))}
      </select><br></br>
      <h4 className='NumberOfMatchPoints'>Enter Number of Match Points</h4>
      <input
  className='NoOfPoints'
  type='text'
  value={numberOfMatchPoints}
  onChange={(e) => setNumberOfMatchPoints(e.target.value)}
></input>
      {selectedGameFormat && (
        <div>
          <h2 className='gameformattitle'>{selectedGameFormat}</h2>
          {isSingles && (
            <div>
              <h2 className='selectTeamName'>Select Player for Team 1:</h2>
              <select className='manualSelect' onChange={(e) => handlePlayerSelect(e, 'team1',1)}>
                <option value="">Select a Player</option>
                {backendData.Team1
                  .filter((player) => player.gender === (selectedGameFormat.includes('Mens') ? 'Male' : 'Female'))
                  .map((player, index) => (
                    <option key={index} value={player.name}>
                      {player.name}
                    </option>
                  ))}
              </select>
              <h2 className='selectTeamName'>Select Player for Team 2:</h2>
              <select className='manualSelect' onChange={(e) => handlePlayerSelect(e, 'team2',1)}>
                <option value="">Select a Player</option>
                {backendData.Team2
                  .filter((player) => player.gender === (selectedGameFormat.includes('Mens') ? 'Male' : 'Female'))
                  .map((player, index) => (
                    <option key={index} value={player.name}>
                      {player.name}
                    </option>
                  ))}
              </select>
            </div>
          )}
{isDoubles && !isMixedDoubles && (
  <div>
    <h2 className='selectTeamName'>Select Players for Team 1:</h2>
    <select className='manualSelect' onChange={(e) => handlePlayerSelect(e, 'team1', 1)}>
      <option value="">Select a Player</option>
      {backendData.Team1
        .filter((player) => player.gender === (selectedGameFormat.includes('Mens') ? 'Male' : 'Female'))
        .map((player, index) => (
          <option key={index} value={player.name}>
            {player.name}
          </option>
        ))}
    </select>
    <select className='manualSelect' onChange={(e) => handlePlayerSelect(e, 'team1', 2)}> {/* Player 2 selection */}
      <option value="">Select a Player</option>
      {backendData.Team1
        .filter((player) => player.gender === (selectedGameFormat.includes('Mens') ? 'Male' : 'Female'))
        .map((player, index) => (
          <option key={index} value={player.name}>
            {player.name}
          </option>
        ))}
    </select>
    <h2 className='selectTeamName'>Select Players for Team 2:</h2>
    <select className='manualSelect' onChange={(e) => handlePlayerSelect(e, 'team2', 1)}>
      <option value="">Select a Player</option>
      {backendData.Team2
        .filter((player) => player.gender === (selectedGameFormat.includes('Mens') ? 'Male' : 'Female'))
        .map((player, index) => (
          <option key={index} value={player.name}>
            {player.name}
          </option>
        ))}
    </select>
    <select className='manualSelect' onChange={(e) => handlePlayerSelect(e, 'team2', 2)}>
      <option value="">Select a Player</option>
      {backendData.Team2
        .filter((player) => player.gender === (selectedGameFormat.includes('Mens') ? 'Male' : 'Female'))
        .map((player, index) => (
          <option key={index} value={player.name}>
            {player.name}
          </option>
        ))}
    </select>
  </div>
)}
{isMixedDoubles && (
  <div>
    <h2 className='selectTeamName'>Select Two Players for Team 1:</h2>
    {[1, 2].map((number) => (
      <select className='manualSelect' key={number} onChange={(e) => handlePlayerSelect(e, 'team1', number)}>
        <option value="">Select a Player</option>
        {backendData.Team1.map((player, index) => (
          <option key={index} value={player.name}>
            {player.name}
          </option>
        ))}
      </select>
    ))}
    <h2 className='selectTeamName'>Select Two Players for Team 2:</h2>
    {[1, 2].map((number) => (
      <select className='manualSelect' key={number} onChange={(e) => handlePlayerSelect(e, 'team2', number)}>
        <option value="">Select a Player</option>
        {backendData.Team2.map((player, index) => (
          <option key={index} value={player.name}>
            {player.name}
          </option>
        ))}
      </select>
    ))}
  </div>
)}
          <button className="StartMatchScoreCounter" onClick={handleSave}>Start a match</button>
        </div>
      )}
      {isStartMatch&&(
  <div className={`overlay3 ${isOverlayVisible ? 'visible' : 'hidden'}`}>
 
<div className='overlay-content3'>
<button className='ScoreCounterXButton' onClick={hideOverlay}>&times;</button>
<div >
  <div className='FirstTeam'><h4 className='PlayerName'>{selectedPlayers.team1}</h4><h4 className='PlayerName'>{selectedPlayers2&&(<>{selectedPlayers2.team1}</>)}</h4></div>
  {winner ? (
    <span>Winner: {winner}</span>
  ) : (
    <div className='ScoreButtons'>
      <button onClick={() => handleScoreChange(team1key, 1)} disabled={winner !== ''}>+</button>
      <span>{userScore}</span>
      <button onClick={() => handleScoreChange(team1key, -1)} disabled={winner !== ''}>-</button>
    </div>
  )}
</div>



<div>
<div className='FirstTeam'><h4 className='PlayerName'>{selectedPlayers.team2}</h4><h4 className='PlayerName'>{selectedPlayers2&&(<>{selectedPlayers2.team2}</>)}</h4></div>
  {winner ? (
    <span>Winner: {winner}</span>
  ) : (
    <div className='ScoreButtons'>
      <button onClick={() => handleScoreChange(team2key, 1)} disabled={winner !== ''}>+</button>
      <span>{opponentScore}</span>
      <button onClick={() => handleScoreChange(team2key, -1)} disabled={winner !== ''}>-</button>
    </div>
  )}
</div>



{winner && (
  <div>Congratulations! {winner} wins!</div>
)}

</div></div>    )}


</div>
</>
  );
};
export default ScoreCounter;