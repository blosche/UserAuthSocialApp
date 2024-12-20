
export const VALID_CONFIG_RESPONSE = JSON.stringify({
    requestType: 'config',
    serverName: 't63',
    features: ['config']
});

export const INVALID_REQUEST = JSON.stringify({
    invalid: 'this is an invalid response to fail the schema'
});

export const VALID_REGISTER_RESPONSE = JSON.stringify({
    username: "user123",
    email: "1@gmail.com",
    password: "password123",
    responseStatus: "Username and Email is already associated with an account",
    requestType: "create"
})

export const VALID_LOGIN_RESPONSE = JSON.stringify({
    username: "user123",
    email: "1@gmail.com",
    password: "password123",
    responseStatus: "Could not find this account",
    requestType: "login"
})