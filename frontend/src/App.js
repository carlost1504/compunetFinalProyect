import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Register from './pages/Register';
import Login from './pages/Login';
import Home from './pages/Home';

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route exact path="/register" element={<Register />} />
          <Route exact path="/" element={<Login />} />
          <Route exact path="/home" element={<Home/>}></Route>
        </Routes>
      </Router>
    </div>
  );
}

export default App;
