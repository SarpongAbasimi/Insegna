import React from 'react';
import {SearchBar} from '../../components/searchBar/SearchBar';
import styles from './IndexPage.module.scss'
import {Link} from 'react-router-dom'

export const IndexPage = () => {
    return(
        <div className={styles.main}>
            <li className={styles.new}>
                <Link to ="/new">➕New</Link>
            </li>
            <div className={styles.search}>
                <SearchBar placeHolder={"Search your thoughts 💭"}/>
            </div>
        </div>
    )
}