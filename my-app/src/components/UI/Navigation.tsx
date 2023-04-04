import React from "react";
import { NavLink, Link } from "react-router-dom";
import styles from "./Navigation.module.scss";

const Naviagtion = () => {
  return (
    <header className={styles.header}>
      <div className={styles["header__container"]}>
        <div className={styles.logo}>
          <Link className={styles["logo__link"]} to="/">
            Insegna
          </Link>
        </div>

        <ul className={styles.nav}>
          <li className={styles["nav__item"]}>
            <NavLink className={styles["nav__link"]} to="/">
              Explore
            </NavLink>
          </li>
          <li className={styles["nav__item"]}>
            <NavLink className={styles["nav__link"]} to="/new">
              New Entry
            </NavLink>
          </li>
        </ul>
      </div>
    </header>
  );
};

export default Naviagtion;
