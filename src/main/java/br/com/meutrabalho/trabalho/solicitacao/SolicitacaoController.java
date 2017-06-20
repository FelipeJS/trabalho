package br.com.meutrabalho.trabalho.solicitacao;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/solicitacao")
public class SolicitacaoController {
	@Autowired
	SolicitacaoService solicitacaoService;

	@RequestMapping(value = "/salvar", method = GET)
	public Solicitacao salvar(@RequestParam String descricao, @RequestParam Long cdServico) {
		return solicitacaoService.salvar(descricao, cdServico);
	}

	@RequestMapping(value = "/aceitar", method = POST)
	public Solicitacao aceitar(@RequestBody Solicitacao solicitacao) {
		return solicitacaoService.aceitar(solicitacao);
	}

	@RequestMapping(value = "/finalizar", method = POST)
	public Solicitacao finalizar(@RequestBody Solicitacao solicitacao) {
		return solicitacaoService.finalizar(solicitacao);
	}

	@RequestMapping(value = "/recusar", method = POST)
	public Solicitacao recusar(@RequestBody Solicitacao solicitacao) {
		return solicitacaoService.recusar(solicitacao);
	}

	@RequestMapping(value = "/listarAbertos", method = GET)
	public Iterable<Solicitacao> listarAbertos() {
		return solicitacaoService.listarAbertos();
	}

	@RequestMapping(value = "/listarAnalisados", method = GET)
	public Iterable<Solicitacao> listarAnalisados() {
		return solicitacaoService.listarAnalisados();
	}

	@RequestMapping(value = "/listarFechados", method = GET)
	public Iterable<Solicitacao> listarFechados() {
		return solicitacaoService.listarFechados();
	}

	@RequestMapping(value = "/listarMinhasSolicitacoes", method = GET)
	public Iterable<Solicitacao> listarMinhasSolicitacoes() {
		return solicitacaoService.listarMinhasSolicitacoes();
	}
}