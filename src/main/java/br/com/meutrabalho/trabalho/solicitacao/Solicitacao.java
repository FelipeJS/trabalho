package br.com.meutrabalho.trabalho.solicitacao;

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

import br.com.meutrabalho.trabalho.servico.Servico;
import br.com.meutrabalho.trabalho.usuario.User;

@Entity
public class Solicitacao implements Serializable {

	private static final long serialVersionUID = 682716510341623861L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cd_solicitacao")
	private Long cdSolicitacao;

	@ManyToOne
	@JoinColumn(name = "cd_servico", referencedColumnName = "cd_servico")
	private Servico servico;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;

	@OneToOne
	@JoinColumn(name = "user_id_analise", referencedColumnName = "user_id")
	private User userAnalise;

	@Column(name = "dh_solicitacao")
	private Date dhSolicitacao;

	private String descricao;

	private int status;

	@Column(name = "motivo_recusado")
	private String motivoRecusado;

	public Long getCdSolicitacao() {
		return cdSolicitacao;
	}

	public void setCdSolicitacao(Long cdSolicitacao) {
		this.cdSolicitacao = cdSolicitacao;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUserAnalise() {
		return userAnalise;
	}

	public void setUserAnalise(User userAnalise) {
		this.userAnalise = userAnalise;
	}

	public Date getDhSolicitacao() {
		return dhSolicitacao;
	}

	public void setDhSolicitacao(Date dhSolicitacao) {
		this.dhSolicitacao = dhSolicitacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMotivoRecusado() {
		return motivoRecusado;
	}

	public void setMotivoRecusado(String motivoRecusado) {
		this.motivoRecusado = motivoRecusado;
	}
}
