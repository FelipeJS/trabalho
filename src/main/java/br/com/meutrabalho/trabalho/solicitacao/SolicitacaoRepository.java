package br.com.meutrabalho.trabalho.solicitacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.meutrabalho.trabalho.servico.Servico;
import br.com.meutrabalho.trabalho.usuario.User;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {
	Iterable<Solicitacao> findByUserOrderByUserDesc(Iterable<User> users);

	Iterable<Solicitacao> findByUserOrderByCdSolicitacaoDesc(User user);

	Solicitacao findOneByCdSolicitacao(Long cdSolicitacao);

	Iterable<Solicitacao> findByServicoInOrderByCdSolicitacaoDesc(Iterable<Servico> servicos);

	@Query(value = "SELECT S.*, V.nome, U.* FROM servico V INNER JOIN solicitacao S ON S.CD_SERVICO = V.CD_SERVICO "
			+ "INNER JOIN user U ON U.USER_ID = S.USER_ID "
			+ "WHERE V.USER_ID = ?1 AND S.STATUS = ?2 ORDER BY S.CD_SOLICITACAO DESC", nativeQuery = true)
	Iterable<Solicitacao> findByUserIdAndStatus(Long userId, int status);
	
	@Query(value = "SELECT count(*) FROM servico V INNER JOIN solicitacao S ON S.CD_SERVICO = V.CD_SERVICO "
			+ "INNER JOIN user U ON U.USER_ID = S.USER_ID "
			+ "WHERE V.USER_ID = ?1 AND S.STATUS = ?2", nativeQuery = true)
	Long countByUserIdAndStatus(Long userId, int status);
}