import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import { beforeEach, describe, expect, it, test } from '@jest/globals';
import UnregisterModal from '../../src/components/Modals/UnregisterModal';

describe('UnregistrationModal' , () => {
  beforeEach(() => {
    render(
      <UnregisterModal isOpen={true} />
    );
  });

  test('jibarra1: Modal Opens', () => {
    const unregisterBttn = screen.getByRole('button',{name:/Unregister/i});
    expect(unregisterBttn).toBeInTheDocument;

  });
})