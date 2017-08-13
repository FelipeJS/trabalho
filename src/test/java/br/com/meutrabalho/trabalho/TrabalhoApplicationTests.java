package br.com.meutrabalho.trabalho;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.meutrabalho.trabalho.parecermedico.ParecerMedicoRepository;
import br.com.meutrabalho.trabalho.relatorio.ParecerMedicoExcelTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrabalhoApplicationTests {

	@Autowired
	ParecerMedicoRepository parecerMedicoRepository;

	@Test
	public void contextLoads() {
		ParecerMedicoExcelTest excelTest = new ParecerMedicoExcelTest();
		excelTest.gerarExcel(parecerMedicoRepository);
	}
}