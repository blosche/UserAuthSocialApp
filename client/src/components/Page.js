import React, { useEffect, useState } from 'react';
import { useServerSettings } from '../hooks/useServerSettings'
import Header from './Header/Header'

const LOCAL_STORAGE_USER = 'Page.username'

export default function Page(props) {
	const [serverSettings, processServerConfigSuccess] = useServerSettings(props.showMessage);
	const [loggedInUser, setLoggedInUser] = useState("guest");

	useEffect(()=>{getUser(LOCAL_STORAGE_USER, setLoggedInUser)},[]);
	useEffect(()=>{saveUser(LOCAL_STORAGE_USER,loggedInUser)},[loggedInUser])
	
	return (
		<Header loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser}/>
	);
}

export function getUser(StorageKey, setter){
    return () => {
        const obj = JSON.parse(localStorage.getItem(StorageKey))
        if (obj) setter(obj)
    }
}
export function saveUser(StorageKey, str){
    return () => {
        localStorage.setItem(StorageKey, JSON.stringify((str)))
    }
}

