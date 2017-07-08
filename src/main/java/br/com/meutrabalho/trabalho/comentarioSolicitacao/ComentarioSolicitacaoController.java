package br.com.meutrabalho.trabalho.comentarioSolicitacao;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.meutrabalho.trabalho.solicitacao.Solicitacao;
import br.com.meutrabalho.trabalho.solicitacao.SolicitacaoRepository;
import br.com.meutrabalho.trabalho.usuario.UserLogado;

@CrossOrigin
@RestController
@RequestMapping("/comentarioSolicitacao")
public class ComentarioSolicitacaoController {

	@Autowired
	private SolicitacaoRepository solicitacaoRepository;

	@Autowired
	private ComentarioSolicitacaoRepository comentarioSolicitacaoRepository;

	@Autowired
	private UserLogado userLogado;

	@RequestMapping(value = "/listar", method = GET)
	public Iterable<ComentarioSolicitacao> listar(@RequestParam Long cdSolicitacao) {
		Solicitacao solicitacao = solicitacaoRepository.findOneByCdSolicitacao(cdSolicitacao);
		return comentarioSolicitacaoRepository.findBySolicitacao(solicitacao);
	}

	@RequestMapping(value = "/salvar", method = GET)
	public ComentarioSolicitacao salvar(@RequestParam Long cdSolicitacao, @RequestParam String descricao) {
		Solicitacao solicitacao = solicitacaoRepository.findOneByCdSolicitacao(cdSolicitacao);

		ComentarioSolicitacao comentarioSolicitacao = new ComentarioSolicitacao();
		comentarioSolicitacao.setSolicitacao(solicitacao);
		comentarioSolicitacao.setDescricao(descricao);
		comentarioSolicitacao.setDhComentario(new Date());

		comentarioSolicitacao.setUser(userLogado.getUsuarioLogado());

		return comentarioSolicitacaoRepository.save(comentarioSolicitacao);
	}
}