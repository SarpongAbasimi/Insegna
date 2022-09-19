import React from "react";
import { SearchBar } from "../../components/searchBar/SearchBar";
import styles from "./IndexPage.module.scss";

export const IndexPage = () => {
  return (
    <div className={styles.main}>
      <div className={styles.search}>
        <SearchBar placeHolder={"Search your thoughts 💬"} />
      </div>
    </div>
  );
};
