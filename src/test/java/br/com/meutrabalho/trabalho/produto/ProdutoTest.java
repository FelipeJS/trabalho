package br.com.meutrabalho.trabalho.produto;

import java.math.BigDecimal;

import br.com.meutrabalho.trabalho.enumerador.Ativo;
import br.com.meutrabalho.trabalho.usuario.User;
import br.com.meutrabalho.trabalho.usuario.UserRepository;

public class ProdutoTest {

	public void salvarProduto(ProdutoRepository produtoRepository, UserRepository userRepository) {
		Produto produto = new Produto();
		produto.setNome("Camiseta GG");
		produto.setDescricao("Camiseta de programador tamanho GG");
		produto.setPreco(new BigDecimal("150.00"));
		produto.setAtivo(Ativo.SIM.toString());

		User usuario = userRepository.findOneById(1L);
		produto.setUsuario(usuario);

		produtoRepository.save(produto);
	}
}