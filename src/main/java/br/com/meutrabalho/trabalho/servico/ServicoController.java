package br.com.meutrabalho.trabalho.servico;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.meutrabalho.trabalho.usuario.User;
import br.com.meutrabalho.trabalho.usuario.UserLogado;
import br.com.meutrabalho.trabalho.usuario.UserService;

@CrossOrigin
@RestController
@RequestMapping("/servico")
public class ServicoController {

	@Autowired
	private ServicoRepository servicoRepository;

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserLogado userLogado;

	@RequestMapping(value = "/salvar", method = POST)
	public Servico salvar(@RequestBody Servico servico) {
		servico.setUser(userLogado.getUsuarioLogado());
		return servicoRepository.save(servico);
	}

	@RequestMapping(value = "/excluir", method = GET)
	public Long excluir(@RequestParam Long cdServico) {
		Servico servico = servicoRepository.findOneByCdServico(cdServico);
		servico.setActive(0);
		servicoRepository.save(servico);
		return servico.getCdServico();
	}

	@RequestMapping("/listar")
	public Iterable<Servico> listar() {
		return servicoRepository.findByUserAndActive(userLogado.getUsuarioLogado(), 1);
	}

	@RequestMapping(value = "/listarPorUsuario", method = GET)
	public Iterable<Servico> listarByUser(@RequestParam Long userId) {
		User user = userService.findUserById(userId);
		return servicoRepository.findByUserAndActive(user, 1);
	}

	@RequestMapping(value = "/consultar", method = GET)
	public Servico consultar(@RequestParam Long cdServico) {
		return servicoRepository.findOneByCdServico(cdServico);
	}
}