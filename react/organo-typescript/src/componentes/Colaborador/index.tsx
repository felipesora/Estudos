import './Colaborador.css'

interface ColaboradorProps {
    nome: string;
    imagem: string;
    cargo: string;
    cor: string;
    data: string;
}

const Colaborador = ( {nome, imagem, cargo, data, cor}: ColaboradorProps ) => {
    return (
        <div className='colaborador'>
            <div className='cabecalho' style={{backgroundColor: cor}}>
                <img src={imagem} alt={nome} />
            </div>
            <div className='rodape'>
                <h4>{nome}</h4>
                <h5>{cargo}</h5>
                <h5>{new Date(data).toLocaleDateString()}</h5>
            </div>
        </div>
    )
}

export default Colaborador