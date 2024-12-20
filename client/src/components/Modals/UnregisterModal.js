import React from 'react';
import {getOriginalServerUrl,sendAPIRequest} from '../../utils/restfulAPI';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';

const LOCAL_STORAGE_USER = 'Page.username'

export default function UnregisterModal(props){

    async function handleUnregisterClick() {
      const response = await sendAPIRequest({username:props.loggedInUser, requestType:"unregister"},getOriginalServerUrl());
      if(response.responseStatus){
        props.setLoggedInUser("guest")
        localStorage.setItem(LOCAL_STORAGE_USER,"guest");
        alert("User unregistered!");
      }
    }

    return (
        <Modal isOpen={props.isOpen} toggle={props.toggle}>
          <ModalHeader>Unregister</ModalHeader>
          <ModalBody>
            <p>Are you sure you would like to unregister your account?</p>
          </ModalBody>
          <ModalFooter>
            <Button color="secondary" onClick={props.toggle}>
              Cancel
            </Button>
            <Button color="primary" onClick={handleUnregisterClick}>
              Unregister
            </Button>
          </ModalFooter>
        </Modal>
    );
}