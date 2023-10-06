import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import '../../../styles/CreateTeam.css';
import players from '../../../images/badminton-player.png';
import teams from '../../../images/team.png';
import groups from '../../../images/groups.png';
import schedule from '../../../images/schedule.png';
import format from '../../../images/format.png';
import AddPlayers from './AddPlayers'; // Import your components
import CreateTeam from './CreateTeam';
import CreateGroups from './CreateGroups';
import CreateSchedule from './CreateSchedule';
import HeaderAfterLogin from "../../common/HeaderAfterLogin";
import Footer from '../../common/Footer';
import { getCookie } from '../../afterlogin/HomeAfterSuccessfulLogin';
import CreateFormat from './CreateFormat';

function LeaguesComponent() {
  const {tournamentId} = useParams(); 
  const [showAddPlayers, setShowAddPlayers] = useState(false);
  const [showCreateTeam, setShowCreateTeam] = useState(false);
  const [showCreateGroups, setShowCreateGroups] = useState(false);
  const [showCreateSchedule, setShowCreateSchedule] = useState(false);
  const [showCreateFormat, setShowCreateFormat] = useState(false);

  return (
    <>
    <HeaderAfterLogin />
    <div className='LeaguePage'>
      <div className="activities-container" id="activities-container1">
        <div className="activity-card Players" onClick={() => setShowAddPlayers(true)}>
          <img className='PlayerImage' src={players} alt="playersimg" />
          <div className="activity-details">
            <h3>Players</h3>
          </div>
        </div>
        <div className="activity-card Teams" onClick={() => setShowCreateTeam(true)}>
          <img className='PlayerImage' src={teams} alt="Teamsimg" />
          <div className="activity-details">
            <h3>Teams</h3>
          </div>
        </div>
        <div className="activity-card Groups" onClick={() => setShowCreateGroups(true)}>
          <img className='PlayerImage' src={groups} alt="add teams to a group img" />
          <div className="activity-details">
            <h3>Groups</h3>
          </div>
        </div>


        <div className="activity-card Groups" onClick={() =>  setShowCreateSchedule(true)}>
          <img className='PlayerImage' src={schedule} alt="add teams to a group img" />
          <div className="activity-details">
            <h3>Schedule</h3>
          </div>
        </div>

        <div className="activity-card Groups" onClick={() =>  setShowCreateFormat(true)}>
          <img className='PlayerImage' src={format} alt="add teams to a group img" />
          <div className="activity-details">
            <h3>Game Format</h3>
          </div>
        </div>

        
      </div>
      {showAddPlayers && <AddPlayers onClose={() => setShowAddPlayers(false)} tournamentId={tournamentId}/>}
      {showCreateTeam && <CreateTeam onClose={() => setShowCreateTeam(false)}  tournamentId={tournamentId} />}
      {showCreateGroups && <CreateGroups onClose={() => setShowCreateGroups(false)} tournamentId={tournamentId} />}
      {showCreateSchedule && <CreateSchedule onClose={() => setShowCreateSchedule(false)} tournamentId={tournamentId}/>}
      {showCreateFormat && <CreateFormat onClose={() => setShowCreateFormat(false)} tournamentId={tournamentId}/>}
    </div>
    {/* <Footer></Footer> */}
    </>
  );
}

export default LeaguesComponent;