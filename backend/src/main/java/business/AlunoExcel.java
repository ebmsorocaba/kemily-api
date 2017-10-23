package business;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import DTO.AlunoDTO;
import model.aluno.Aluno;
import model.aluno.AparelhosEletronicos;
import model.aluno.Automovel;
import model.aluno.Contato;
import model.aluno.Despesa;
import model.aluno.Endereco;
import model.aluno.EstruturaFamiliar;
import model.aluno.Imovel;
import model.aluno.MembroFamiliar;
import model.aluno.ResponsavelLegal;
import model.aluno.Roupa;
import model.aluno.Saude;
import model.aluno.SituacaoHabitacional;

public class AlunoExcel {

	private AlunoService service = new AlunoService();
	
	public AlunoExcel() throws SQLException {}

	public HSSFWorkbook gerarExcel() throws SQLException {
		
		HSSFWorkbook alunoExcel = new HSSFWorkbook();
		
		CellStyle styleCabecalho = alunoExcel.createCellStyle();
		Font font = alunoExcel.createFont();
		
		font.setBold(true);
		styleCabecalho.setFont(font);
		styleCabecalho.setBorderBottom(BorderStyle.THIN);
		styleCabecalho.setBorderTop(BorderStyle.THIN);
		styleCabecalho.setBorderLeft(BorderStyle.THIN);
		styleCabecalho.setBorderRight(BorderStyle.THIN);
		styleCabecalho.setAlignment(HorizontalAlignment.CENTER);
		
		CellStyle styleBorda = alunoExcel.createCellStyle();
		styleBorda.setBorderBottom(BorderStyle.THIN);
		styleBorda.setBorderTop(BorderStyle.THIN);
		styleBorda.setBorderLeft(BorderStyle.THIN);
		styleBorda.setBorderRight(BorderStyle.THIN);
		
		List<AlunoDTO> alunos = service.GetAll();
		
		int linha = 2;
		
		try {
			
			HSSFSheet sheet = alunoExcel.createSheet();
			
			criarCabecalhoDialog(sheet, styleCabecalho);
			
			for(AlunoDTO aluno : alunos) {
				int tamanho = getTamanho(aluno);
				int numCelula = 0;
				System.out.println("Última Célula: " + numCelula);
				
				preencherDadosGerais(sheet, linha, aluno, tamanho, numCelula);
				
				numCelula = sheet.getRow(linha).getLastCellNum();
				
				preencherEndereco(sheet, linha, aluno, numCelula);
				
				numCelula = sheet.getRow(linha).getLastCellNum();
				
				preencherResponsaveisLegais(sheet, linha, aluno, numCelula);
				
				numCelula = sheet.getRow(linha).getLastCellNum();
				
				preencherContatos(sheet, linha, aluno, numCelula);
				
				numCelula = sheet.getRow(linha).getLastCellNum();
				
				preencherEstruturaFamiliar(sheet, linha, aluno, numCelula);
				
				numCelula = sheet.getRow(linha).getLastCellNum();
				
				preencherSaude(sheet, linha, aluno, numCelula);
				
				numCelula = sheet.getRow(linha).getLastCellNum();
				
				preencherBens(sheet, linha, aluno, numCelula);
				
				numCelula = sheet.getRow(linha).getLastCellNum();
				
				preencherComposicaoFamiliar(sheet, linha, aluno, numCelula);
				
				numCelula = sheet.getRow(linha).getLastCellNum();
				
				preencherSituacaoHabitacional(sheet, linha, aluno, numCelula);
				
				numCelula = sheet.getRow(linha).getLastCellNum();
				
				preencherAparelhosEletronicos(sheet, linha, aluno, numCelula);
				
				numCelula = sheet.getRow(linha).getLastCellNum();
				
				preencherDespesas(sheet, linha, aluno, numCelula);
				
				numCelula = sheet.getRow(linha).getLastCellNum();
				
				preencherObservacoes(sheet, linha, aluno, numCelula);
				
				numCelula = sheet.getRow(linha).getLastCellNum();
				
				for(int i = 0; i < sheet.getRow(linha).getLastCellNum(); i++) {
					sheet.getRow(linha).getCell(i).setCellStyle(styleBorda);
					//sheet.autoSizeColumn(i);
				}
				
				linha++;
			}
			
			for(int i = 0; i < sheet.getRow(1).getLastCellNum(); i++) {
				sheet.getRow(1).getCell(i).setCellStyle(styleCabecalho);
				sheet.autoSizeColumn(i);
			}
			
			//alunoExcel.write();
			
			alunoExcel.close();
			
			System.out.println("Arquivo Excel dos Alunos Gerado");
			
		} catch (Exception ex) {
			System.out.println("Erro de Exception no try gerarExcel()");
			System.out.println(ex.toString());
		}
		
		return alunoExcel;
	}
	
	public void criarCabecalhoDialog(HSSFSheet sheet, CellStyle style) {
		
		try {
			
			HSSFRow rowTop = sheet.createRow(0);		
			
			rowTop.createCell(0).setCellValue("Dados Gerais");
			rowTop.createCell(12).setCellValue("Endereço");
			rowTop.createCell(19).setCellValue("Responsáveis Legais");
			rowTop.createCell(27).setCellValue("Contatos");
			rowTop.createCell(33).setCellValue("Estrutura Familiar");
			rowTop.createCell(39).setCellValue("Saúde");
			rowTop.createCell(49).setCellValue("Bens Familiares");
			rowTop.createCell(55).setCellValue("Composição Familiar");
			rowTop.createCell(63).setCellValue("Situação Habitacional");
			rowTop.createCell(71).setCellValue("Aparelhos Eletronicos");
			rowTop.createCell(82).setCellValue("Despesas");
			rowTop.createCell(97).setCellValue("Observações");
			
			rowTop.getCell(0).setCellStyle(style);
			rowTop.getCell(12).setCellStyle(style);
			rowTop.getCell(19).setCellStyle(style);
			rowTop.getCell(27).setCellStyle(style);
			rowTop.getCell(33).setCellStyle(style);
			rowTop.getCell(39).setCellStyle(style);
			rowTop.getCell(49).setCellStyle(style);
			rowTop.getCell(55).setCellStyle(style);
			rowTop.getCell(63).setCellStyle(style);
			rowTop.getCell(71).setCellStyle(style);
			rowTop.getCell(82).setCellStyle(style);
			rowTop.getCell(97).setCellStyle(style);
			
		} catch (Exception ex) {
			System.out.println("Erro de Exception no try criarCabecalhoDialog()");
			System.out.println(ex.toString());
		}
		
	}
	
	public void preencherDadosGerais(HSSFSheet sheet, int linha, AlunoDTO aluno, int tamanhoLinha, int celulaInicial) {
		
		HSSFRow rowHead = sheet.createRow(1);
		rowHead.createCell(celulaInicial).setCellValue("RA");
		rowHead.createCell(celulaInicial + 1).setCellValue("Nome");
		rowHead.createCell(celulaInicial + 2).setCellValue("RG");
		rowHead.createCell(celulaInicial + 3).setCellValue("Etnia");
		rowHead.createCell(celulaInicial + 4).setCellValue("Naturalidade");
		rowHead.createCell(celulaInicial + 5).setCellValue("Estado");
		rowHead.createCell(celulaInicial + 6).setCellValue("Data de Nascimento");
		rowHead.createCell(celulaInicial + 7).setCellValue("Camiseta");
		rowHead.createCell(celulaInicial + 8).setCellValue("Calça");
		rowHead.createCell(celulaInicial + 9).setCellValue("Calçado");
		rowHead.createCell(celulaInicial + 10).setCellValue("Transporte");
		rowHead.createCell(celulaInicial + 11).setCellValue("Escola");
		
		HSSFRow dadosGerais = sheet.createRow(linha);
		
		dadosGerais.setHeightInPoints((tamanhoLinha * sheet.getDefaultRowHeightInPoints()));
		
		Aluno alu = aluno.getAluno();
		Roupa roupa = aluno.getRoupa();
		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
		
		String rg = alu.getRg();
		
		rg = rg.substring(0, 2).concat(".").concat(rg.substring(2,5)).concat(".").concat(rg.substring(5,8)).concat("-").concat(rg.substring(8));

		dadosGerais.createCell(celulaInicial).setCellValue(alu.getRa());
		dadosGerais.createCell(celulaInicial + 1).setCellValue(alu.getNome());
		dadosGerais.createCell(celulaInicial + 2).setCellValue(rg);
		dadosGerais.createCell(celulaInicial + 3).setCellValue(alu.getEtnia());
		dadosGerais.createCell(celulaInicial + 4).setCellValue(alu.getNaturalidade());
		dadosGerais.createCell(celulaInicial + 5).setCellValue(alu.getEstado());
		dadosGerais.createCell(celulaInicial + 6).setCellValue(data.format(alu.getDataNascimento()));
		dadosGerais.createCell(celulaInicial + 7).setCellValue(roupa.getTamanhoCamiseta());
		dadosGerais.createCell(celulaInicial + 8).setCellValue(roupa.getTamanhoCalca());
		dadosGerais.createCell(celulaInicial + 9).setCellValue(roupa.getTamanhoSapato());
		dadosGerais.createCell(celulaInicial + 10).setCellValue(alu.getMeioTransporte());
		dadosGerais.createCell(celulaInicial + 11).setCellValue(alu.getEscola());
		
		if(linha == 2) {
			sheet.addMergedRegion(new CellRangeAddress(0,0,celulaInicial,(dadosGerais.getLastCellNum() - 1)));
		}
		
	}
	
	public void preencherEndereco(HSSFSheet sheet, int linha, AlunoDTO aluno, int celulaInicial) {
		
		HSSFRow rowHead = sheet.getRow(1);
		rowHead.createCell(celulaInicial).setCellValue("Rua");
		rowHead.createCell(celulaInicial + 1).setCellValue("Numero");
		rowHead.createCell(celulaInicial + 2).setCellValue("Complemento");
		rowHead.createCell(celulaInicial + 3).setCellValue("Bairro");
		rowHead.createCell(celulaInicial + 4).setCellValue("Cidade");
		rowHead.createCell(celulaInicial + 5).setCellValue("CEP");
		rowHead.createCell(celulaInicial + 6).setCellValue("Ponto de Referencia");
		
		HSSFRow endereco = sheet.getRow(linha);
		
		Endereco end = aluno.getEndereco();
		
		String cep = end.getCep();
		
		cep = cep.substring(0, 5).concat("-").concat(cep.substring(5));
		
		endereco.createCell(celulaInicial).setCellValue(end.getRua());
		endereco.createCell(celulaInicial + 1).setCellValue(end.getNumero());
		endereco.createCell(celulaInicial + 2).setCellValue(end.getComplemento());
		endereco.createCell(celulaInicial + 3).setCellValue(end.getBairro());
		endereco.createCell(celulaInicial + 4).setCellValue(end.getCidade());
		endereco.createCell(celulaInicial + 5).setCellValue(cep);
		endereco.createCell(celulaInicial + 6).setCellValue(end.getPontoReferencia());
		
		if(linha == 2) {
			sheet.addMergedRegion(new CellRangeAddress(0,0,celulaInicial,(endereco.getLastCellNum() - 1)));
		}
		
	}
	
	public void preencherResponsaveisLegais(HSSFSheet sheet, int linha, AlunoDTO aluno, int celulaInicial) {
		try {
			HSSFRow rowHead = sheet.getRow(1);
			rowHead.createCell(celulaInicial).setCellValue("Nome");
			rowHead.createCell(celulaInicial + 1).setCellValue("Telefone");
			rowHead.createCell(celulaInicial + 2).setCellValue("RG");
			rowHead.createCell(celulaInicial + 3).setCellValue("CPF");
			rowHead.createCell(celulaInicial + 4).setCellValue("Email");
			rowHead.createCell(celulaInicial + 5).setCellValue("Rede Social");
			rowHead.createCell(celulaInicial + 6).setCellValue("Parentesco");
			rowHead.createCell(celulaInicial + 7).setCellValue("Situação");
			
			HSSFRow resp = sheet.getRow(linha);
			
			//resp.setHeightInPoints((aluno.getResponsavelLegalList().size() * sheet.getDefaultRowHeightInPoints()));
			
			List<ResponsavelLegal> rl = aluno.getResponsavelLegalList(); 
			
			String quebraLinha = System.lineSeparator();			
			
			for(int i = 0; i < rl.size(); i++) {
				
				if(i == 0) {
				
					resp.createCell(celulaInicial).setCellValue(rl.get(i).getNome());
					resp.createCell(celulaInicial + 1).setCellValue(rl.get(i).getTelefone());
					resp.createCell(celulaInicial + 2).setCellValue(rl.get(i).getRg());
					resp.createCell(celulaInicial + 3).setCellValue(rl.get(i).getCpf());
					resp.createCell(celulaInicial + 4).setCellValue(rl.get(i).getEmail());
					resp.createCell(celulaInicial + 5).setCellValue(rl.get(i).getRedeSocial());
					resp.createCell(celulaInicial + 6).setCellValue(rl.get(i).getGrauParentesco());
					resp.createCell(celulaInicial + 7).setCellValue(rl.get(i).getEstado());
				
				} else {
					resp.getCell(celulaInicial).setCellValue(resp.getCell(celulaInicial).getStringCellValue().concat(quebraLinha + rl.get(i).getNome()));
					resp.getCell(celulaInicial + 1).setCellValue(resp.getCell(celulaInicial + 1).getStringCellValue().concat(quebraLinha + rl.get(i).getTelefone()));
					resp.getCell(celulaInicial + 2).setCellValue(resp.getCell(celulaInicial + 2).getStringCellValue().concat(quebraLinha + rl.get(i).getRg()));
					resp.getCell(celulaInicial + 3).setCellValue(resp.getCell(celulaInicial + 3).getStringCellValue().concat(quebraLinha + rl.get(i).getCpf()));
					resp.getCell(celulaInicial + 4).setCellValue(resp.getCell(celulaInicial + 4).getStringCellValue().concat(quebraLinha + rl.get(i).getEmail()));
					resp.getCell(celulaInicial + 5).setCellValue(resp.getCell(celulaInicial + 5).getStringCellValue().concat(quebraLinha + rl.get(i).getRedeSocial()));
					resp.getCell(celulaInicial + 6).setCellValue(resp.getCell(celulaInicial + 6).getStringCellValue().concat(quebraLinha + rl.get(i).getGrauParentesco()));
					resp.getCell(celulaInicial + 7).setCellValue(resp.getCell(celulaInicial + 7).getStringCellValue().concat(quebraLinha + rl.get(i).getEstado()));
				}
				
			}
			
			if(linha == 2) {
				sheet.addMergedRegion(new CellRangeAddress(0,0,celulaInicial,(resp.getLastCellNum() - 1)));
			}
			
		} catch (Exception ex) {
			System.out.println("Erro de Exception no try preencherResponsaveisLegais()");
			System.out.println(ex.toString());
		}
	}
	
	public void preencherContatos(HSSFSheet sheet, int linha, AlunoDTO aluno, int celulaInicial) {
		HSSFRow rowHead = sheet.getRow(1);
		rowHead.createCell(celulaInicial).setCellValue("Nome");
		rowHead.createCell(celulaInicial + 1).setCellValue("Telefone");
		rowHead.createCell(celulaInicial + 2).setCellValue("Email");
		rowHead.createCell(celulaInicial + 3).setCellValue("Rede Social");
		rowHead.createCell(celulaInicial + 4).setCellValue("É Contato Profissional?");
		rowHead.createCell(celulaInicial + 5).setCellValue("Cargo");
		
		HSSFRow contato = sheet.getRow(linha);
		
		//contato.setHeightInPoints((aluno.getContatoList().size() * sheet.getDefaultRowHeightInPoints()));
		
		List<Contato> listaContatos = aluno.getContatoList();
		
		String quebraLinha = System.lineSeparator();
		
		for(int i = 0; i < listaContatos.size(); i++) {
			if(i == 0) {
				contato.createCell(celulaInicial).setCellValue(listaContatos.get(i).getNome());
				contato.createCell(celulaInicial + 1).setCellValue(listaContatos.get(i).getTelefone());
				contato.createCell(celulaInicial + 2).setCellValue(listaContatos.get(i).getEmail());
				contato.createCell(celulaInicial + 3).setCellValue(listaContatos.get(i).getRedeSocial());
				contato.createCell(celulaInicial + 4).setCellValue(String.valueOf(listaContatos.get(i).isProfissional()));
				contato.createCell(celulaInicial + 5).setCellValue(listaContatos.get(i).getCargo());
			} else {
				contato.getCell(celulaInicial).setCellValue(contato.getCell(celulaInicial).getStringCellValue().concat(quebraLinha + listaContatos.get(i).getNome()));
				contato.getCell(celulaInicial + 1).setCellValue(contato.getCell(celulaInicial + 1).getStringCellValue().concat(quebraLinha + listaContatos.get(i).getTelefone()));
				contato.getCell(celulaInicial + 2).setCellValue(contato.getCell(celulaInicial + 2).getStringCellValue().concat(quebraLinha + listaContatos.get(i).getEmail()));
				contato.getCell(celulaInicial + 3).setCellValue(contato.getCell(celulaInicial + 3).getStringCellValue().concat(quebraLinha + listaContatos.get(i).getRedeSocial()));
				contato.getCell(celulaInicial + 4).setCellValue(contato.getCell(celulaInicial + 4).getStringCellValue().concat(quebraLinha + String.valueOf(listaContatos.get(i).isProfissional())));
				contato.getCell(celulaInicial + 5).setCellValue(contato.getCell(celulaInicial + 5).getStringCellValue().concat(quebraLinha + listaContatos.get(i).getCargo()));
			}
		}
		
		if(linha == 2) {
			sheet.addMergedRegion(new CellRangeAddress(0,0,celulaInicial,(contato.getLastCellNum() - 1)));
		}
	
	}
	
	public void preencherEstruturaFamiliar(HSSFSheet sheet, int linha, AlunoDTO aluno, int celulaInicial) {
		
		HSSFRow rowHead = sheet.getRow(1);
		rowHead.createCell(celulaInicial).setCellValue("Criança Reside Com?");
		rowHead.createCell(celulaInicial + 1).setCellValue("Estado Civil dos Pais");
		rowHead.createCell(celulaInicial + 2).setCellValue("Possui Problemas Financeiros?");
		rowHead.createCell(celulaInicial + 3).setCellValue("Familiares com Problemas de Álcool/Drogas?");
		rowHead.createCell(celulaInicial + 4).setCellValue("Alguem Agressivo na Família?");
		rowHead.createCell(celulaInicial + 5).setCellValue("Participa de Programas Sociais?");
		
		HSSFRow efLinha = sheet.getRow(linha);
		
		EstruturaFamiliar ef = aluno.getEstruturaFamiliar();
		
		efLinha.createCell(celulaInicial).setCellValue(String.valueOf(ef.getCriancaResideCom()));
		efLinha.createCell(celulaInicial + 1).setCellValue(String.valueOf(ef.getEstadoCivilPais()));
		efLinha.createCell(celulaInicial + 2).setCellValue(String.valueOf(ef.isProblemasFinanceiros()));
		efLinha.createCell(celulaInicial + 3).setCellValue(String.valueOf(ef.isUsoAlcoolDrogas()));
		efLinha.createCell(celulaInicial + 4).setCellValue(String.valueOf(ef.isAlguemAgressivo()));
		efLinha.createCell(celulaInicial + 5).setCellValue(String.valueOf(ef.isProgramasSociais()));
		
		if(linha == 2) {
			sheet.addMergedRegion(new CellRangeAddress(0,0,celulaInicial,(efLinha.getLastCellNum() - 1)));
		}
		
	}
	
	public void preencherSaude(HSSFSheet sheet, int linha, AlunoDTO aluno, int celulaInicial) {
		HSSFRow rowHead = sheet.getRow(1);
		rowHead.createCell(celulaInicial).setCellValue("Criança Faz Tratamentos Médicos?");
		rowHead.createCell(celulaInicial + 1).setCellValue("Descrição do Tratamento");
		rowHead.createCell(celulaInicial + 2).setCellValue("Criança Possui Alguma Alergia?");
		rowHead.createCell(celulaInicial + 3).setCellValue("Descrição da Alergia");
		rowHead.createCell(celulaInicial + 4).setCellValue("Criança Toma Alguma Medidação?");
		rowHead.createCell(celulaInicial + 5).setCellValue("Descriçaõ da Medicação");
		rowHead.createCell(celulaInicial + 6).setCellValue("Problemas de Saúde na Família?");
		rowHead.createCell(celulaInicial + 7).setCellValue("Possui Plano de Saúde?");
		rowHead.createCell(celulaInicial + 8).setCellValue("Pessoas Idosas no Lar?");
		rowHead.createCell(celulaInicial + 9).setCellValue("Pessoas com Problemas Psíquiatricos na Familia?");
		
		HSSFRow saudeLinha = sheet.getRow(linha);
		
		Saude saude = aluno.getSaude();
		
		saudeLinha.createCell(celulaInicial).setCellValue(String.valueOf(saude.isFazTratamentosMedicos()));
		saudeLinha.createCell(celulaInicial + 1).setCellValue(saude.getDescricaoTratamento());
		saudeLinha.createCell(celulaInicial + 2).setCellValue(String.valueOf(saude.isPossuiAlergia()));
		saudeLinha.createCell(celulaInicial + 3).setCellValue(saude.getDescricaoAlergia());
		saudeLinha.createCell(celulaInicial + 4).setCellValue(String.valueOf(saude.isTomaMedicacao()));
		saudeLinha.createCell(celulaInicial + 5).setCellValue(saude.getDescricaoMedicacao());
		saudeLinha.createCell(celulaInicial + 6).setCellValue(String.valueOf(saude.isProblemasSaudeFamilia()));
		saudeLinha.createCell(celulaInicial + 7).setCellValue(String.valueOf(saude.isPlanoSaude()));
		saudeLinha.createCell(celulaInicial + 8).setCellValue(String.valueOf(saude.isPessoasIdosas()));
		saudeLinha.createCell(celulaInicial + 9).setCellValue(String.valueOf(saude.isProblemasPsiquiatricos()));
		
		if(linha == 2) {
			sheet.addMergedRegion(new CellRangeAddress(0,0,celulaInicial,(saudeLinha.getLastCellNum() - 1)));
		}
	}
	
	public void preencherBens(HSSFSheet sheet, int linha, AlunoDTO aluno, int celulaInicial) {
		HSSFRow rowHead = sheet.getRow(1);
		rowHead.createCell(celulaInicial).setCellValue("Possui Automovéis?");
		rowHead.createCell(celulaInicial + 1).setCellValue("Modelo");
		rowHead.createCell(celulaInicial + 2).setCellValue("Ano");
		rowHead.createCell(celulaInicial + 3).setCellValue("Financiado?");
		rowHead.createCell(celulaInicial + 4).setCellValue("Possui Imóveis?");
		rowHead.createCell(celulaInicial + 5).setCellValue("Financiado?");
		
		HSSFRow bens = sheet.getRow(linha);
		
		List<Automovel> autos = aluno.getAutomovelList();
		List<Imovel> imoveis = aluno.getImovelList();
		
		String quebraLinha = System.lineSeparator();
		
		if(autos.size() > 0) {
			bens.createCell(celulaInicial).setCellValue("true");
			
			for(int i = 0; i < autos.size(); i++) {
				if(i == 0) {
					bens.createCell(celulaInicial + 1).setCellValue(autos.get(i).getModelo());
					bens.createCell(celulaInicial + 2).setCellValue(autos.get(i).getAno());
					bens.createCell(celulaInicial + 3).setCellValue(String.valueOf(autos.get(i).isFinanciado()));
				} else {
					bens.getCell(celulaInicial + 1).setCellValue(bens.getCell(celulaInicial + 1).getStringCellValue().concat(quebraLinha + autos.get(i).getModelo()));
					bens.getCell(celulaInicial + 2).setCellValue(bens.getCell(celulaInicial + 2).getStringCellValue().concat(quebraLinha + autos.get(i).getAno()));
					bens.getCell(celulaInicial + 3).setCellValue(bens.getCell(celulaInicial + 3).getStringCellValue().concat(quebraLinha + String.valueOf(autos.get(i).isFinanciado())));
				}
			}
		} else {
			bens.createCell(celulaInicial).setCellValue("false");
			bens.createCell(celulaInicial + 1).setCellValue("");
			bens.createCell(celulaInicial + 2).setCellValue("");
			bens.createCell(celulaInicial + 3).setCellValue("");
		}
		
		if(imoveis.size() > 0) {
			bens.createCell(celulaInicial + 4).setCellValue("true");
			
			for(int i = 0; i < imoveis.size(); i++) {
				if(i == 0) {
					bens.createCell(celulaInicial + 5).setCellValue(String.valueOf(imoveis.get(i).isFinanciado()));
				} else {
					bens.getCell(celulaInicial + 5).setCellValue(bens.getCell(celulaInicial + 5).getStringCellValue().concat(String.valueOf(quebraLinha + imoveis.get(i).isFinanciado())));
				}
			}
		} else {
			bens.createCell(celulaInicial + 4).setCellValue("false");
			bens.createCell(celulaInicial + 5).setCellValue("");
		}
		
		if(linha == 2) {
			sheet.addMergedRegion(new CellRangeAddress(0,0,celulaInicial,(bens.getLastCellNum() - 1)));
		}
		
	}
	
	public void preencherComposicaoFamiliar(HSSFSheet sheet, int linha, AlunoDTO aluno, int celulaInicial) {
		HSSFRow rowHead = sheet.getRow(1);
		rowHead.createCell(celulaInicial).setCellValue("Nome");
		rowHead.createCell(celulaInicial + 1).setCellValue("Parentesco");
		rowHead.createCell(celulaInicial + 2).setCellValue("Data de Nascimento");
		rowHead.createCell(celulaInicial + 3).setCellValue("Escolaridade");
		rowHead.createCell(celulaInicial + 4).setCellValue("Ocupação");
		rowHead.createCell(celulaInicial + 5).setCellValue("Condição de Trabalho");
		rowHead.createCell(celulaInicial + 6).setCellValue("Renda");
		rowHead.createCell(celulaInicial + 7).setCellValue("Local de Trabalho");
		
		HSSFRow cf = sheet.getRow(linha);
		
		List<MembroFamiliar> mf = aluno.getMembroFamiliarList();
		
		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
		
		String quebraLinha = System.lineSeparator();
		
		for(int i = 0; i < mf.size(); i++) {
			if(i == 0) {
				cf.createCell(celulaInicial).setCellValue(mf.get(i).getNome());
				cf.createCell(celulaInicial + 1).setCellValue(mf.get(i).getParentesco());
				cf.createCell(celulaInicial + 2).setCellValue(data.format(mf.get(i).getDataNascimento()));
				cf.createCell(celulaInicial + 3).setCellValue(mf.get(i).getEscolaridade());
				cf.createCell(celulaInicial + 4).setCellValue(mf.get(i).getOcupacao());
				cf.createCell(celulaInicial + 5).setCellValue(mf.get(i).getCondicaoTrabalho());
				cf.createCell(celulaInicial + 6).setCellValue(String.valueOf(mf.get(i).getSalario()));
				cf.createCell(celulaInicial + 7).setCellValue(mf.get(i).getLocalTrabalho());
			} else {
				cf.getCell(celulaInicial).setCellValue(cf.getCell(celulaInicial).getStringCellValue().concat(quebraLinha + mf.get(i).getNome()));
				cf.getCell(celulaInicial + 1).setCellValue(cf.getCell(celulaInicial + 1).getStringCellValue().concat(quebraLinha + mf.get(i).getParentesco()));
				cf.getCell(celulaInicial + 2).setCellValue(cf.getCell(celulaInicial + 2).getStringCellValue().concat(quebraLinha + data.format(mf.get(i).getDataNascimento())));
				cf.getCell(celulaInicial + 3).setCellValue(cf.getCell(celulaInicial + 3).getStringCellValue().concat(quebraLinha + mf.get(i).getEscolaridade()));
				cf.getCell(celulaInicial + 4).setCellValue(cf.getCell(celulaInicial + 4).getStringCellValue().concat(quebraLinha + mf.get(i).getOcupacao()));
				cf.getCell(celulaInicial + 5).setCellValue(cf.getCell(celulaInicial + 5).getStringCellValue().concat(quebraLinha + mf.get(i).getCondicaoTrabalho()));
				cf.getCell(celulaInicial + 6).setCellValue(cf.getCell(celulaInicial + 6).getStringCellValue().concat(quebraLinha + String.valueOf(mf.get(i).getSalario())));
				cf.getCell(celulaInicial + 7).setCellValue(cf.getCell(celulaInicial + 7).getStringCellValue().concat(quebraLinha + mf.get(i).getLocalTrabalho()));
			}
		}
		
		if(linha == 2) {
			sheet.addMergedRegion(new CellRangeAddress(0,0,celulaInicial,(cf.getLastCellNum() - 1)));
		}
	}
	
	public void preencherSituacaoHabitacional(HSSFSheet sheet, int linha, AlunoDTO aluno, int celulaInicial) {
		HSSFRow rowHead = sheet.getRow(1);
		rowHead.createCell(celulaInicial).setCellValue("Situação do Imóvel");
		rowHead.createCell(celulaInicial + 1).setCellValue("Nº de Comodos");
		rowHead.createCell(celulaInicial + 2).setCellValue("Esgoto?");
		rowHead.createCell(celulaInicial + 3).setCellValue("Rede Elétrica?");
		rowHead.createCell(celulaInicial + 4).setCellValue("Asfalto?");
		rowHead.createCell(celulaInicial + 5).setCellValue("Alvenaria?");
		rowHead.createCell(celulaInicial + 6).setCellValue("Casa de Madeira?");
		rowHead.createCell(celulaInicial + 7).setCellValue("Imovel Construido em Área Irregular?");
		
		HSSFRow shLinha = sheet.getRow(linha);
		
		SituacaoHabitacional sh = aluno.getSituacaoHabitacional();
		
		shLinha.createCell(celulaInicial).setCellValue(sh.getSituacao());
		shLinha.createCell(celulaInicial + 1).setCellValue(String.valueOf(sh.getNumeroComodos()));
		shLinha.createCell(celulaInicial + 2).setCellValue(String.valueOf(sh.getEsgoto()));
		shLinha.createCell(celulaInicial + 3).setCellValue(String.valueOf(sh.getRedeEletrica()));
		shLinha.createCell(celulaInicial + 4).setCellValue(String.valueOf(sh.getAsfalto()));
		shLinha.createCell(celulaInicial + 5).setCellValue(String.valueOf(sh.getAlvenaria()));
		shLinha.createCell(celulaInicial + 6).setCellValue(String.valueOf(sh.getMadeira()));
		shLinha.createCell(celulaInicial + 7).setCellValue(String.valueOf(sh.getAreaIrregular()));
		
		if(linha == 2) {
			sheet.addMergedRegion(new CellRangeAddress(0,0,celulaInicial,(shLinha.getLastCellNum() - 1)));
		}
	}
	
	public void preencherAparelhosEletronicos(HSSFSheet sheet, int linha, AlunoDTO aluno, int celulaInicial) {
		HSSFRow rowHead = sheet.getRow(1);
		rowHead.createCell(celulaInicial).setCellValue("Televisão");
		rowHead.createCell(celulaInicial + 1).setCellValue("TV por Assinatura/Internet");
		rowHead.createCell(celulaInicial + 2).setCellValue("Computador");
		rowHead.createCell(celulaInicial + 3).setCellValue("Notebook");
		rowHead.createCell(celulaInicial + 4).setCellValue("Fogão");
		rowHead.createCell(celulaInicial + 5).setCellValue("Geladeira");
		rowHead.createCell(celulaInicial + 6).setCellValue("Microondas");
		rowHead.createCell(celulaInicial + 7).setCellValue("Máquina de Lavar");
		rowHead.createCell(celulaInicial + 8).setCellValue("Máquina de Secar");
		rowHead.createCell(celulaInicial + 9).setCellValue("Telefone Fixo");
		rowHead.createCell(celulaInicial + 10).setCellValue("Celular");
		
		HSSFRow aeLinha = sheet.getRow(linha);
		
		AparelhosEletronicos ae = aluno.getAparelhosEletronicos();
		
		aeLinha.createCell(celulaInicial).setCellValue(String.valueOf(ae.isTelevisao()));
		aeLinha.createCell(celulaInicial + 1).setCellValue(String.valueOf(ae.isTvAssinatura()));
		aeLinha.createCell(celulaInicial + 2).setCellValue(String.valueOf(ae.isComputador()));
		aeLinha.createCell(celulaInicial + 3).setCellValue(String.valueOf(ae.isNotebook()));
		aeLinha.createCell(celulaInicial + 4).setCellValue(String.valueOf(ae.isFogao()));
		aeLinha.createCell(celulaInicial + 5).setCellValue(String.valueOf(ae.isGeladeira()));
		aeLinha.createCell(celulaInicial + 6).setCellValue(String.valueOf(ae.isMicroondas()));
		aeLinha.createCell(celulaInicial + 7).setCellValue(String.valueOf(ae.isMaquinaLavar()));
		aeLinha.createCell(celulaInicial + 8).setCellValue(String.valueOf(ae.isMaquinaSecar()));
		aeLinha.createCell(celulaInicial + 9).setCellValue(String.valueOf(ae.isTelefoneFixo()));
		aeLinha.createCell(celulaInicial + 10).setCellValue(String.valueOf(ae.isCelular()));
		
		if(linha == 2) {
			sheet.addMergedRegion(new CellRangeAddress(0,0,celulaInicial,(aeLinha.getLastCellNum() - 1)));
		}
	}
	
	public void preencherDespesas(HSSFSheet sheet, int linha, AlunoDTO aluno, int celulaInicial) {
		HSSFRow rowHead = sheet.getRow(1);
		rowHead.createCell(celulaInicial).setCellValue("Água");
		rowHead.createCell(celulaInicial + 1).setCellValue("Energia Elétrica");
		rowHead.createCell(celulaInicial + 2).setCellValue("Telefone");
		rowHead.createCell(celulaInicial + 3).setCellValue("Aluguel");
		rowHead.createCell(celulaInicial + 4).setCellValue("Financiamento Casa");
		rowHead.createCell(celulaInicial + 5).setCellValue("Financiamento Carro");
		rowHead.createCell(celulaInicial + 6).setCellValue("Transporte");
		rowHead.createCell(celulaInicial + 7).setCellValue("Alimentação");
		rowHead.createCell(celulaInicial + 8).setCellValue("Gás");
		rowHead.createCell(celulaInicial + 9).setCellValue("Cartão de Crédito");
		rowHead.createCell(celulaInicial + 10).setCellValue("Empréstimo");
		rowHead.createCell(celulaInicial + 11).setCellValue("TV por Assinatura/Internet");
		rowHead.createCell(celulaInicial + 12).setCellValue("Educação");
		rowHead.createCell(celulaInicial + 13).setCellValue("Pensão");
		rowHead.createCell(celulaInicial + 14).setCellValue("Convênio Médico");
		
		HSSFRow despesaLinha = sheet.getRow(linha);
		
		Despesa despesa = aluno.getDespesa();
		
		despesaLinha.createCell(celulaInicial).setCellValue(String.valueOf(despesa.getAgua()));
		despesaLinha.createCell(celulaInicial + 1).setCellValue(String.valueOf(despesa.getEnergiaEletrica()));
		despesaLinha.createCell(celulaInicial + 2).setCellValue(String.valueOf(despesa.getTelefone()));
		despesaLinha.createCell(celulaInicial + 3).setCellValue(String.valueOf(despesa.getAluguel()));
		despesaLinha.createCell(celulaInicial + 4).setCellValue(String.valueOf(despesa.getFinanciamentoCasa()));
		despesaLinha.createCell(celulaInicial + 5).setCellValue(String.valueOf(despesa.getFinanciamentoCarro()));
		despesaLinha.createCell(celulaInicial + 6).setCellValue(String.valueOf(despesa.getTransporte()));
		despesaLinha.createCell(celulaInicial + 7).setCellValue(String.valueOf(despesa.getAlimentacao()));
		despesaLinha.createCell(celulaInicial + 8).setCellValue(String.valueOf(despesa.getGas()));
		despesaLinha.createCell(celulaInicial + 9).setCellValue(String.valueOf(despesa.getCartaoCredito()));
		despesaLinha.createCell(celulaInicial + 10).setCellValue(String.valueOf(despesa.getEmprestimo()));
		despesaLinha.createCell(celulaInicial + 11).setCellValue(String.valueOf(despesa.getTvCabo()));
		despesaLinha.createCell(celulaInicial + 12).setCellValue(String.valueOf(despesa.getEducacao()));
		despesaLinha.createCell(celulaInicial + 13).setCellValue(String.valueOf(despesa.getPensao()));
		despesaLinha.createCell(celulaInicial + 14).setCellValue(String.valueOf(despesa.getConvenioMedico()));
		
		if(linha == 2) {
			sheet.addMergedRegion(new CellRangeAddress(0,0,celulaInicial,(despesaLinha.getLastCellNum() - 1)));
		}
	}
	
	public void preencherObservacoes(HSSFSheet sheet, int linha, AlunoDTO aluno, int celulaInicial) {
		HSSFRow rowHead = sheet.getRow(1);
		rowHead.createCell(celulaInicial).setCellValue("Observações");
		
		HSSFRow obsLinha = sheet.getRow(linha);
		
		obsLinha.createCell(celulaInicial).setCellValue(aluno.getAluno().getObservacoes());
	}
	
	public int getTamanho(AlunoDTO aluno) {
		
		int maior = 0;
		int auto = aluno.getAutomovelList().size();
		int contato = aluno.getContatoList().size();
		int imovel = aluno.getImovelList().size();
		int membro = aluno.getMembroFamiliarList().size();
		int resp = aluno.getResponsavelLegalList().size();
		
		if(auto > maior) {
			maior = auto;
		}
		if(contato > maior) {
			maior = contato;
		}
		if(imovel > maior) {
			maior = imovel;
		}
		if(membro > maior) {
			maior = membro;
		}
		if(resp > maior) {
			maior = resp;
		}
		
		return maior;
	}
	
}
