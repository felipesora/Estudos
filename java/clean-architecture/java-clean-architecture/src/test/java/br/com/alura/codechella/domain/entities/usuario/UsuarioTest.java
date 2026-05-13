package br.com.alura.codechella.domain.entities.usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    public void naoDeveCadastrarUsuarioComCpfNoFormatoInvalido() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Usuario("123456789-99", "Felipe", LocalDate.parse("2005-10-13"), "felipe@email.com"));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Usuario("12345678999", "Felipe", LocalDate.parse("2005-10-13"), "felipe@email.com"));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Usuario("", "Felipe", LocalDate.parse("2005-10-13"), "felipe@email.com"));
    }

    @Test
    public void deveCriarUsuarioUsandoFabricaDeUsuario() {
        FabricaDeUsuario fabrica = new FabricaDeUsuario();
        Usuario usuario = fabrica.comNomeCpfNascimento("Felipe", "123.456.789-00", LocalDate.parse("2005-10-13"));

        Assertions.assertEquals("Felipe", usuario.getNome());

        usuario = fabrica.incluiEndereco("12345-99", 40, "apto 133");

        Assertions.assertEquals("apto 133", usuario.getEndereco().getComplemento());
    }
}