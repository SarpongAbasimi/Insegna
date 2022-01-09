import React from "react";
import styles from './SearchBar.module.scss';
import {SerchBarProp} from '../../utils/utls';

export const SearchBar = ({placeHolder}: SerchBarProp) => {
    return (
      <form className={styles.search}>
        <input type='search' placeholder={placeHolder} className={styles.children}/>
        <input type='submit' className={styles.submit}/>
      </form>
    )
}