import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
const LeagueForm = (props) => {
    const navigate = useNavigate();
    const currentDate = new Date();
    const currentHour = String(currentDate.getHours()).padStart(2, '0');
    const currentMinute = String(currentDate.getMinutes()).padStart(2, '0');
    const currentTime = `${currentHour}:${currentMinute}`;
    const [formData, setFormData] = useState({
        leagueName: "",
        startDate: new Date().toISOString().split('T')[0],
        startTime: currentTime, // Initialize with the current time
        endDate: "",
        endTime: "",
        description: "",
        conductorEmail: "",
    });
    const [endDateError, setEndDateError] = useState("");
    const [selectedStartDate, setSelectedStartDate] = useState(new Date().toISOString().split('T')[0]);
    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setFormData({
            ...formData,
            [name]: value,
        });
        if (name === "startDate" || name === "startTime" || name === "endDate" || name === "endTime") {
            validateTime();
        }
        // Call the validation function for end date and time whenever their values change
    };
    const getCurrentTime = () => {
        const currentDate = new Date();
        const currentHour = String(currentDate.getHours()).padStart(2, '0');
        const currentMinute = String(currentDate.getMinutes()).padStart(2, '0');
        return `${currentHour}:${currentMinute}`;
    };
    const validateTime = () => {
        const { startDate, startTime, endDate, endTime } = formData;
        const startDateTime = new Date(`${startDate}T${startTime}`);
        const endDateTime = new Date(`${endDate}T${endTime}`);
        if (endDateTime < startDateTime) {
            setEndDateError("Start time should be smaller than end time on the same date");
        } else {
            setEndDateError("");
        }
    };
    const handleSubmit = (event) => {
        event.preventDefault();
        validateTime(); // Validate end date and time before submitting
        if (endDateError === "") {
            // Validation passed
            const { startDate, startTime, endDate, endTime } = formData;
            const startDateTime = `${startDate}T${startTime}`;
            const endDateTime = `${endDate}T${endTime}`;
            // Create a new FormData object with concatenated date-time values
            const formDataToSend = {
                ...formData,
                startDate: startDateTime,
                endDate: endDateTime,
            };
            fetch("http://localhost:8080/addTournament", {
                method: "POST",
                body: JSON.stringify(formDataToSend),
                headers: {
                    "Content-Type": "application/json",
                },
            })
            .then((response) => response.json())
            .then((data) => {
                console.log("Response from dummy link:", data);
                navigate("/upcoming-tournaments/"+data.id);
            })
            .catch((error) => {
                console.error("Error sending data:", error);
            });
        }
    };
    const clearFormFields = () => {
        setFormData({
            leagueName: "",
            startDate: new Date().toISOString().split('T')[0], // Reset to the current date
            startTime: getCurrentTime(), // Reset to the current time
            endDate: "",
            endTime: "",
            description: "",
            conductorEmail: "",
        });
        setEndDateError("");
    };
    return (
        <>
            <div className="signupout" id="signupid" style={{ display: "none" }}>
                <div className="form-container form-container1">
                    <button className="close-button" id="Signupclose" onClick={() => {
                        props.closeLeagueForm();
                        clearFormFields();
                    }}>
                        &times;
                    </button>
                    <form id="form" onSubmit={handleSubmit}>
                        <h2>Create League</h2>
                        <div className="form-row">
                            <div className="form-column">
                                <label htmlFor="leagueName">League Name:</label>
                                <div className="field">
                                    <input
                                        type="text"
                                        id="league_name"
                                        name="leagueName"
                                        placeholder="Enter League Name"
                                        value={formData.leagueName}
                                        onChange={handleInputChange}
                                        required
                                    />
                                    <small></small>
                                </div>
                                <label htmlFor="startDate">Start Date:</label>
                                <div className="field">
                                    <input
                                        type="date"
                                        id="startdate"
                                        name="startDate"
                                        value={formData.startDate}
                                        onChange={handleInputChange}
                                        min={selectedStartDate} // Set the minimum date to the current date
                                        required
                                    />
                                    <small></small>
                                </div>
                                <label htmlFor="startTime">Start Time:</label>
                                <div className="field">
                                    <input
                                        type="time"
                                        id="starttime"
                                        name="startTime"
                                        value={formData.startTime}
                                        onChange={handleInputChange}
                                        required
                                    />
                                    <small></small>
                                </div>
                                <label htmlFor="endDate">End Date:</label>
                                <div className="field">
                                    <input
                                        type="date"
                                        id="enddate"
                                        name="endDate"
                                        value={formData.endDate}
                                        onChange={handleInputChange}
                                        min={formData.startDate} // Set the minimum date based on selected start date
                                        required
                                    />
                                    <small className="error-message">{endDateError}</small>
                                </div>
                                <label htmlFor="endTime">End Time:</label>
                                <div className="field">
                                    <input
                                        type="time"
                                        id="endtime"
                                        name="endTime"
                                        value={formData.endTime}
                                        onChange={handleInputChange}
                                        required
                                    />
                                    <small className="error-message">{endDateError}</small>
                                </div>
                                <label htmlFor="description">Description:</label>
                                <div className="field">
                                    <input
                                        type="text"
                                        name="description"
                                        placeholder="Enter Description"
                                        value={formData.description}
                                        onChange={handleInputChange}
                                    />
                                    <small></small>
                                </div>
                                <label htmlFor="conductorEmail">Conductor Email:</label>
                                <div className="field">
                                    <input
                                        type="email"
                                        id="conductor_name"
                                        name="conductorEmail"
                                        placeholder="Conductor Email"
                                        value={formData.conductorEmail}
                                        onChange={handleInputChange}
                                        required
                                    />
                                    <small></small>
                                </div>
                            </div>
                        </div>
                        <button id="register-btn" type="submit">
                            Create League
                        </button>
                    </form>
                </div>
            </div>
        </>
    );
};
export default LeagueForm;