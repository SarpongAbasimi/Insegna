import React from 'react';
import { ThoughtsPage } from './pages/thoughtsPage/ThoughtsPage';
import {IndexPage} from './pages/indexPage/IndexPage';
import { BrowserRouter,  Routes, Route } from "react-router-dom";
import './App.module.scss'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route>
          <Route path="/" element={<IndexPage/>} />
          <Route path="/new" element={<ThoughtsPage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;