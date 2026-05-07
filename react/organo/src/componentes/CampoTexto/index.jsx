import "./CampoTexto.css"

const CampoTexto = (props) => {
    
    // let valor = 'Felipe Sora'

    // const [valor, setValor] = useState('')

    const aoDigitado = (evento) => {
        // setValor(evento.target.value)
        // console.log(valor);
        props.aoAlterado(evento.target.value)
    }

    return (
        <div className="campo-texto">
            <label>{props.label}</label>

            <input value={props.valor} onChange={aoDigitado} type="text" required={props.obrigatorio} placeholder={props.placeholder}/>
        </div>
    )
}

export default CampoTexto