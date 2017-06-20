package br.com.meutrabalho.trabalho.comentarioSolicitacao;

import org.springframework.data.repository.CrudRepository;

import br.com.meutrabalho.trabalho.solicitacao.Solicitacao;


public interface ComentarioSolicitacaoRepository extends CrudRepository<ComentarioSolicitacao, Long> {
	public Iterable<ComentarioSolicitacao> findBySolicitacao(Solicitacao solicitacao);
}