import React from "react";
import styles from './SearchBar.module.scss'


export const SearchBar = () => {
    return (
      <form className={styles.search}>
        <input type='search' placeholder="Search your thoughts 💬 " className={styles.children}/>
        <input type='submit' className={styles.submit}/>
      </form>
    )
}