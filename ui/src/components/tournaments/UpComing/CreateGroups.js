import React, { useEffect, useState } from 'react';
import { getCookie } from '../../afterlogin/HomeAfterSuccessfulLogin';
const CreateGroups = ({ onClose, tournamentId }) => {
  const [teams, setTeams] = useState();
  const [groupCount, setGroupCount] = useState(0);
  const [groups, setGroups] = useState([]);
  const [selectedTeam, setSelectedTeam] = useState(null);
  const [showGroupSelection, setShowGroupSelection] = useState(false);
  const [selectedPlayers, setSelectedPlayers] = useState([]);
  useEffect(() => {
    const apiUrl = `http://localhost:8080/getUpcomingTeamsData/${tournamentId}`;
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
        setTeams(data);
      })
      .catch((error) => {
        console.log("Error: ", error);
        //navigate("/sessionFailed");
      });
  }, [tournamentId]);
  const handleCreateGroups = () => {
    if (groupCount <= 0) {
      alert('Please specify a valid group count.');
      return;
    }
    const newGroups = Array.from({ length: groupCount }, (_, index) => ({
      groupName: `Group ${String.fromCharCode(65 + index)}`,
      teamList: [],
    }));
    setGroups(newGroups);
  };
  const moveTeamToGroup = (team) => {
    if (groups.length === 0) {
      alert('Please create at least one group before adding teams.');
      return;
    }
    setSelectedTeam(team);
    setShowGroupSelection(true);
  };
  const handleSave = () => {
    if (groups.length === 0) {
      alert('Please create at least one group before saving.');
      return;
    }
    // Create an array of group objects
    const groupObjects = groups.map((group) => ({
      name: group.groupName,
      teams: group.teamList.map((team) => team.name).join(', '), // Concatenate team names with commas
      tournamentId: tournamentId, // Add tournamentId to each group
    }));
    const groupObjects2 = {};
    groupObjects.forEach(element => {
      console.log(element);
    });
    console.log(JSON.stringify(groupObjects));
    alert('Groups created successfully');
    fetch('http://localhost:8080/addGroups', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(groupObjects), // Convert the player object to JSON
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
  };
  const handleGroupSelection = (selectedGroup) => {
    const updatedTeams = teams.filter(t => t.id !== selectedTeam.id);
    const updatedGroups = groups.map(group =>
      group.groupName === selectedGroup
        ? { ...group, teamList: [...group.teamList, selectedTeam] }
        : group
    );
    const updatedSelectedPlayers = [...selectedPlayers, { ...selectedTeam, selectedGroup }];
    setTeams(updatedTeams);
    setGroups(updatedGroups);
    setShowGroupSelection(false);
    setSelectedTeam(null);
    setSelectedPlayers(updatedSelectedPlayers);
  };
  const removeTeamFromGroup = (team, groupIndex) => {
    const updatedTeams = [...teams, team];
    const updatedGroups = [...groups];
    updatedGroups[groupIndex].teamList = updatedGroups[groupIndex].teamList.filter(t => t.id !== team.id);
    setTeams(updatedTeams);
    setGroups(updatedGroups);
  };
  return (
    <div className="overlay1">
      <div className="overlay-content1">
        <button className='XBtn' onClick={onClose}>&times;</button>
        <div className='groupContainer'>
          <h1>Create Groups</h1>
          <table>
            <thead>
              <tr>
                <th className='TeamName'>Team Names</th>
              </tr>
            </thead>
            <tbody>
              {teams?.map(team => (
                <tr key={team.id}>
                  <td>{team.name}</td>
                  <td>
                    {groups.length >= 1 && (
                      <button onClick={() => moveTeamToGroup(team)}>+</button>
                    )}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          <input
            type="number"
            value={groupCount}
            onChange={e => setGroupCount(parseInt(e.target.value))}
          />
          <button className='CreateGroups' onClick={handleCreateGroups}>Create Groups</button>
          {groups.map((group, groupIndex) => (
            <div key={groupIndex}>
              <h2>{group.groupName}</h2>
              <table>
                <thead>
                  <tr>
                    <th className='TeamName'>Teams</th>
                  </tr>
                </thead>
                <tbody>
                  {group.teamList.map(team => (
                    <tr key={team.id}>
                      <td>{team.name}</td>
                      <td>
                        <button onClick={() => removeTeamFromGroup(team, groupIndex)}>Remove</button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          ))}
          {groups.length > 0 && (
            <button className='GroupSave' onClick={handleSave}>Save</button>
          )}
          {showGroupSelection && selectedTeam && (
            <div className='overlay11'>
              <div className='overlay-content11'>
                <h3 className='Heading'>Select a Group for {selectedTeam.name}</h3>
                {groups.map(group => (
                  <label key={group.groupName} className='group-button'>
                    <input
                      type="radio"
                      name="groupSelection"
                      value={group.groupName}
                      onChange={() => handleGroupSelection(group.groupName)}
                    />
                    {group.groupName}
                  </label>
                ))}
              </div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};
export default CreateGroups;