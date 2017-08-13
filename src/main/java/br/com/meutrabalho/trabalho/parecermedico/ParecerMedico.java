package br.com.meutrabalho.trabalho.parecermedico;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.com.meutrabalho.trabalho.generico.GenericoDomain;

@Entity
public class ParecerMedico extends GenericoDomain {
	private static final long serialVersionUID = -5517968355586170266L;

	private String status;
	private String prontuario;
	@Column(name = "DT_SOLICITACAO")
	private Date dtSolicitacao;
	private String leito;
	private String unidadeInternacao;
	private String nomePaciente;
	private String especialidade;
	private String prestadorSolicitante;
	private String prestadorResposta;
	private Date dtResposta;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProntuario() {
		return prontuario;
	}

	public void setProntuario(String prontuario) {
		this.prontuario = prontuario;
	}

	public Date getDtSolicitacao() {
		return dtSolicitacao;
	}

	public void setDtSolicitacao(Date dtSolicitacao) {
		this.dtSolicitacao = dtSolicitacao;
	}

	public String getLeito() {
		return leito;
	}

	public void setLeito(String leito) {
		this.leito = leito;
	}

	public String getUnidadeInternacao() {
		return unidadeInternacao;
	}

	public void setUnidadeInternacao(String unidadeInternacao) {
		this.unidadeInternacao = unidadeInternacao;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getPrestadorSolicitante() {
		return prestadorSolicitante;
	}

	public void setPrestadorSolicitante(String prestadorSolicitante) {
		this.prestadorSolicitante = prestadorSolicitante;
	}

	public String getPrestadorResposta() {
		return prestadorResposta;
	}

	public void setPrestadorResposta(String prestadorResposta) {
		this.prestadorResposta = prestadorResposta;
	}

	public Date getDtResposta() {
		return dtResposta;
	}

	public void setDtResposta(Date dtResposta) {
		this.dtResposta = dtResposta;
	}
}