import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
const LoginForm = (props) => {

    const navigate = useNavigate();

    const [loginFormData, setLoginFormData] = useState({
        userName: "",
        password: ""
    })

    const handleChange = (e) => {
        setLoginFormData((prevLoginFormData) => ({
            ...prevLoginFormData,
            [e.target.name]: e.target.value
        }))
    }

    const handleLoginSubmit = (e) => {
      e.preventDefault();
      const apiUrl = 'http://localhost:8080/login';

      const requestBody = {
              email: loginFormData.userName,
              password: loginFormData.password,
              
          };
      
      const requestOptions = {
          method: 'POST',
          headers: {
              'Content-Type': 'application/json',
          },
          body: JSON.stringify(requestBody),
      };

      fetch(apiUrl, requestOptions)
          .then((response) => {
              if (!response.ok) {
              throw new Error('Network response was not ok');
              }
              return response.json(); 
          })
          .then((data) => {
              console.log('Response:', data);
              console.log(data.sessionKey);
              //alert(data.sessionKey)
               // Check if data contains a session id
                if (data && data.sessionKey) {
                    // Calculate expiration time for one hour from now
                    const expirationTime = new Date();
                    expirationTime.setTime(expirationTime.getTime() + 7 * 60 * 60 * 1000);
                    // Store session id as a cookie with the expiration time
                    document.cookie = `sessionKey=${data.sessionKey}; expires=${expirationTime.toUTCString()}; path=/`;
                    
                    // Redirect to your desired component
                    
                    navigate("/home");
                }
                else{
                    navigate("/registrationFailed");
                }
              
          })
          .catch((error) => {
              console.error('Error:', error);
      });
  }
    return(
        <div class="loginout" id="loginid" style={{display: "none"}}>
            <div className="form-container">
              <h2>Login</h2>
              <button class="close-button" id="Loginclose" onClick={props.closeLogin} >&times;</button>
              <form id="login-form" enctype="multipart/form-data" onSubmit={handleLoginSubmit}>
                <label htmlFor="userName">Email: </label>
                <input 
                 type="text" 
                 name="userName"
                 value={loginFormData.userName}
                 placeHolder="Email" 
                 onChange={handleChange} 
                 required
                />

                <label htmlFor="password">Password: </label>
                <input 
                 type="password" 
                 name="password" 
                 value={loginFormData.password} 
                 placeHolder="Password" 
                 onChange={handleChange} 
                 required 
                />

                <button type="submit">Login</button>
              </form>
            </div>
          </div>
    );
}

export default LoginForm;