package br.com.meutrabalho.trabalho.produto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.meutrabalho.trabalho.generico.GenericoDomain;
import br.com.meutrabalho.trabalho.usuario.User;

@Entity
public class Produto extends GenericoDomain {
	private static final long serialVersionUID = 1722442994822860044L;

	@Column(name = "NOME", length = 80, nullable = false)
	private String nome;

	@Column(name = "DESCRICAO")
	private String descricao;

	@Column(name = "PRECO", precision = 11, scale = 2)
	private BigDecimal preco;

	@ManyToOne
	@JoinColumn(nullable = false)
	private User usuario;

	@Column(name = "ATIVO")
	private String ativo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
}