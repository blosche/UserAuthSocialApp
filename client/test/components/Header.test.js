import React from 'react';
import { fireEvent, render, screen, waitFor } from '@testing-library/react';
import { beforeEach, describe, expect, it } from '@jest/globals';
import Header from '../../src/components/Header/Header';
import user from '@testing-library/user-event';
import '@testing-library/jest-dom';


describe('Header', () => {
    beforeEach(() => {
        render(
        
            <Header />
        );
    });

    it('dropdown opens and closes', async () => {
        const dropdown = screen.getByTestId('hamburger-dropdown');
        user.click(dropdown);

        await waitFor(() => {
            let profile = screen.getByTestId('profile-dropdown-item');
            expect(profile).toBeVisible();
        });
    });

    it('mscott17: Registration Modal should render when registration button is clicked', () => {
        const dropdown = screen.getByTestId('hamburger-dropdown');
        fireEvent.click(dropdown)
        const registerBtn = screen.getByTestId('register-dropdown')
        fireEvent.click(registerBtn);
        const registerationCancelBtn = screen.getByRole('button', { name: /cancel/i });
        expect(registerationCancelBtn).toBeInTheDocument;
    });

    it('corpaul123: Login Modal should render when login button is clicked', () => {
        const dropdown = screen.getByTestId('hamburger-dropdown');
        fireEvent.click(dropdown)
        const loginBtn = screen.getByTestId('login-dropdown')
        fireEvent.click(loginBtn);
        const loginCancelBtn = screen.getByRole('button', {name: /cancel/i });
        expect(loginCancelBtn).toBeInTheDocument;
    })
})