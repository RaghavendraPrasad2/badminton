import React, { useState } from "react";

const RegistrationForm = (props) => {

    const [registerFormData,setRegisterFormData] = useState({
        userName: "",
        contactInfo: "",
        password: "",
        confirm_password: "",
        gender: ""
    });

    const handleRegistrationSubmit = (e) => {
        e.preventDefault();
        const apiUrl = 'http://localhost:8080/register';

        const requestBody = {
                userName: registerFormData.userName,
                password: registerFormData.password,
                contactInfo: registerFormData.contactInfo,
                gender: registerFormData.gender
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
                alert("Registration is successful. Please Login")
                props.closeRegister();
                props.openLogin();
            })
            .catch((error) => {
                console.error('Error:', error);
        });
    }

    
    const handleChange = (e) => {
        setRegisterFormData((prevRegisterFormData) => ({
            ...prevRegisterFormData,
            [e.target.name]: e.target.value
        }))
    }



    return(
        <div class="signupout" id="signupid" style={{display: "none"}}>
            <div class="form-container">
              <button class="close-button" id="Signupclose" onClick={props.closeRegister}>&times;</button>
              <form id="form" onSubmit={handleRegistrationSubmit}>
                <h2>Registration Form</h2>
                <div class="form-row">
                  <div class="form-column">
                    <label for="userName">Name:</label>
                        <div class="field">
                        <input 
                         type="text" 
                         id="name" 
                         name="userName" 
                         placeHolder="Enter Your Name" 
                         value={registerFormData.userName}
                         onChange={handleChange}
                         required 
                        />
                        <small></small>
                        </div>
                    <label for="contactInfo">Email:</label>
                        <div class="field">
                        <input 
                         type="email" 
                         id="email" 
                         name="contactInfo" 
                         placeholder="Enter Your Email" 
                         value={registerFormData.contactInfo}
                         onChange={handleChange}
                         required 
                        />
                        <small></small>
                        </div>
                    <label for="password">Password:</label>
                        <div class="field">
                        <input 
                         type="password" 
                         id="password" 
                         name="password" 
                         placeholder="Create new Password" 
                         value={registerFormData.password}
                         onChange={handleChange}
                         required 
                        />
                        <small></small>
                        </div>
                    <label for="confirm_password">Confirm Password:</label>
                        <div class="field">
                        <input 
                         type="password" 
                         id="confirm_password" 
                         name="confirm_password" 
                         placeholder="Confirm Password" 
                         value={registerFormData.confirm_password}
                         onChange={handleChange}
                         required 
                        />
                        <small></small>
                        </div>
                    <div class="field">
                        <label for="gender">Gender:</label>
                        <select 
                         id="gender" 
                         name="gender"
                         value={registerFormData.gender}
                         onChange={handleChange}
                         >
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                            <option value="Other">Other</option>
                        </select>
                    </div>
                  </div>
                </div>
                <button id="register-btn" type="submit">Register</button>
              </form>
            </div>
          </div>
    );
}
export default RegistrationForm;