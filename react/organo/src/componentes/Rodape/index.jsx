import './Rodape.css'

const Rodape = () => {
    return (
        <footer>
            <div className='icones'>
                <a href="https://www.facebook.com/" target="_blank">
                    <img src="../../../public/imagens/fb.png" alt="icone Facebook" />
                </a>
                <a href="https://x.com/home" target='_blank'>
                    <img src="../../../public/imagens/tw.png" alt="icone Twitter" />
                </a>
                <a href="https://www.instagram.com/" target='_blank'>
                    <img src="../../../public/imagens/ig.png" alt="icone Instagram" />
                </a>
            </div>
            <div>
                <img src="../../../public/imagens/logo.png" alt="icone Organo" />
            </div>
            <div className='texto'>
                <p>Desenvolvido por <a href="https://github.com/felipesora" target='_blank'>Felipe Sora</a></p>
            </div>
        </footer>
    )
}

export default Rodape