import React, { useState } from 'react';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import InputField from '../InputField/InputField';
import { getOriginalServerUrl, sendAPIRequest } from '../../utils/restfulAPI';

async function SendRegisterRequest(responseText, setResponseText, toggle) {
  const vals = document.getElementsByTagName('input');
  const registerResponse = await sendAPIRequest({ requestType:"create", email: vals[0].value, username: vals[1].value, password: vals[2].value }, getOriginalServerUrl());
  setResponseText(registerResponse.responseStatus);
  }
export default function RegistrationModal({ isOpen, toggle }) {

  const [responseText, setResponseText] = useState(null);
  
  function handleRegisterClick() {
    setResponseText(null);
    const regRes = SendRegisterRequest(responseText, setResponseText, toggle);
    
  }

 
  return (
    <Modal isOpen={isOpen} toggle={toggle}>
      <ModalHeader>Register</ModalHeader>
      <ModalBody>
        <InputField placeholder={"Email"} type={"text"}/>
        <InputField placeholder={"Username"} type={"text"}/>
        <InputField placeholder={"Password"} type={"password"}/>
      </ModalBody>
      {responseText && (
        <div data-testid='responseTextTest' className='modal-message'>{responseText}</div>
        )}
      <ModalFooter>
        <Button color="secondary" onClick={toggle}>
          Cancel
        </Button>
        <Button data-testid='registerButton' color="primary" onClick={handleRegisterClick}>
          Register
        </Button>
      </ModalFooter>
    </Modal>
  );
}