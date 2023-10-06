import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import Footer from '../../common/Footer';
import { getCookie } from '../../afterlogin/HomeAfterSuccessfulLogin';
import HeaderAfterLogin from '../../common/HeaderAfterLogin';
import '../../../styles/SchedulerStyle.css';
import { useNavigate } from "react-router-dom";

const CreateScheduleRandom = () => {
  const { tournamentId } = useParams();
  const [groupsAndTeams, setGroupsAndTeams] = useState(null);
  const [groups, setGroups] = useState(groupsAndTeams);
  const [showTeamNames, setShowTeamNames] = useState(false);
  const [matchesGenerated, setMatchesGenerated] = useState(false); // Track if matches are generated

  const navigate = useNavigate();

  useEffect(() => {
    const apiUrl = `http://localhost:8080/getUpcomingGroupsAndTeamsData/${tournamentId}`;
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
        const restructuredData = {};
        data.forEach((item) => {
          const group = Object.keys(item.groups)[0];
          const teams = item.groups[group];
          restructuredData[group] = teams;
        });
        setGroupsAndTeams(restructuredData);
        console.log(JSON.stringify(groupsAndTeams));
      })
      .catch((error) => {
        console.log("Error: ", error);
      });
  }, [tournamentId]);

  const generateMatches = () => {
    const randomizedGroups = { ...groupsAndTeams };
    for (const groupName in randomizedGroups) {
      randomizedGroups[groupName] = generateMatchesWithinGroup(randomizedGroups[groupName]);
    }
    setGroups(randomizedGroups);
    setShowTeamNames(true);
    setMatchesGenerated(true); // Set matchesGenerated to true
    console.log(JSON.stringify(groups));
  };

  const generateMatchesWithinGroup = (teamArray) => {
    const matches = [];
    for (let i = 0; i < teamArray.length; i++) {
      for (let j = i + 1; j < teamArray.length; j++) {
        matches.push(`${teamArray[i]} vs ${teamArray[j]}`);
      }
    }
    return matches;
  };

  const handleDeleteMatch = (groupName, match) => {
    const updatedGroups = { ...groups };
    updatedGroups[groupName] = groups[groupName].filter((m) => m !== match);
    setGroups(updatedGroups);
  };

  const handleSave = () => {
    console.log(JSON.stringify(groups));
    const scheduleData = {
      matches: groups,
      tournamentId: tournamentId,
      clearData: false,
    };

    fetch('http://localhost:8080/addMatches', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(scheduleData),
    })
      .then((response) => {
        if (response.status === 409) {
          const confirmChange = window.confirm(
            "It's already scheduled. Do you want to change?"
          );
          if (confirmChange) {
            const updatedScheduleData = {
              ...scheduleData,
              clearData: true,
            };
            return fetch('http://localhost:8080/addMatches', {
              method: 'POST',
              headers: {
                'Content-Type': 'application/json',
              },
              body: JSON.stringify(updatedScheduleData),
            });
          } else {
            return Promise.reject('Schedule change cancelled');
          }
        }
        return response.json();
      })
      .then((data) => {
        console.log('Server response:', data);
        alert('Schedule entered successfully');
        navigate(`/upcoming-tournaments/${tournamentId}`)

      })
      .catch((error) => {
        if (error !== 'Schedule change cancelled') {
          console.error('Error:', error);
        }
      });
  };

  return (  
    <>
      <HeaderAfterLogin />
      <div>
        <h1 className='generateMatch'>Generate Matches Within Groups</h1>
        
        {showTeamNames && (
          <ul className='TeamNamesRandom'>
            {Object.keys(groupsAndTeams).map((groupName) => (
              <li key={groupName}>
                <strong className='ShowGroupName'>{groupName}</strong>
                <ul>
                  {groupsAndTeams[groupName].map((team, index) => (
                    <li key={index}>{team}</li>
                  ))}
                </ul>
              </li>
            ))}
          </ul>
        )}

        <button className='generateMatches' onClick={generateMatches}>Generate Matches</button>

        {matchesGenerated && groups && Object.keys(groups).map((groupName) => (
          <div key={groupName}>
            <h2 className='groupName'>{groupName}</h2>
            <table><td>
            <ul>
              {groups[groupName].map((match) => (
                <li key={match}>
                  {match} <button onClick={() => handleDeleteMatch(groupName, match)}>Delete</button>
                </li>
              ))}
            </ul>
            </td></table>
          </div>
        ))}

        {matchesGenerated && (
          <button className='RandomSave' onClick={handleSave}>Save</button>
        )}
      </div>
      {/* <Footer /> */}
    </>
  );
};

export default CreateScheduleRandom;
