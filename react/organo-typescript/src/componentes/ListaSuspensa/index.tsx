import './ListaSuspensa.css'

interface ListaSuspensaProps {
  aoAlterado: (valor: string) => void;
  label: string;
  obrigatorio: boolean;
  valor: string;
  itens: string[]
}

const ListaSuspensa = (props: ListaSuspensaProps) => {
    console.log(props.itens)

    return (
        <div className='lista-suspensa'>
            <label>{props.label}</label>
            <select onChange={evento => props.aoAlterado(evento.target.value)} required={props.obrigatorio} value={props.valor}>
                <option value="" disabled>Selecione um time</option>
                {props.itens.map(item => <option key={item}>{item}</option>)}
            </select>
        </div>
    )
}

export default ListaSuspensa