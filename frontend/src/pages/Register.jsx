import React, { useState } from 'react';
import { TextField, Button, Grid, FormControl, FormLabel, RadioGroup, FormControlLabel, Radio } from '@mui/material';
import { Navigate } from "react-router-dom";
import axios from 'axios';


function Register() {
  const [contact, setContact] = useState('');
  const [password, setPassword] = useState('');
  const [contactType, setContactType] = useState('email');
  const [redirect, setRedirect] = useState(false)

  const handleContactChange = (event) => {
    setContact(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleContactTypeChange = (event) => {
    setContactType(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    setContact("")
    setPassword("")
  };

  const handleLoginRedirect = () => {
    setRedirect(true);
  };

  return (
    <form onSubmit={handleSubmit}>
      <Grid container justifyContent="center" alignItems="center" style={{ height: '100vh' }}>
        <Grid item>
          <Grid container direction="column" spacing={2}>
            <Grid item>
              <h1>Register</h1>
            </Grid>
            <Grid item>
              <FormControl component="fieldset">
                <FormLabel component="legend">Contact Type</FormLabel>
                <RadioGroup
                  aria-label="contact-type"
                  name="contactType"
                  value={contactType}
                  onChange={handleContactTypeChange}
                >
                  <FormControlLabel value="email" control={<Radio />} label="Email" />
                  <FormControlLabel value="phone" control={<Radio />} label="Phone Number" />
                </RadioGroup>
              </FormControl>
            </Grid>
            <Grid item>
              {contactType === 'email' ? (
                <TextField
                  label="Email"
                  type="email"
                  value={contact}
                  onChange={handleContactChange}
                  required
                />
              ) : (
                <TextField
                  label="Phone Number"
                  type="tel"
                  value={contact}
                  onChange={handleContactChange}
                  required
                />
              )}
            </Grid>
            <Grid item>
              <TextField
                label="Password"
                type="password"
                value={password}
                onChange={handlePasswordChange}
                required
              />
            </Grid>
            <Grid item>
              <Button type="submit" variant="contained" color="primary" fullWidth>
                Register
              </Button>
            </Grid>
          </Grid>
        </Grid>
        <Grid item style={{ position: 'absolute', top: 10, right: 10 }}>
          <Button onClick={handleLoginRedirect} variant="outlined" color="primary">
            Login
          </Button>
          {redirect && <Navigate to={"/"}/>}
        </Grid>
      </Grid>
    </form>
  );
}

export default Register;