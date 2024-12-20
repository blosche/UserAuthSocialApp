import React, { useState } from 'react';

function InputField({ placeholder, type }) {
    const [inputValue, setInputValue] = useState('');
  
    const handleInputChange = (event) => {
      setInputValue(event.target.value);
    };
    
    return (
      <div style={{padding: "5px"}}>
        <input
          type = {type}
          value={inputValue}
          onChange={handleInputChange}
          placeholder={placeholder}
        />
      </div>
    );
  }

  export default InputField;
  