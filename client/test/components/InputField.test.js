import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import { beforeEach, describe, it, test } from '@jest/globals';
import InputField from '../../src/components/InputField/InputField';

describe('InputField' , () => {
    it('mscott17: renders with a placeholder that is passed in as a prop', () => {
        const placeholder = 'test-placeholder'
        render(<InputField placeholder={placeholder}/>);
        const textField = screen.getByPlaceholderText(placeholder);
        expect(textField).toBeInTheDocument;
    })

    it('mscott17: changes with user input change', () => {
        const placeholder = 'test-placeholder'
        render(<InputField placeholder={placeholder}/>);
        const textField = screen.getByPlaceholderText(placeholder);
        fireEvent.change(textField, { target: { value: 'Test Input' } });
        expect(textField.value).toBe('Test Input');
    })
})