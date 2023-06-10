import {configureStore} from '@reduxjs/toolkit'

const initialState = {
    products: [],
    userAccount: null,
}

const ADD_PRODUCT = 'ADD_PRODUCT'
const LOG_USER = 'LOG_USER'
const LOG_OUT = 'LOG_OUT'

function modalReducer(state = initialState, action){
    switch(action.type) {
        case ADD_PRODUCT:
            return {
                ...state,
                products: [...state.products, action.payload],
            };
        case LOG_USER:
            return {
                ...state,
                userAccount: action.payload,
            }
        case LOG_OUT:
            return{
                ...state,
                userAccount: null
            }
        default:
            return state;
    }
}

const store = configureStore({
    reducer: modalReducer
});

export default store