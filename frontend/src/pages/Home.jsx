import React from 'react';
import Navbar from '../components/Navbar';
import books from '../components/asserts/items';
import BookList from '../components/BookList';

function Home(){
    return (
        <div>
            <Navbar/>
            <BookList books={books}/>
        </div>
    );
};

export default Home;
