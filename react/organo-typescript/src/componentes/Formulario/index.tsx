import './Formulario.css';
import CampoTexto from '../CampoTexto/index.js';
import ListaSuspensa from '../ListaSuspensa/index.js';
import Botao from '../Botao/index.js';
import { useState } from 'react';
import type { IColaborador } from '../../shared/interfaces/IColaborador.js';

interface FormularioProps {
    aoColaboradorCadastrado: (colaborador: IColaborador) => void;
    times: string[];
}

const Formulario = (props: FormularioProps) => {
    const [nome, setNome] = useState('')
    const [cargo, setCargo] = useState('')
    const [imagem, setImagem] = useState('')
    const [time, setTime] = useState('')
    const [data, setData] = useState('')

    const aoSalvar = (evento: React.FormEvent<HTMLFormElement>) => {
        evento.preventDefault()
        props.aoColaboradorCadastrado({
            nome: nome,
            cargo: cargo,
            imagem: imagem,
            time: time,
            data: data
        })
        setNome('')
        setCargo('')
        setImagem('')
        setTime('')
    }

    return (
        <section className='formulario'>
            <form onSubmit={aoSalvar}>
                <h2>Preencha os dados para criar o card do colaborador.</h2>
                <CampoTexto 
                obrigatorio={true} 
                label="Nome" 
                placeholder="Digite seu nome" 
                valor={nome}
                aoAlterado={valor => setNome(valor)}
                />
                
                <CampoTexto 
                obrigatorio={true} 
                label="Cargo" 
                placeholder="Digite seu cargo" 
                valor={cargo}
                aoAlterado={cargo => setCargo(cargo)}
                />

                <CampoTexto
                label="Imagem" 
                placeholder="Informe o endereço da imagem" 
                valor={imagem}
                aoAlterado={imagem => setImagem(imagem)}
                />

                <CampoTexto
                label="Data de entrafa no time" 
                placeholder="" 
                valor={data}
                aoAlterado={valor => setData(valor)}
                tipo='date'
                />

                <ListaSuspensa 
                obrigatorio={true} 
                label="Time" 
                itens={props.times}
                valor={time}
                aoAlterado={time => setTime(time)}
                />
                <Botao>
                    Criar card
                </Botao>
            </form>
        </section>
    )
}

export default Formulario