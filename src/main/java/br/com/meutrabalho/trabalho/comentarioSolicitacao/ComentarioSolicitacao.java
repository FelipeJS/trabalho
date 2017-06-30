package br.com.meutrabalho.trabalho.comentarioSolicitacao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.com.meutrabalho.trabalho.solicitacao.Solicitacao;
import br.com.meutrabalho.trabalho.usuario.User;

@Entity
public class ComentarioSolicitacao implements Serializable {

	private static final long serialVersionUID = -6122500130963764208L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cd_comentario_solicitacao")
	private Long cdComentarioSolicitacao;

	@ManyToOne
	@JoinColumn(name = "cd_solicitacao", referencedColumnName = "cd_solicitacao")
	private Solicitacao solicitacao;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;

	private String descricao;

	@Column(name = "dh_solicitacao")
	private Date dhComentario;

	public Long getCdComentarioSolicitacao() {
		return cdComentarioSolicitacao;
	}

	public void setCdComentarioSolicitacao(Long cdComentarioSolicitacao) {
		this.cdComentarioSolicitacao = cdComentarioSolicitacao;
	}

	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDhComentario() {
		return dhComentario;
	}

	public void setDhComentario(Date dhComentario) {
		this.dhComentario = dhComentario;
	}
}
