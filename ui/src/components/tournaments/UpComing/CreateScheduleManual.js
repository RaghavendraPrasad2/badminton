import React, { useEffect, useState } from 'react';
import HeaderAfterLogin from "../../common/HeaderAfterLogin";
import { useParams } from 'react-router-dom';
import { getCookie } from '../../afterlogin/HomeAfterSuccessfulLogin';
const CreateScheduleManual = () => {
  const { tournamentId } = useParams();
  const [groupsAndTeams, setGroupsAndTeams] = useState(null);
  const [availableGroups, setAvailableGroups] = useState([]);
  const [selectedGroups, setSelectedGroups] = useState([]);
  const [matchups, setMatchups] = useState({});
  const [isSaveEnabled, setIsSaveEnabled] = useState(false);
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
        // Restructure the data here
        const restructuredData = {};
        data.forEach((item) => {
          const group = Object.keys(item.groups)[0];
          const teams = item.groups[group];
          restructuredData[group] = teams;
        });
        setGroupsAndTeams(restructuredData);
        setAvailableGroups(Object.keys(restructuredData));
      })
      .catch((error) => {
        console.error("Error: ", error);
      });
  }, [tournamentId]);
 
  const handleDeleteRow = (group, index) => {
    const updatedMatchups = { ...matchups };
    updatedMatchups[group].splice(index, 1);
    setMatchups(updatedMatchups);
  };

  const handleGroupSelect = (group) => {
    // Remove the selected group from availableGroups
    const updatedAvailableGroups = availableGroups.filter((item) => item !== group);
    setAvailableGroups(updatedAvailableGroups);

    setSelectedGroups([...selectedGroups, group]);
    const updatedData = { ...groupsAndTeams };
    delete updatedData[group];

    // Enable the "Save" button when all groups are selected
    setIsSaveEnabled(updatedAvailableGroups.length === 0);
  };

  const handleTeamSelect = (group, team1, team2) => {
    const matchup = `${team1} vs ${team2}`;
    setMatchups({
      ...matchups,
      [group]: [...(matchups[group] || []), matchup],
    });
  };

  const handleTeamSelect1 = (e,group) =>{
    if (e.target.parentNode.previousSibling.firstChild.value &&  e.target.parentNode.previousSibling.firstChild.value !== e.target.value){
        handleTeamSelect(group, e.target.parentNode.previousSibling.firstChild.value, e.target.value)
        e.target.value="";
        e.target.parentNode.previousSibling.firstChild.value="";
    }
    else {
        e.target.value="";
        alert("check once");
    }
  }

  const handleSave = () => {
    console.log(JSON.stringify(matchups));
    const scheduleData  = {
      matches: matchups,
      tournamentId: tournamentId,
      clearData: false
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
          // If the response status is 409 (Conflict), show a confirmation dialog
          const confirmChange = window.confirm(
            "It's already scheduled. Do you want to change?"
          );
          if (confirmChange) {
            // If the user confirms, send the JSON again with clearData as true
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
            // If the user cancels, return a rejected promise to stop further processing
            return Promise.reject('Schedule change cancelled');
          }
        }
        return response.json(); // Continue processing for other responses
      })
      .then((data) => {
        // Handle the response from the server here if needed
        console.log('Server response:', data);
        alert('Schedule entered successfully');
      })
      .catch((error) => {
        if (error !== 'Schedule change cancelled') {
          // Handle any other errors that occurred during the fetch request
          console.error('Error:', error);
        }
      });
  };

  return (
    <> <HeaderAfterLogin />
    {
      groupsAndTeams && 
      <div>
      <div>
        <h2 className='SelectGroups'>Select Groups:</h2>
        <select className='manualSelect' onChange={(e) => handleGroupSelect(e.target.value)}>
          <option value="">Select a group</option>{console.log(JSON.stringify(availableGroups) +  " Available")}
          {availableGroups.map((group) => (
            <option key={group} value={group}>
              {group}
            </option>
          ))}
        </select>
      </div>
      {selectedGroups.map((group) => (
        <div key={group}>
          <h3 className='SideGroupName'>{group}</h3>
          <table className='manualtable'>
            <thead>
              <tr>
                <th className='manualTableHeader'>
                <select className='selectteam1' >
                    <option value="">Select Team</option>
                    {groupsAndTeams[group].map((team) => (
                      <option key={team} value={team}>
                        {team}
                      </option>
                    ))}
                  </select>
                </th>
                <th className='manualTableHeader'>
                <select className='selectteam1' onChange={(e) =>{handleTeamSelect1(e,group)}}>
                    <option value="">Select Team</option>
                    {groupsAndTeams[group].map((team) => (
                      <option key={team} value={team}>
                        {team}
                      </option>
                    ))}
                  </select>
                </th>
                <th className='manualTableHeader action'>Action</th> 
              </tr>
            </thead>
            <tbody>
              {matchups[group] &&
                matchups[group].map((matchup, index) => (
                  <tr key={index}>
                    <td>{matchup.split(" vs ")[0]}</td>
                    <td>{matchup.split(" vs ")[1]}</td>
                    <td>
                      <button onClick={() => handleDeleteRow(group, index)}>
                        Delete
                      </button>
                    </td>
                  </tr>
                ))}
            </tbody>
          </table>
        </div>
      ))}
      {isSaveEnabled && <button className='Manualsave' onClick={handleSave}>Save</button>}
    </div>
    }
    </>
  );
}

export default CreateScheduleManual;