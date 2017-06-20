package br.com.meutrabalho.trabalho.trabalhaPara;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.meutrabalho.trabalho.usuario.User;
import br.com.meutrabalho.trabalho.usuario.UserService;

@RestController
@RequestMapping("/trabalhaPara")
public class TrabalhaParaController {
	@Autowired
	private TrabalhaParaRepository trabalhaParaRepository;

	@Autowired
	private UserService userService;

	public User getUsuarioLogado() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return userService.findUserByEmail(auth.getName());
	}

	@RequestMapping("/listar")
	public Iterable<TrabalhaPara> listar() {
		return trabalhaParaRepository.findByUserFuncionario(getUsuarioLogado());
	}

	@RequestMapping(value = "/consultar", method = GET)
	public Boolean consultar(@RequestParam Long cdUsuarioFuncionario) {
		User userFuncionario = userService.findUserById(cdUsuarioFuncionario);
		TrabalhaPara trabalhaPara = trabalhaParaRepository.findOneByUserFuncionarioAndUserEmpresa(userFuncionario,
				getUsuarioLogado());
		if (trabalhaPara == null) {
			return false;
		} else {
			return true;
		}
	}

	@RequestMapping("/listarFuncionarios")
	public Iterable<User> listarFuncionarios() {
		Iterable<TrabalhaPara> funcionarios = trabalhaParaRepository.findByUserEmpresa(getUsuarioLogado());
		ArrayList<Long> ids = new ArrayList<>();
		for (TrabalhaPara funcionario : funcionarios) {
			ids.add(funcionario.getUserFuncionario().getId());
		}
		return userService.findAllById(ids);
	}

	@RequestMapping(value = "/salvar", method = GET)
	public TrabalhaPara salvar(@RequestParam Long cdUsuarioFuncionario) {
		TrabalhaPara trabalhaPara = new TrabalhaPara();

		trabalhaPara.setUserEmpresa(getUsuarioLogado());
		trabalhaPara.setUserFuncionario(userService.findUserById(cdUsuarioFuncionario));
		return trabalhaParaRepository.save(trabalhaPara);
	}

	@RequestMapping(value = "/excluir", method = GET)
	public Long excluir(@RequestParam Long cdUsuarioFuncionario) {
		User userFuncionario = userService.findUserById(cdUsuarioFuncionario);
		User userEmpresa = getUsuarioLogado();

		TrabalhaPara trabalhaPara = trabalhaParaRepository.findOneByUserFuncionarioAndUserEmpresa(userFuncionario,
				userEmpresa);

		trabalhaParaRepository.delete(trabalhaPara);
		return trabalhaPara.getCdTrabalhaPara();
	}
}