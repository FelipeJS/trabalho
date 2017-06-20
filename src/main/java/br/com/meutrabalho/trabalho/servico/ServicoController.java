package br.com.meutrabalho.trabalho.servico;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.meutrabalho.trabalho.usuario.User;
import br.com.meutrabalho.trabalho.usuario.UserService;

@RestController
@RequestMapping("/servico")
public class ServicoController {

	@Autowired
	private ServicoRepository servicoRepository;

	@Autowired
	private UserService userService;

	public User getUsuarioLogado() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return userService.findUserByEmail(auth.getName());
	}

	@RequestMapping(value = "/salvar", method = POST)
	public Servico salvar(@RequestBody Servico servico) {
		servico.setUser(getUsuarioLogado());
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
		return servicoRepository.findByUserAndActive(getUsuarioLogado(), 1);
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