import React, { useState } from 'react';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import InputField from '../InputField/InputField';
import { getOriginalServerUrl, sendAPIRequest } from '../../utils/restfulAPI';


async function SendLoginRequest(username, password, setLoginStatus) {
  try {
    // Assume sendAPIRequest and getOriginalServerUrl are defined elsewhere
    const loginResponse = await sendAPIRequest({
      requestType: "login",
      username: username,
      password: password,
    }, getOriginalServerUrl());

    console.log(loginResponse);

    if (loginResponse.validated === true) {
      setLoginStatus("Login Successful!");
    } else {
      setLoginStatus("Sorry, username/password is incorrect.")
    }
    
  } catch (error) {
    console.log("Error during login: ", error);
    setLoginStatus("A server error has occurred. Please try again later");
  }
}

export default function LoginModal({ isOpen, toggle }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [loginStatus, setLoginStatus] = useState(null);

  function handleLoginClick() {
    // Call SendLoginRequest with username, password, and setLoginStatus
    setLoginStatus(null);
    SendLoginRequest(username, password, setLoginStatus);
  }

  return (
    <Modal isOpen={isOpen} toggle={toggle}>
      <ModalHeader>Login</ModalHeader>
      <ModalBody>
        <div>
          <input
            type="text"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>

        <div>
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>

      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={toggle}>
          Cancel
        </Button>
        <Button data-testid='loginButton' color="primary" onClick={handleLoginClick}>
          Login
        </Button>
        {loginStatus && <div data-testid='loginStatusTest' className='modal-message'>{loginStatus}</div>}
      </ModalFooter>
    </Modal>
  );
}
