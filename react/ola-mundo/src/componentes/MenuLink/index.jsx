import { Link, useLocation } from "react-router-dom";
import styles from "./MenuLink.module.css";

export default function MenuLink({ children, to }) {
  const localizacao = useLocation();

  return (
    <Link
      to={to}
      className={`${styles.link} ${
        localizacao.pathname === to ? styles.linkDestacado : ""
      }`}
    >
      {children}
    </Link>
  );
}
