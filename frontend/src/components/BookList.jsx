import React from 'react';
import BookCard from './BookCard';
import { Grid } from '@mui/material';
import {v4} from "uuid"

function BookList({ books }) {
  return (
    <Grid container spacing={2}>
      {books.map((book) => (
        <Grid item key={v4()} xs={12} sm={6} md={4} lg={3}>
            <BookCard book={book} />
        </Grid>
      ))}
    </Grid>
  );
}

export default BookList;