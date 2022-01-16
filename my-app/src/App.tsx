import React from 'react';
import './App.css';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import {IndexPage} from './pages/indexPage/IndexPage';
import {ThoughtsPage} from './pages/thoughtsPages/ThoughtsPage';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<IndexPage/>}></Route>
        <Route path='/new' element={<ThoughtsPage/>}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
