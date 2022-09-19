import React from "react";
import styles from "./SearchBar.module.scss";
import { SerchBarProp } from "../../utils/utls";

export const SearchBar = ({ placeHolder }: SerchBarProp) => {
  return (
    <form className={styles.form}>
      <input
        type="search"
        placeholder={placeHolder}
        className={styles["form__input"]}
      />
      <button type="submit" className={styles["form__button"]}>
        Submit
      </button>
    </form>
  );
};
