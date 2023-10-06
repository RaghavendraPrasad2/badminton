import React from 'react';
import { Link } from 'react-router-dom';
import '../../styles/SessionInvalidateComponent.css';
import Header from './Header';
import Footer from './Footer';
const SessionInvalidComponent = () => {
  return (

<>   
    <Header />
    <div className="session-invalid">
      <p>Session is invalid. Please <Link to="/">login</Link> again.</p>
    </div>
    <Footer/>
    </>
  );
};
export default SessionInvalidComponent;