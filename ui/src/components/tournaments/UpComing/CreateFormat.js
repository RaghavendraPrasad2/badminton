import React, { useState } from 'react';

function CreateFormat({ onClose ,tournamentId }) {
  const [selectedFormats, setSelectedFormats] = useState([]);
  const [tableData, setTableData] = useState([]);
  const [availableFormats, setAvailableFormats] = useState([
    "Men Singles",
    "Women Singles",
    "Men Doubles",
    "Women Doubles",
    "Mixed Doubles"
  ]);

  const handleFormatSelect = (format) => {
    const updatedSelectedFormats = [...selectedFormats, format];
    setSelectedFormats(updatedSelectedFormats);

    const updatedAvailableFormats = availableFormats.filter((item) => item !== format);
    setAvailableFormats(updatedAvailableFormats);
  };

  const handleRemoveFormat = (format) => {
    const updatedSelectedFormats = selectedFormats.filter((item) => item !== format);
    setSelectedFormats(updatedSelectedFormats);

    const updatedTableData = [...tableData, format];
    setTableData(updatedTableData);

    const updatedAvailableFormats = [...availableFormats, format];
    setAvailableFormats(updatedAvailableFormats);
  };

  const handleSave = () => {
    if (selectedFormats.length === 0) {
      alert("Please select at least one format.");
      return;
    }
  
    const tournamentData = {
      tournamentId: tournamentId,
      gameFormat: selectedFormats.join(', ')
    };
  
    // Perform a fetch call to a dummy link
    fetch('http://localhost:8080/addSchedule', {
      method: 'POST', 
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(tournamentData),
    })
    .then((response) => {
      if (response.ok) {
        alert("Save successful!");
        onClose();
      } else {
        alert("Save failed. Please try again.");
        onClose();
      }
    })
    .catch((error) => {
      console.error("Error:", error);
    });
  };

  return (
    <div className='overlay'>
     <div className='overlay-content'>
     <button className='XBtn' onClick={onClose}>&times;</button>
      <h1>Create Format</h1>
      <div>
        <select onChange={(e) => handleFormatSelect(e.target.value)}>
          <option value="">Select a Format</option>
          {availableFormats.map((format) => (
            <option key={format} value={format}>
              {format}
            </option>
          ))}
        </select>
      </div>
      <table>
        <thead>
          <tr>
            <th>Selected Formats</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {selectedFormats.map((format) => (
            <tr key={format}>
              <td>{format}</td>
              <td>
                <button onClick={() => handleRemoveFormat(format)}>Remove</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <button onClick={handleSave}>Save</button>
      </div>
    </div>

  );
}

export default CreateFormat;