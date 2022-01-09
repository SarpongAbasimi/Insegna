import React from 'react';
import { ThoughtsPage } from './pages/ThoughtsPage';
 import styles from './App.module.scss';
 import {SearchBar} from './components/searchBar/SearchBar'

function App() {
  return (
    <div className={styles.App}>
      <ThoughtsPage/>
      <SearchBar/>
    </div>
  );
}

export default App;
