import React from 'react';
import { findByPlaceholderText, render, screen, fireEvent, getByTestId, waitFor, toBeVisible } from '@testing-library/react';
import { beforeEach, describe, expect, it, test } from '@jest/globals';
import RegistrationModal from '../../src/components/Modals/RegistrationModal';
import user from '@testing-library/user-event';
import { VALID_REGISTER_RESPONSE } from '../sharedMocks';
import { act } from 'react-dom/test-utils';
import '@testing-library/jest-dom'

describe('RegistrationModal' , () => {
    test('mscott17: renders the RegistrationModal', () => {
        const { getAllByText } = render(<RegistrationModal isOpen={true} />);
        expect(getAllByText('Register')).toBeInTheDocument;
      });

      test('mscott17: closes the modal when Cancel button is clicked', () => {
        let isModalOpen = true;
        const toggleModal = () => {
          isModalOpen = !isModalOpen;
        };
        const { getByText } = render(<RegistrationModal isOpen={isModalOpen} toggle={toggleModal} />);
        fireEvent.click(getByText('Cancel'));
        expect(isModalOpen).toBe(false);
      });

      it('blosche: text entry works', async () => {
        fetch.resetMocks();
        fetch.mockResponse(VALID_REGISTER_RESPONSE);
        let isModalOpen = true;
        const toggleModal = () => {
          isModalOpen = !isModalOpen;
        };

        render(<RegistrationModal isOpen={isModalOpen} toggle={toggleModal} />);

        
        const emailBox = screen.getByPlaceholderText('Email');
        const usernameBox = screen.getByPlaceholderText('Username');
        const passwordBox = screen.getByPlaceholderText('Password');
        user.type(emailBox, '1@gmail.com');
        user.type(passwordBox, 'password123');
        user.type(usernameBox, 'user123');

        
        const registerButton = screen.getByTestId('registerButton');
        await waitFor(() => {
        fireEvent.click(registerButton)
        
        })

        expect(screen.getByTestId('responseTextTest')).toBeInTheDocument();
      });
})