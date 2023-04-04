import React from "react";
import { ThoughtsPage } from "./pages/thoughtsPage/ThoughtsPage";
import { IndexPage } from "./pages/indexPage/IndexPage";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Naviagtion from "./components/UI/Navigation";

function App() {
  return (
    <>
      <Naviagtion />
      <Routes>
        <Route path="/" element={<IndexPage />} />
        <Route path="/new" element={<ThoughtsPage />} />
      </Routes>
    </>
  );
}

export default App;
