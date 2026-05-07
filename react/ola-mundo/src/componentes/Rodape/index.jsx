import styles from "./Rodape.module.css";
import marca from "../../assets/marca_registrada.svg";

export default function Rodape() {
  return (
    <footer className={styles.rodape}>
        <img src={marca} alt="Marca Registrada" />

        Desenvolvido por Felipe Sora
    </footer>
  );
}