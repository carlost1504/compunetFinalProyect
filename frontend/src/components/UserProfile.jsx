import React, { useState } from 'react';
import { Typography, Button, Grid, Paper} from '@mui/material';
import CloseIcon from '@mui/icons-material/Close';
import UpdateProfile from './UpdateProfile.jsx'

const cardContainerStyles = {
  position: 'fixed',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  zIndex: 999,
  width: 300,
  padding: '16px',
  borderRadius: '4px',
  boxShadow: '0px 2px 4px rgba(0, 0, 0, 0.1)',
  backgroundColor: '#ffffff',
};

const titleStyles = {
  marginBottom: '16px',
  fontWeight: 'bold',
};

const infoItemStyles = {
  marginBottom: '8px',
};

const editButtonStyles = {
  marginTop: '16px',
};

const closeButtonStyles = {
  position: 'absolute',
  top: '8px',
  right: '8px',
  cursor: 'pointer',
};

const responsiveCardContainerStyles = {
  '@media (maxWidth: 480px)': {
    width: '90%',
    left: '5%',
    transform: 'translate(0, -50%)',
  },
};

function UserProfile({ user, isVisible }) {
    const { firstName, lastName, email, phoneNumber, address, birthday } = user;
    const [update, setUpdate] = useState(false);

    const handleEditProfile = () => {
        setUpdate(true);
    };

    const handleClose = () => {
        isVisible(false);
    };

    return (
        <div>
            { !update ? (
                <Paper elevation={3} style={{ ...cardContainerStyles, ...responsiveCardContainerStyles }}>
                <CloseIcon style={closeButtonStyles} onClick={handleClose} />
                <Typography variant="h5" style={titleStyles} gutterBottom>
                    User Profile
                </Typography>
                <Grid container spacing={2}>
                    <Grid item xs={12}>
                        <Typography variant="body1" style={infoItemStyles}>
                            <strong>First Name:</strong> {firstName}
                        </Typography>
                    </Grid>
                    <Grid item xs={12}>
                        <Typography variant="body1" style={infoItemStyles}>
                            <strong>Last Name:</strong> {lastName}
                        </Typography>
                    </Grid>
                    <Grid item xs={12}>
                        <Typography variant="body1" style={infoItemStyles}>
                            <strong>Email:</strong> {email}
                        </Typography>
                    </Grid>
                    <Grid item xs={12}>
                        <Typography variant="body1" style={infoItemStyles}>
                            <strong>Phone Number:</strong> {phoneNumber}
                        </Typography>
                    </Grid>
                    <Grid item xs={12}>
                        <Typography variant="body1" style={infoItemStyles}>
                            <strong>Address:</strong> {address}
                        </Typography>
                    </Grid>
                    <Grid item xs={12}>
                        <Typography variant="body1" style={infoItemStyles}>
                            <strong>Birthday:</strong> {convertDate(birthday)}
                        </Typography>
                    </Grid>
                    </Grid>
                    <Button
                        variant="contained"
                        color="primary"
                        fullWidth
                        style={editButtonStyles}
                        onClick={handleEditProfile}
                    >
                        Edit Profile
                    </Button>
                </Paper>
            ):(
                update && <UpdateProfile user={user} isVisible={setUpdate}/>
            )}
        </div>
        
    );
}

function convertDate(inputFormat) {
    function pad(s) { return (s < 10) ? '0' + s : s; }
    var d = new Date(inputFormat)
    return [pad(d.getDate()), pad(d.getMonth()+1), d.getFullYear()].join('/')
}

export default UserProfile;
