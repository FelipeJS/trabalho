package br.com.meutrabalho.trabalho.user;

import br.com.meutrabalho.trabalho.usuario.User;
import br.com.meutrabalho.trabalho.usuario.UserRepository;

public class UserTest {

	public void salvarUsuario(UserRepository userRepository) {
		User usuario = new User();
		usuario.setName("Felipe Ferreira Campos");
		usuario.setTelefone("62 993038153");
		usuario.setDocumento("5411582");
		usuario.setBairro("Recanto do Bosque");
		usuario.setEmail("tecnologiagrave@gmail.com");
		usuario.setEmpresa("AGIR");
		usuario.setDepartamento("TI");
		usuario.setCidade("Goiânia");
		usuario.setEstado("GO");
		usuario.setPassword("123456789");
		usuario.setEndereco("Rua RB04");
		
		userRepository.save(usuario);
	}
}