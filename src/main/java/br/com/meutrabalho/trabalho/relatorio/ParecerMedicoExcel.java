package br.com.meutrabalho.trabalho.relatorio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;

import br.com.meutrabalho.trabalho.parecermedico.ParecerMedico;

public class ParecerMedicoExcel {

	public void gerarExcel(List<ParecerMedico> parecerMedicos) throws FileNotFoundException, IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Parecer Medico");

		DataFormat format = workbook.createDataFormat();
		CellStyle dateStyle = workbook.createCellStyle();
		dateStyle.setDataFormat(format.getFormat("dd/mm/yyyy"));
		HSSFCell cell;

		for (int linha = 0; linha < parecerMedicos.size(); linha++) {
			HSSFRow row = sheet.createRow(linha);
			ParecerMedico parecerMedico = parecerMedicos.get(linha);

			for (int coluna = 0; coluna < 10; coluna++) {
				switch (coluna) {
				case 0:
					row.createCell(coluna).setCellValue(parecerMedico.getStatus());
					break;
				case 1:
					row.createCell(coluna).setCellValue(parecerMedico.getProntuario());
					break;
				case 2:
					cell = row.createCell(coluna);
					cell.setCellStyle(dateStyle);
					cell.setCellValue(parecerMedico.getDtSolicitacao());
					sheet.autoSizeColumn(coluna);
					break;
				case 3:
					row.createCell(coluna).setCellValue(parecerMedico.getLeito());
					break;
				case 4:
					row.createCell(coluna).setCellValue(parecerMedico.getUnidadeInternacao());
					break;
				case 5:
					row.createCell(coluna).setCellValue(parecerMedico.getNomePaciente());
					break;
				case 6:
					row.createCell(coluna).setCellValue(parecerMedico.getEspecialidade());
					break;
				case 7:
					row.createCell(coluna).setCellValue(parecerMedico.getPrestadorSolicitante());
					break;
				case 8:
					row.createCell(coluna).setCellValue(parecerMedico.getPrestadorResposta());
					break;
				case 9:
					cell = row.createCell(coluna);
					cell.setCellStyle(dateStyle);
					cell.setCellValue(new Date());
					sheet.autoSizeColumn(coluna);
					break;
				default:
					break;
				}
			}
		}

		workbook.write(new FileOutputStream("calc.ods"));
		workbook.close();
	}
}