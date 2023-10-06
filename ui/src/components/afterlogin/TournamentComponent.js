import React from "react";
import { Link } from "react-router-dom";

const TournamentComponent = ({tournament, status}) => {
    const tournamentRoute = () => {
      console.log(status)
      if(status==="ongoing"){
        return "/ongoing-tournaments/" + tournament.id;
      }
      else if(status === "upcoming"){
        return "/upcoming-tournaments/" + tournament.id;
      }
      else if(status === "recent"){
        return "/recent-tournaments/" + tournament.id;
      }
    }

    return(
      <Link to={tournamentRoute()} className="tournament-link">
        <div className="tournament-card">
          <h3>{tournament.name}</h3>
          <p> <span> Description: </span>{tournament.description}</p>
          <p> <span>Start Time:</span> {new Date(tournament.startTime).toLocaleString()}</p>
          <p> <span>End Time: </span> {new Date(tournament.endTime).toLocaleString()}</p>
          <p> <span>Organized By: </span>{tournament.user.userName}</p>
        </div>
      </Link>
    );
}

export default TournamentComponent;