import React from 'react';
import TournamentComponent from './TournamentComponent';
import "../../styles/ActivitiesComponent.css";

const ActivitiesComponent = ({ tournaments }) => {

  return (
    <div id="activities-section">
      <h2>Ongoing Events</h2>
      <div className='activity-section'>
      
      {tournaments.onGoingTournaments.length === 0 ? (
        <p>No ongoing events</p>
      ) : (
        tournaments.onGoingTournaments.map(tournament => (
          <TournamentComponent key={tournament.id} status={"ongoing"} tournament={tournament} />
        ))
      )}
      </div>
      <h2>Upcoming Events</h2>
      <div className='activity-section'>
      {tournaments.upcomingTournaments.length === 0 ? (
        <p>No upcoming events</p>
      ) : (
        tournaments.upcomingTournaments.map(tournament => (
          <TournamentComponent key={tournament.id} status={"upcoming"} tournament={tournament} />
        ))
      )}
      </div>
      <h2>Recent Events</h2>
      <div className='activity-section' id='recent-section'>
      {tournaments.recentTournaments.length === 0 ? (
        <p>No recent events</p>
      ) : (
        tournaments.recentTournaments.map(tournament => (
          <TournamentComponent key={tournament.id} status={"recent"} tournament={tournament} />
        ))
      )}
      </div>
    </div>
  );
};
export default ActivitiesComponent;