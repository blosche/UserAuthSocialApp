import React from 'react';

import { findByPlaceholderText, render, screen, fireEvent, getByTestId, waitFor, toBeVisible } from '@testing-library/react';
import { beforeEach, describe, expect, it, test } from '@jest/globals';
import LoginModal from '../../src/components/Modals/LoginModal';
import user from '@testing-library/user-event';
import { VALID_LOGIN_RESPONSE } from '../sharedMocks';
import { act } from 'react-dom/test-utils';
import '@testing-library/jest-dom'

describe('LoginModal' , () => {


      it('corpaul123: text entry works', async () => {
        fetch.resetMocks();
        fetch.mockResponse(VALID_LOGIN_RESPONSE);
        let isModalOpen = true;
        const toggleModal = () => {
          isModalOpen = !isModalOpen;
        };

        render(<LoginModal isOpen={isModalOpen} toggle={toggleModal} />);


        const usernameBox = screen.getByPlaceholderText('Username');
        const passwordBox = screen.getByPlaceholderText('Password');
        user.type(passwordBox, 'password123');
        user.type(usernameBox, 'user123');


        const loginButton = screen.getByTestId('loginButton');
        await waitFor(() => {
        fireEvent.click(loginButton)

        })

        expect(screen.getByTestId('loginStatusTest')).toBeInTheDocument();
      });
})