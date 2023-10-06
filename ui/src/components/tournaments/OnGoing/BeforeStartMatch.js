import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { getCookie } from '../../afterlogin/HomeAfterSuccessfulLogin';

const BeforeStartMatch = () => {
  const { tournamentId } = useParams();
  const navigate = useNavigate();
  const [matchData, setMatchData] = useState(null); // Initialize as null

  function caesarEncrypt(plainText)
  {
    var encryptedText = 0;
    encryptedText = ((plainText + 20)*8)+1;;
    return encryptedText;
  }


  useEffect(() => {
    const apiUrl = `http://localhost:8080/getGroupMatches/${tournamentId}`;
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
        setMatchData(data.groups);
      })
      .catch((error) => {
        console.log("Error: ", error);
        //navigate("/sessionFailed");
      });
  }, [tournamentId]);

  // Define functions for starting and deleting matches here
  const handleStartMatch = (matchId) => {
    
    const matchId1 = caesarEncrypt(matchId);
    var temp = tournamentId-1;
    console.log("temp"+temp);
    const tournamentId1 = caesarEncrypt(temp+1);

    console.log("Tout "+tournamentId);

    navigate("/start-match/" + matchId1 + "-" + tournamentId1);
  };
  const handleDeleteMatch = (matchId) => {
    // Implement delete match logic
    console.log(matchId);
    navigate("/ongoing-tournaments/" + tournamentId);
  };

  return (
    <div>
      <h1 className='startmatchtitle'>Start Match</h1>
      {matchData !== null && Object.keys(matchData).map(groupName => (
        <div key={groupName}>
          <h2 className='GroupNameBefore'>{groupName}</h2>
          <table className='StartMatchTable'>
            <thead>
              <tr>
                <th>Team A</th>
                <th>Team B</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              {matchData[groupName].map(match => (
                <tr key={match.id}>
                  <td>{match.match.split(' vs ')[0]}</td>
                  <td>{match.match.split(' vs ')[1]}</td>
                  <td>
                    <button onClick={() => handleStartMatch(match.id)}>Start Match</button>
                    <button onClick={() => handleDeleteMatch(match.id)}>Delete</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      ))}
    </div>
  );
};

export default BeforeStartMatch;