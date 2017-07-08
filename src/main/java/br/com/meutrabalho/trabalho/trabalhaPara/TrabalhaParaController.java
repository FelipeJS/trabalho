package br.com.meutrabalho.trabalho.trabalhaPara;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.meutrabalho.trabalho.usuario.User;
import br.com.meutrabalho.trabalho.usuario.UserLogado;
import br.com.meutrabalho.trabalho.usuario.UserService;

@CrossOrigin
@RestController
@RequestMapping("/trabalhaPara")
public class TrabalhaParaController {
	@Autowired
	private TrabalhaParaRepository trabalhaParaRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private UserLogado userLogado;
	
	@RequestMapping("/listar")
	public Iterable<TrabalhaPara> listar() {
		return trabalhaParaRepository.findByUserFuncionario(userLogado.getUsuarioLogado());
	}

	@RequestMapping(value = "/consultar", method = GET)
	public Boolean consultar(@RequestParam Long cdUsuarioFuncionario) {
		User userFuncionario = userService.findUserById(cdUsuarioFuncionario);
		TrabalhaPara trabalhaPara = trabalhaParaRepository.findOneByUserFuncionarioAndUserEmpresa(userFuncionario,
				userLogado.getUsuarioLogado());
		if (trabalhaPara == null) {
			return false;
		} else {
			return true;
		}
	}

	@RequestMapping("/listarFuncionarios")
	public Iterable<User> listarFuncionarios() {
		Iterable<TrabalhaPara> funcionarios = trabalhaParaRepository.findByUserEmpresa(userLogado.getUsuarioLogado());
		ArrayList<Long> ids = new ArrayList<>();
		for (TrabalhaPara funcionario : funcionarios) {
			ids.add(funcionario.getUserFuncionario().getId());
		}
		return userService.findAllById(ids);
	}

	@RequestMapping(value = "/salvar", method = GET)
	public TrabalhaPara salvar(@RequestParam Long cdUsuarioFuncionario) {
		TrabalhaPara trabalhaPara = new TrabalhaPara();

		trabalhaPara.setUserEmpresa(userLogado.getUsuarioLogado());
		trabalhaPara.setUserFuncionario(userService.findUserById(cdUsuarioFuncionario));
		return trabalhaParaRepository.save(trabalhaPara);
	}

	@RequestMapping(value = "/excluir", method = GET)
	public Long excluir(@RequestParam Long cdUsuarioFuncionario) {
		User userFuncionario = userService.findUserById(cdUsuarioFuncionario);
		User userEmpresa = userLogado.getUsuarioLogado();

		TrabalhaPara trabalhaPara = trabalhaParaRepository.findOneByUserFuncionarioAndUserEmpresa(userFuncionario,
				userEmpresa);

		trabalhaParaRepository.delete(trabalhaPara);
		return trabalhaPara.getCdTrabalhaPara();
	}
}