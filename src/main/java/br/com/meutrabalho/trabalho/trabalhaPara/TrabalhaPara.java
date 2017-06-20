package br.com.meutrabalho.trabalho.trabalhaPara;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.meutrabalho.trabalho.usuario.User;

@Entity
public class TrabalhaPara implements Serializable {

	private static final long serialVersionUID = -4449965105605658752L;

	@Id
	@Column(name = "cd_trabalha_para")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cdTrabalhaPara;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User userEmpresa;

	@ManyToOne
	@JoinColumn(name = "user_id_funcionario", referencedColumnName = "user_id")
	private User userFuncionario;

	public Long getCdTrabalhaPara() {
		return cdTrabalhaPara;
	}

	public void setCdTrabalhaPara(Long cdTrabalhaPara) {
		this.cdTrabalhaPara = cdTrabalhaPara;
	}

	public User getUserEmpresa() {
		return userEmpresa;
	}

	public void setUserEmpresa(User userEmpresa) {
		this.userEmpresa = userEmpresa;
	}

	public User getUserFuncionario() {
		return userFuncionario;
	}

	public void setUserFuncionario(User userFuncionario) {
		this.userFuncionario = userFuncionario;
	}
}
