package br.com.meutrabalho.trabalho.servico;

import org.springframework.data.repository.CrudRepository;

import br.com.meutrabalho.trabalho.usuario.User;

public interface ServicoRepository extends CrudRepository<Servico, Long> {
	public Iterable<Servico> findByUser(User user);

	public Iterable<Servico> findByUserAndActive(User user, int active);

	public Servico findOneByCdServico(Long cdServico);
}