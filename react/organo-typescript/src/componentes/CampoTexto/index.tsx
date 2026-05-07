import type React from "react";
import "./CampoTexto.css"

interface CampoTextoProps {
    aoAlterado: (valor: string) => void;
    label: string;
    valor: string;
    placeholder: string;
    obrigatorio?: boolean;
    tipo?: 'text' | 'password' | 'date' | 'email' | 'number'
}

const CampoTexto = ({ aoAlterado, label, valor, placeholder, obrigatorio = false, tipo = 'text' }: CampoTextoProps) => {

    const aoDigitado = (evento: React.ChangeEvent<HTMLInputElement>) => {
        aoAlterado(evento.target.value)
    }

    return (
        <div className="campo-texto">
            <label>{label}</label>

            <input 
                value={valor} 
                onChange={aoDigitado} 
                type={tipo} 
                required={obrigatorio} 
                placeholder={placeholder}
            />
        </div>
    )
}

export default CampoTexto