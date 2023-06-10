import React, { useState } from 'react';
import { AppBar, Toolbar, Typography, Button, IconButton, Badge } from '@mui/material';
import { AccountCircle, ShoppingCart } from '@mui/icons-material';
import UserProfile from './UserProfile';
import { useDispatch, useSelector } from 'react-redux';
import { Navigate } from 'react-router-dom';

function Navbar() {
    
    const products = useSelector((state) => state.products)
    const [showProfile, setShowProfile] = useState(false);
    
    const userLog = useSelector((state) => state.userAccount)
    const dispatch = useDispatch()

    const handleLogout = () => {
        dispatch({type: 'LOG_OUT'})
    };

    const handleProfile = () => {
        setShowProfile(true)
    };

    const handleCart = () => {
        console.log('Go to shopping cart');
    };

    return (
        <div>
            <AppBar position="static">
                    <Toolbar>
                        <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                            Book Shop
                        </Typography>

                        {userLog ? (
                        <div>
                            <Button color="inherit" startIcon={<AccountCircle />} onClick={handleProfile}>
                                {userLog.email ? (userLog.email):(userLog.phoneNumber)}
                            </Button>
                            <Button color="inherit" onClick={handleLogout}>
                                Logout
                            </Button>
                        </div>
                        ) : (
                        <Button color="inherit" startIcon={<AccountCircle />} onClick={handleProfile}>
                            Login
                        </Button>
                        )}

                        <IconButton color="inherit" onClick={handleCart}>
                        <Badge badgeContent={products.length} color="error">
                            <ShoppingCart />
                        </Badge>
                        </IconButton>
                    </Toolbar>
                    </AppBar>
            {showProfile && <UserProfile user={userLog} isVisible={setShowProfile}/>}
            {userLog ? (<div></div>):(<Navigate to={"/"}/>)}
        </div>
        
    );
}

export default Navbar;