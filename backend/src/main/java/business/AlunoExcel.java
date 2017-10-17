package business;

import java.sql.SQLException;
import java.util.List;
import java.io.*;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import DTO.AlunoDTO;

public class AlunoExcel {

	private AlunoService service = new AlunoService();
	
	public AlunoExcel() throws SQLException {}

	public HSSFWorkbook gerarExcel() throws SQLException {
		
		HSSFWorkbook alunoExcel = new HSSFWorkbook();
		
		List<AlunoDTO> alunos = service.GetAll();
		
		try {
			
			HSSFSheet sheet = alunoExcel.createSheet();
			
			HSSFRow rowTop = sheet.createRow(0);
			HSSFRow rowHead = sheet.createRow(1);
			
			rowTop.createCell(0).setCellValue("Dados Gerais");
			rowTop.createCell(11).setCellValue("Endereço");
			rowTop.createCell(18).setCellValue("Responsáveis Legais");
			rowTop.createCell(26).setCellValue("Contatos");
			rowTop.createCell(32).setCellValue("Estrutura Familiar");
			rowTop.createCell(38).setCellValue("Saúde");
			rowTop.createCell(48).setCellValue("Bens Familiares");
			rowTop.createCell(54).setCellValue("Composição Familiar");
			rowTop.createCell(62).setCellValue("Situação Habitacional");
			rowTop.createCell(70).setCellValue("Aparelhos Eletronicos");
			rowTop.createCell(81).setCellValue("Despesas");
			rowTop.createCell(96).setCellValue("Observações");
			
			alunoExcel.write();
			
			alunoExcel.close();
			
			System.out.println("Arquivo Excel dos Alunos Gerado");
			
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
		return alunoExcel;
	}
	
	
}
