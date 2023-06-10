import React, { useState } from 'react';
import { TextField, Button, Grid, Paper, IconButton } from '@mui/material';
import CloseIcon from '@mui/icons-material/Close';

const formContainerStyles = {
    padding: '16px',
    maxWidth: '500px',
    margin: '0 auto',
};

const containerStyles = {
    position: 'fixed',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    zIndex: 9999,
};

const formItemStyles = {
  marginBottom: '16px',
};

const closeButtonStyles = {
    position: 'absolute',
    top: '5px',
    right: '8px',
    cursor: 'pointer',
};

function EditProfileForm({ user, isVisible }) {
    const [firstName, setFirstName] = useState(user.firstName);
    const [lastName, setLastName] = useState(user.lastName);
    const [email, setEmail] = useState(user.email);
    const [phoneNumber, setPhoneNumber] = useState(user.phoneNumber);
    const [address, setAddress] = useState(user.address);
    const [birthday, setBirthday] = useState(user.birthday);
    
    const handleFirstNameChange = (event) => {
        setFirstName(event.target.value);
    };

    const handleLastNameChange = (event) => {
        setLastName(event.target.value);
    };

    const handleEmailChange = (event) => {
        setEmail(event.target.value);
    };

    const handlePhoneNumberChange = (event) => {
        setPhoneNumber(event.target.value);
    };

    const handleAddressChange = (event) => {
        setAddress(event.target.value);
    };

    const handleBirthdayChange = (event) => {
        setBirthday(event.target.value);
    };

    const handleSubmit = (event) => {
        event.preventDefault();

        const updatedUser = {
            firstName,
            lastName,
            email,
            phoneNumber,
            address,
            birthday,
        };
        
        console.log(updatedUser)
    };

    const handleClose = () => {
        isVisible(false);
    };
    

    return (
        <Paper elevation={3} style={containerStyles}>
            <div style={{ position: 'relative', padding: '5px' }}>
                <IconButton style={closeButtonStyles} onClick={handleClose}>
                    <CloseIcon />
                </IconButton>
            </div>
            <form onSubmit={handleSubmit}>
                <Grid container spacing={2} style={formContainerStyles}>
                <Grid item xs={12} sm={6}>
                    <TextField
                    label="First Name"
                    type="text"
                    value={firstName}
                    onChange={handleFirstNameChange}
                    fullWidth
                    required
                    style={formItemStyles}
                    />
                </Grid>
                <Grid item xs={12} sm={6}>
                    <TextField
                    label="Last Name"
                    type="text"
                    value={lastName}
                    onChange={handleLastNameChange}
                    fullWidth
                    required
                    style={formItemStyles}
                    />
                </Grid>
                <Grid item xs={12}>
                    <TextField
                    label="Email"
                    type="email"
                    value={email}
                    onChange={handleEmailChange}
                    fullWidth
                    required
                    style={formItemStyles}
                    />
                </Grid>
                <Grid item xs={12}>
                    <TextField
                    label="Phone Number"
                    type="tel"
                    value={phoneNumber}
                    onChange={handlePhoneNumberChange}
                    fullWidth
                    required
                    style={formItemStyles}
                    />
                </Grid>
                <Grid item xs={12}>
                    <TextField
                    label="Address"
                    type="text"
                    value={address}
                    onChange={handleAddressChange}
                    fullWidth
                    required
                    style={formItemStyles}
                    />
                </Grid>
                <Grid item xs={12}>
                    <TextField
                    label="Birthday"
                    type="date"
                    value={birthday}
                    onChange={handleBirthdayChange}
                    fullWidth
                    required
                    style={formItemStyles}
                    InputLabelProps={{
                        shrink: true,
                    }}
                    />
                </Grid>
                <Grid item xs={12}>
                    <Button type="submit" variant="contained" color="primary" fullWidth>
                    Save Changes
                    </Button>
                </Grid>
                </Grid>
            </form>
        </Paper>
    );
}

export default EditProfileForm;