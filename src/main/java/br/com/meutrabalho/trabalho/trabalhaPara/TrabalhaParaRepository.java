package br.com.meutrabalho.trabalho.trabalhaPara;

import org.springframework.data.repository.CrudRepository;

import br.com.meutrabalho.trabalho.usuario.User;

public interface TrabalhaParaRepository extends CrudRepository<TrabalhaPara, Long> {
	public Iterable<TrabalhaPara> findByUserFuncionario(User userFuncionario);

	public TrabalhaPara findOneByUserFuncionarioAndUserEmpresa(User userFuncionario, User userEmpresa);

	public Iterable<TrabalhaPara> findByUserEmpresa(User userEmpresa);
}