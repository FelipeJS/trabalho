package br.com.meutrabalho.trabalho.relatorio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import br.com.meutrabalho.trabalho.parecermedico.ParecerMedico;
import br.com.meutrabalho.trabalho.parecermedico.ParecerMedicoRepository;

public class ParecerMedicoExcelTest {

	public void gerarExcel(ParecerMedicoRepository medicoRepository) {
		ParecerMedico parecerMedico1 = new ParecerMedico();
		parecerMedico1.setStatus("assinado");
		parecerMedico1.setNomePaciente("Pedro");
		parecerMedico1.setDtSolicitacao(new Date());

		ParecerMedico parecerMedico2 = new ParecerMedico();
		parecerMedico2.setStatus("fechado");
		parecerMedico2.setNomePaciente("Paulo");
		parecerMedico2.setDtSolicitacao(new Date());

		ParecerMedico parecerMedico3 = new ParecerMedico();
		parecerMedico3.setStatus("aberto");
		parecerMedico3.setNomePaciente("Joao");
		parecerMedico3.setDtSolicitacao(new Date());

		medicoRepository.save(parecerMedico1);
		medicoRepository.save(parecerMedico2);
		medicoRepository.save(parecerMedico3);

		List<ParecerMedico> pareceresMedicos = (List<ParecerMedico>) medicoRepository.findAll();

		ParecerMedicoExcel parecerMedicoExcel = new ParecerMedicoExcel();
		try {
			parecerMedicoExcel.gerarExcel(pareceresMedicos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}