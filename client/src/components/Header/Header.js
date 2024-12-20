import React, {useState} from 'react';
import { Container, Button } from 'reactstrap';
import { CLIENT_TEAM_NAME } from '../../utils/constants';
import { FiMenu } from 'react-icons/fi';
import { BsPersonFill, BsPersonFillAdd, BsPersonFillDash } from 'react-icons/bs';
import {RiLoginBoxFill} from 'react-icons/ri';
import { Dropdown, DropdownToggle, DropdownMenu, DropdownItem } from 'reactstrap'
import RegistrationModal from '../Modals/RegistrationModal'
import UnregisterModal  from '../Modals/UnregisterModal';
import LoginModal from '../Modals/LoginModal';

export default function Header(props) {

	return (
	<HeaderContents {...props}/> 
	);

}

function HeaderContents(props) {

	const [dropdownOpen, setDropdownOpen] = useState(false);
	const [modalOpen, setModelOpen] = useState(false);
	const [unregisterModal, setUnregisterModal] = useState(false);
	const [loginModalOpen, setLoginModelOpen] = useState(false);

	const toggleModal = () => setModelOpen(!modalOpen)
	const toggleModalLog = () => setLoginModelOpen(!loginModalOpen)
	const toggle = () => setDropdownOpen((prevState) => !prevState);
	const toggleUnregisterModal = () => setUnregisterModal(!unregisterModal);
	return (
		
		<div className='full-width header vertical-center'>
			<Container>
				<div className='header-container'>
					<h1 className='tco-text-upper header-title' data-testid='header-title'>
						{CLIENT_TEAM_NAME}
					</h1>
			
				<Dropdown isOpen={dropdownOpen} toggle={toggle}>
				<DropdownToggle color='white' flip outline className='float-right' data-testid='hamburger-dropdown'><FiMenu className='btn-header'/></DropdownToggle>
					<DropdownMenu>
						<DropdownItem data-testid='profile-dropdown-item' > <BsPersonFill/> Profile</DropdownItem>
						<DropdownItem data-testid='login-dropdown' onClick={toggleModalLog} ><RiLoginBoxFill data-testid='login-icon' /> Login</DropdownItem>
						<DropdownItem data-testid ='register-dropdown' onClick={toggleModal}><BsPersonFillAdd /> Register</DropdownItem>
						<DropdownItem data-testid='unregister-dropdown-item' onClick={toggleUnregisterModal}><BsPersonFillDash />Unregister</DropdownItem>
					</DropdownMenu>
					<RegistrationModal isOpen={modalOpen} toggle={toggleModal} />
					<UnregisterModal isOpen={unregisterModal} toggle={toggleUnregisterModal} {...props}/>
					<LoginModal isOpen={loginModalOpen} toggle={toggleModalLog} />
				</Dropdown>
				</div>
			</Container>
		</div>
	);
}
