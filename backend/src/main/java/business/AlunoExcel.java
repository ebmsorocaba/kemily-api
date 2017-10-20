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
				
				preencherDadosGerais(sheet, linha, aluno, tamanho);
				preencherEndereco(sheet, linha, aluno);
				preencherResponsaveisLegais(sheet, linha, aluno);
				preencherContatos(sheet, linha, aluno);
				preencherEstruturaFamiliar(sheet, linha, aluno);
				preencherSaude(sheet, linha, aluno);
				preencherBens(sheet, linha, aluno);
				preencherComposicaoFamiliar(sheet, linha, aluno);
				preencherSituacaoHabitacional(sheet, linha, aluno);
				preencherAparelhosEletronicos(sheet, linha, aluno);
				preencherDespesas(sheet, linha, aluno);
				preencherObservacoes(sheet, linha, aluno);
				
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
			
			rowTop.getCell(0).setCellStyle(style);
			rowTop.getCell(11).setCellStyle(style);
			rowTop.getCell(18).setCellStyle(style);
			rowTop.getCell(26).setCellStyle(style);
			rowTop.getCell(32).setCellStyle(style);
			rowTop.getCell(38).setCellStyle(style);
			rowTop.getCell(48).setCellStyle(style);
			rowTop.getCell(54).setCellStyle(style);
			rowTop.getCell(62).setCellStyle(style);
			rowTop.getCell(70).setCellStyle(style);
			rowTop.getCell(81).setCellStyle(style);
			rowTop.getCell(96).setCellStyle(style);
			
			sheet.addMergedRegion(new CellRangeAddress(0,0,0,10));
			sheet.addMergedRegion(new CellRangeAddress(0,0,11,17));
			sheet.addMergedRegion(new CellRangeAddress(0,0,18,25));
			sheet.addMergedRegion(new CellRangeAddress(0,0,26,31));
			sheet.addMergedRegion(new CellRangeAddress(0,0,32,37));
			sheet.addMergedRegion(new CellRangeAddress(0,0,38,47));
			sheet.addMergedRegion(new CellRangeAddress(0,0,48,53));
			sheet.addMergedRegion(new CellRangeAddress(0,0,54,61));
			sheet.addMergedRegion(new CellRangeAddress(0,0,62,69));
			sheet.addMergedRegion(new CellRangeAddress(0,0,70,80));
			sheet.addMergedRegion(new CellRangeAddress(0,0,81,95));
			
		} catch (Exception ex) {
			System.out.println("Erro de Exception no try criarCabecalhoDialog()");
			System.out.println(ex.toString());
		}
		
	}
	
	public void preencherDadosGerais(HSSFSheet sheet, int linha, AlunoDTO aluno, int tamanhoLinha) {
		
		HSSFRow rowHead = sheet.createRow(1);
		rowHead.createCell(0).setCellValue("RA");
		rowHead.createCell(1).setCellValue("Nome");
		rowHead.createCell(2).setCellValue("RG");
		rowHead.createCell(3).setCellValue("Etnia");
		rowHead.createCell(4).setCellValue("Naturalidade");
		rowHead.createCell(5).setCellValue("Estado");
		rowHead.createCell(6).setCellValue("Data de Nascimento");
		rowHead.createCell(7).setCellValue("Camiseta");
		rowHead.createCell(8).setCellValue("Calça");
		rowHead.createCell(9).setCellValue("Calçado");
		rowHead.createCell(10).setCellValue("Transporte");
		
		HSSFRow dadosGerais = sheet.createRow(linha);
		
		dadosGerais.setHeightInPoints((tamanhoLinha * sheet.getDefaultRowHeightInPoints()));
		
		Aluno alu = aluno.getAluno();
		Roupa roupa = aluno.getRoupa();
		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
		
		String rg = alu.getRg();
		
		rg = rg.substring(0, 2).concat(".").concat(rg.substring(2,5)).concat(".").concat(rg.substring(5,8)).concat("-").concat(rg.substring(8));

		dadosGerais.createCell(0).setCellValue(alu.getRa());
		dadosGerais.createCell(1).setCellValue(alu.getNome());
		dadosGerais.createCell(2).setCellValue(rg);
		dadosGerais.createCell(3).setCellValue(alu.getEtnia());
		dadosGerais.createCell(4).setCellValue(alu.getNaturalidade());
		dadosGerais.createCell(5).setCellValue(alu.getEstado());
		dadosGerais.createCell(6).setCellValue(data.format(alu.getDataNascimento()));
		dadosGerais.createCell(7).setCellValue(roupa.getTamanhoCamiseta());
		dadosGerais.createCell(8).setCellValue(roupa.getTamanhoCalca());
		dadosGerais.createCell(9).setCellValue(roupa.getTamanhoSapato());
		dadosGerais.createCell(10).setCellValue(alu.getMeioTransporte());
		
	}
	
	public void preencherEndereco(HSSFSheet sheet, int linha, AlunoDTO aluno) {
		
		HSSFRow rowHead = sheet.getRow(1);
		rowHead.createCell(11).setCellValue("Rua");
		rowHead.createCell(12).setCellValue("Numero");
		rowHead.createCell(13).setCellValue("Complemento");
		rowHead.createCell(14).setCellValue("Bairro");
		rowHead.createCell(15).setCellValue("Cidade");
		rowHead.createCell(16).setCellValue("CEP");
		rowHead.createCell(17).setCellValue("Ponto de Referencia");
		
		HSSFRow endereco = sheet.getRow(linha);
		
		Endereco end = aluno.getEndereco();
		
		String cep = end.getCep();
		
		cep = cep.substring(0, 5).concat("-").concat(cep.substring(5));
		
		endereco.createCell(11).setCellValue(end.getRua());
		endereco.createCell(12).setCellValue(end.getNumero());
		endereco.createCell(13).setCellValue(end.getComplemento());
		endereco.createCell(14).setCellValue(end.getBairro());
		endereco.createCell(15).setCellValue(end.getCidade());
		endereco.createCell(16).setCellValue(cep);
		endereco.createCell(17).setCellValue(end.getPontoReferencia());
		
	}
	
	public void preencherResponsaveisLegais(HSSFSheet sheet, int linha, AlunoDTO aluno) {
		try {
			HSSFRow rowHead = sheet.getRow(1);
			rowHead.createCell(18).setCellValue("Nome");
			rowHead.createCell(19).setCellValue("Telefone");
			rowHead.createCell(20).setCellValue("RG");
			rowHead.createCell(21).setCellValue("CPF");
			rowHead.createCell(22).setCellValue("Email");
			rowHead.createCell(23).setCellValue("Rede Social");
			rowHead.createCell(24).setCellValue("Parentesco");
			rowHead.createCell(25).setCellValue("Situação");
			
			HSSFRow resp = sheet.getRow(linha);
			
			//resp.setHeightInPoints((aluno.getResponsavelLegalList().size() * sheet.getDefaultRowHeightInPoints()));
			
			List<ResponsavelLegal> rl = aluno.getResponsavelLegalList(); 
			
			String quebraLinha = System.lineSeparator();			
			
			for(int i = 0; i < rl.size(); i++) {
				
				if(i == 0) {
				
					resp.createCell(18).setCellValue(rl.get(i).getNome());
					resp.createCell(19).setCellValue(rl.get(i).getTelefone());
					resp.createCell(20).setCellValue(rl.get(i).getRg());
					resp.createCell(21).setCellValue(rl.get(i).getCpf());
					resp.createCell(22).setCellValue(rl.get(i).getEmail());
					resp.createCell(23).setCellValue(rl.get(i).getRedeSocial());
					resp.createCell(24).setCellValue(rl.get(i).getGrauParentesco());
					resp.createCell(25).setCellValue(rl.get(i).getEstado());
				
				} else {
					resp.getCell(18).setCellValue(resp.getCell(18).getStringCellValue().concat(quebraLinha + rl.get(i).getNome()));
					resp.getCell(19).setCellValue(resp.getCell(19).getStringCellValue().concat(quebraLinha + rl.get(i).getTelefone()));
					resp.getCell(20).setCellValue(resp.getCell(20).getStringCellValue().concat(quebraLinha + rl.get(i).getRg()));
					resp.getCell(21).setCellValue(resp.getCell(21).getStringCellValue().concat(quebraLinha + rl.get(i).getCpf()));
					resp.getCell(22).setCellValue(resp.getCell(22).getStringCellValue().concat(quebraLinha + rl.get(i).getEmail()));
					resp.getCell(23).setCellValue(resp.getCell(23).getStringCellValue().concat(quebraLinha + rl.get(i).getRedeSocial()));
					resp.getCell(24).setCellValue(resp.getCell(24).getStringCellValue().concat(quebraLinha + rl.get(i).getGrauParentesco()));
					resp.getCell(25).setCellValue(resp.getCell(25).getStringCellValue().concat(quebraLinha + rl.get(i).getEstado()));
				}
				
			}
			
		} catch (Exception ex) {
			System.out.println("Erro de Exception no try preencherResponsaveisLegais()");
			System.out.println(ex.toString());
		}
	}
	
	public void preencherContatos(HSSFSheet sheet, int linha, AlunoDTO aluno) {
		HSSFRow rowHead = sheet.getRow(1);
		rowHead.createCell(26).setCellValue("Nome");
		rowHead.createCell(27).setCellValue("Telefone");
		rowHead.createCell(28).setCellValue("Email");
		rowHead.createCell(29).setCellValue("Rede Social");
		rowHead.createCell(30).setCellValue("É Contato Profissional?");
		rowHead.createCell(31).setCellValue("Cargo");
		
		HSSFRow contato = sheet.getRow(linha);
		
		//contato.setHeightInPoints((aluno.getContatoList().size() * sheet.getDefaultRowHeightInPoints()));
		
		List<Contato> listaContatos = aluno.getContatoList();
		
		String quebraLinha = System.lineSeparator();
		
		for(int i = 0; i < listaContatos.size(); i++) {
			if(i == 0) {
				contato.createCell(26).setCellValue(listaContatos.get(i).getNome());
				contato.createCell(27).setCellValue(listaContatos.get(i).getTelefone());
				contato.createCell(28).setCellValue(listaContatos.get(i).getEmail());
				contato.createCell(29).setCellValue(listaContatos.get(i).getRedeSocial());
				contato.createCell(30).setCellValue(String.valueOf(listaContatos.get(i).isProfissional()));
				contato.createCell(31).setCellValue(listaContatos.get(i).getCargo());
			} else {
				contato.getCell(26).setCellValue(contato.getCell(26).getStringCellValue().concat(quebraLinha + listaContatos.get(i).getNome()));
				contato.getCell(27).setCellValue(contato.getCell(27).getStringCellValue().concat(quebraLinha + listaContatos.get(i).getTelefone()));
				contato.getCell(28).setCellValue(contato.getCell(28).getStringCellValue().concat(quebraLinha + listaContatos.get(i).getEmail()));
				contato.getCell(29).setCellValue(contato.getCell(29).getStringCellValue().concat(quebraLinha + listaContatos.get(i).getRedeSocial()));
				contato.getCell(30).setCellValue(contato.getCell(30).getStringCellValue().concat(quebraLinha + String.valueOf(listaContatos.get(i).isProfissional())));
				contato.getCell(31).setCellValue(contato.getCell(31).getStringCellValue().concat(quebraLinha + listaContatos.get(i).getCargo()));
			}
		}
	
	}
	
	public void preencherEstruturaFamiliar(HSSFSheet sheet, int linha, AlunoDTO aluno) {
		
		HSSFRow rowHead = sheet.getRow(1);
		rowHead.createCell(32).setCellValue("Criança Reside Com?");
		rowHead.createCell(33).setCellValue("Estado Civil dos Pais");
		rowHead.createCell(34).setCellValue("Possui Problemas Financeiros?");
		rowHead.createCell(35).setCellValue("Familiares com Problemas de Álcool/Drogas?");
		rowHead.createCell(36).setCellValue("Alguem Agressivo na Família?");
		rowHead.createCell(37).setCellValue("Participa de Programas Sociais?");
		
		HSSFRow efLinha = sheet.getRow(linha);
		
		EstruturaFamiliar ef = aluno.getEstruturaFamiliar();
		
		efLinha.createCell(32).setCellValue(String.valueOf(ef.getCriancaResideCom()));
		efLinha.createCell(33).setCellValue(String.valueOf(ef.getEstadoCivilPais()));
		efLinha.createCell(34).setCellValue(String.valueOf(ef.isProblemasFinanceiros()));
		efLinha.createCell(35).setCellValue(String.valueOf(ef.isUsoAlcoolDrogas()));
		efLinha.createCell(36).setCellValue(String.valueOf(ef.isAlguemAgressivo()));
		efLinha.createCell(37).setCellValue(String.valueOf(ef.isProgramasSociais()));
		
	}
	
	public void preencherSaude(HSSFSheet sheet, int linha, AlunoDTO aluno) {
		HSSFRow rowHead = sheet.getRow(1);
		rowHead.createCell(38).setCellValue("Criança Faz Tratamentos Médicos?");
		rowHead.createCell(39).setCellValue("Descrição do Tratamento");
		rowHead.createCell(40).setCellValue("Criança Possui Alguma Alergia?");
		rowHead.createCell(41).setCellValue("Descrição da Alergia");
		rowHead.createCell(42).setCellValue("Criança Toma Alguma Medidação?");
		rowHead.createCell(43).setCellValue("Descriçaõ da Medicação");
		rowHead.createCell(44).setCellValue("Problemas de Saúde na Família?");
		rowHead.createCell(45).setCellValue("Possui Plano de Saúde?");
		rowHead.createCell(46).setCellValue("Pessoas Idosas no Lar?");
		rowHead.createCell(47).setCellValue("Pessoas com Problemas Psíquiatricos na Familia?");
		
		HSSFRow saudeLinha = sheet.getRow(linha);
		
		Saude saude = aluno.getSaude();
		
		saudeLinha.createCell(38).setCellValue(String.valueOf(saude.isFazTratamentosMedicos()));
		saudeLinha.createCell(39).setCellValue(saude.getDescricaoTratamento());
		saudeLinha.createCell(40).setCellValue(String.valueOf(saude.isPossuiAlergia()));
		saudeLinha.createCell(41).setCellValue(saude.getDescricaoAlergia());
		saudeLinha.createCell(42).setCellValue(String.valueOf(saude.isTomaMedicacao()));
		saudeLinha.createCell(43).setCellValue(saude.getDescricaoMedicacao());
		saudeLinha.createCell(44).setCellValue(String.valueOf(saude.isProblemasSaudeFamilia()));
		saudeLinha.createCell(45).setCellValue(String.valueOf(saude.isPlanoSaude()));
		saudeLinha.createCell(46).setCellValue(String.valueOf(saude.isPessoasIdosas()));
		saudeLinha.createCell(47).setCellValue(String.valueOf(saude.isProblemasPsiquiatricos()));
	}
	
	public void preencherBens(HSSFSheet sheet, int linha, AlunoDTO aluno) {
		HSSFRow rowHead = sheet.getRow(1);
		rowHead.createCell(48).setCellValue("Possui Automovéis?");
		rowHead.createCell(49).setCellValue("Modelo");
		rowHead.createCell(50).setCellValue("Ano");
		rowHead.createCell(51).setCellValue("Financiado?");
		rowHead.createCell(52).setCellValue("Possui Imóveis?");
		rowHead.createCell(53).setCellValue("Financiado?");
		
		HSSFRow bens = sheet.getRow(linha);
		
		List<Automovel> autos = aluno.getAutomovelList();
		List<Imovel> imoveis = aluno.getImovelList();
		
		String quebraLinha = System.lineSeparator();
		
		if(autos.size() > 0) {
			bens.createCell(48).setCellValue("true");
			
			for(int i = 0; i < autos.size(); i++) {
				if(i == 0) {
					bens.createCell(49).setCellValue(autos.get(i).getModelo());
					bens.createCell(50).setCellValue(autos.get(i).getAno());
					bens.createCell(51).setCellValue(String.valueOf(autos.get(i).isFinanciado()));
				} else {
					bens.getCell(49).setCellValue(bens.getCell(49).getStringCellValue().concat(quebraLinha + autos.get(i).getModelo()));
					bens.getCell(50).setCellValue(bens.getCell(50).getStringCellValue().concat(quebraLinha + autos.get(i).getAno()));
					bens.getCell(51).setCellValue(bens.getCell(51).getStringCellValue().concat(quebraLinha + String.valueOf(autos.get(i).isFinanciado())));
				}
			}
		} else {
			bens.createCell(48).setCellValue("false");
			bens.createCell(49).setCellValue("");
			bens.createCell(50).setCellValue("");
			bens.createCell(51).setCellValue("");
		}
		
		if(imoveis.size() > 0) {
			bens.createCell(52).setCellValue("true");
			
			for(int i = 0; i < imoveis.size(); i++) {
				if(i == 0) {
					bens.createCell(53).setCellValue(String.valueOf(imoveis.get(i).isFinanciado()));
				} else {
					bens.getCell(53).setCellValue(bens.getCell(53).getStringCellValue().concat(String.valueOf(quebraLinha + imoveis.get(i).isFinanciado())));
				}
			}
		} else {
			bens.createCell(52).setCellValue("false");
			bens.createCell(53).setCellValue("");
		}
		
	}
	
	public void preencherComposicaoFamiliar(HSSFSheet sheet, int linha, AlunoDTO aluno) {
		HSSFRow rowHead = sheet.getRow(1);
		rowHead.createCell(54).setCellValue("Nome");
		rowHead.createCell(55).setCellValue("Parentesco");
		rowHead.createCell(56).setCellValue("Data de Nascimento");
		rowHead.createCell(57).setCellValue("Escolaridade");
		rowHead.createCell(58).setCellValue("Ocupação");
		rowHead.createCell(59).setCellValue("Condição de Trabalho");
		rowHead.createCell(60).setCellValue("Renda");
		rowHead.createCell(61).setCellValue("Local de Trabalho");
		
		HSSFRow cf = sheet.getRow(linha);
		
		List<MembroFamiliar> mf = aluno.getMembroFamiliarList();
		
		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
		
		String quebraLinha = System.lineSeparator();
		
		for(int i = 0; i < mf.size(); i++) {
			if(i == 0) {
				cf.createCell(54).setCellValue(mf.get(i).getNome());
				cf.createCell(55).setCellValue(mf.get(i).getParentesco());
				cf.createCell(56).setCellValue(data.format(mf.get(i).getDataNascimento()));
				cf.createCell(57).setCellValue(mf.get(i).getEscolaridade());
				cf.createCell(58).setCellValue(mf.get(i).getOcupacao());
				cf.createCell(59).setCellValue(mf.get(i).getCondicaoTrabalho());
				cf.createCell(60).setCellValue(String.valueOf(mf.get(i).getSalario()));
				cf.createCell(61).setCellValue(mf.get(i).getLocalTrabalho());
			} else {
				cf.getCell(54).setCellValue(cf.getCell(54).getStringCellValue().concat(quebraLinha + mf.get(i).getNome()));
				cf.getCell(55).setCellValue(cf.getCell(55).getStringCellValue().concat(quebraLinha + mf.get(i).getParentesco()));
				cf.getCell(56).setCellValue(cf.getCell(56).getStringCellValue().concat(quebraLinha + data.format(mf.get(i).getDataNascimento())));
				cf.getCell(57).setCellValue(cf.getCell(57).getStringCellValue().concat(quebraLinha + mf.get(i).getEscolaridade()));
				cf.getCell(58).setCellValue(cf.getCell(58).getStringCellValue().concat(quebraLinha + mf.get(i).getOcupacao()));
				cf.getCell(59).setCellValue(cf.getCell(59).getStringCellValue().concat(quebraLinha + mf.get(i).getCondicaoTrabalho()));
				cf.getCell(60).setCellValue(cf.getCell(60).getStringCellValue().concat(quebraLinha + String.valueOf(mf.get(i).getSalario())));
				cf.getCell(61).setCellValue(cf.getCell(61).getStringCellValue().concat(quebraLinha + mf.get(i).getLocalTrabalho()));
			}
		}
	}
	
	public void preencherSituacaoHabitacional(HSSFSheet sheet, int linha, AlunoDTO aluno) {
		HSSFRow rowHead = sheet.getRow(1);
		rowHead.createCell(62).setCellValue("Situação do Imóvel");
		rowHead.createCell(63).setCellValue("Nº de Comodos");
		rowHead.createCell(64).setCellValue("Esgoto?");
		rowHead.createCell(65).setCellValue("Rede Elétrica?");
		rowHead.createCell(66).setCellValue("Asfalto?");
		rowHead.createCell(67).setCellValue("Alvenaria?");
		rowHead.createCell(68).setCellValue("Casa de Madeira?");
		rowHead.createCell(69).setCellValue("Imovel Construido em Área Irregular?");
		
		HSSFRow shLinha = sheet.getRow(linha);
		
		SituacaoHabitacional sh = aluno.getSituacaoHabitacional();
		
		shLinha.createCell(62).setCellValue(sh.getSituacao());
		shLinha.createCell(63).setCellValue(String.valueOf(sh.getNumeroComodos()));
		shLinha.createCell(64).setCellValue(String.valueOf(sh.getEsgoto()));
		shLinha.createCell(65).setCellValue(String.valueOf(sh.getRedeEletrica()));
		shLinha.createCell(66).setCellValue(String.valueOf(sh.getAsfalto()));
		shLinha.createCell(67).setCellValue(String.valueOf(sh.getAlvenaria()));
		shLinha.createCell(68).setCellValue(String.valueOf(sh.getMadeira()));
		shLinha.createCell(69).setCellValue(String.valueOf(sh.getAreaIrregular()));
	}
	
	public void preencherAparelhosEletronicos(HSSFSheet sheet, int linha, AlunoDTO aluno) {
		HSSFRow rowHead = sheet.getRow(1);
		rowHead.createCell(70).setCellValue("Televisão");
		rowHead.createCell(71).setCellValue("TV por Assinatura/Internet");
		rowHead.createCell(72).setCellValue("Computador");
		rowHead.createCell(73).setCellValue("Notebook");
		rowHead.createCell(74).setCellValue("Fogão");
		rowHead.createCell(75).setCellValue("Geladeira");
		rowHead.createCell(76).setCellValue("Microondas");
		rowHead.createCell(77).setCellValue("Máquina de Lavar");
		rowHead.createCell(78).setCellValue("Máquina de Secar");
		rowHead.createCell(79).setCellValue("Telefone Fixo");
		rowHead.createCell(80).setCellValue("Celular");
		
		HSSFRow aeLinha = sheet.getRow(linha);
		
		AparelhosEletronicos ae = aluno.getAparelhosEletronicos();
		
		aeLinha.createCell(70).setCellValue(String.valueOf(ae.isTelevisao()));
		aeLinha.createCell(71).setCellValue(String.valueOf(ae.isTvAssinatura()));
		aeLinha.createCell(72).setCellValue(String.valueOf(ae.isComputador()));
		aeLinha.createCell(73).setCellValue(String.valueOf(ae.isNotebook()));
		aeLinha.createCell(74).setCellValue(String.valueOf(ae.isFogao()));
		aeLinha.createCell(75).setCellValue(String.valueOf(ae.isGeladeira()));
		aeLinha.createCell(76).setCellValue(String.valueOf(ae.isMicroondas()));
		aeLinha.createCell(77).setCellValue(String.valueOf(ae.isMaquinaLavar()));
		aeLinha.createCell(78).setCellValue(String.valueOf(ae.isMaquinaSecar()));
		aeLinha.createCell(79).setCellValue(String.valueOf(ae.isTelefoneFixo()));
		aeLinha.createCell(80).setCellValue(String.valueOf(ae.isCelular()));
	}
	
	public void preencherDespesas(HSSFSheet sheet, int linha, AlunoDTO aluno) {
		HSSFRow rowHead = sheet.getRow(1);
		rowHead.createCell(81).setCellValue("Água");
		rowHead.createCell(82).setCellValue("Energia Elétrica");
		rowHead.createCell(83).setCellValue("Telefone");
		rowHead.createCell(84).setCellValue("Aluguel");
		rowHead.createCell(85).setCellValue("Financiamento Casa");
		rowHead.createCell(86).setCellValue("Financiamento Carro");
		rowHead.createCell(87).setCellValue("Transporte");
		rowHead.createCell(88).setCellValue("Alimentação");
		rowHead.createCell(89).setCellValue("Gás");
		rowHead.createCell(90).setCellValue("Cartão de Crédito");
		rowHead.createCell(91).setCellValue("Empréstimo");
		rowHead.createCell(92).setCellValue("TV por Assinatura/Internet");
		rowHead.createCell(93).setCellValue("Educação");
		rowHead.createCell(94).setCellValue("Pensão");
		rowHead.createCell(95).setCellValue("Convênio Médico");
		
		HSSFRow despesaLinha = sheet.getRow(linha);
		
		Despesa despesa = aluno.getDespesa();
		
		despesaLinha.createCell(81).setCellValue(String.valueOf(despesa.getAgua()));
		despesaLinha.createCell(82).setCellValue(String.valueOf(despesa.getEnergiaEletrica()));
		despesaLinha.createCell(83).setCellValue(String.valueOf(despesa.getTelefone()));
		despesaLinha.createCell(84).setCellValue(String.valueOf(despesa.getAluguel()));
		despesaLinha.createCell(85).setCellValue(String.valueOf(despesa.getFinanciamentoCasa()));
		despesaLinha.createCell(86).setCellValue(String.valueOf(despesa.getFinanciamentoCarro()));
		despesaLinha.createCell(87).setCellValue(String.valueOf(despesa.getTransporte()));
		despesaLinha.createCell(88).setCellValue(String.valueOf(despesa.getAlimentacao()));
		despesaLinha.createCell(89).setCellValue(String.valueOf(despesa.getGas()));
		despesaLinha.createCell(90).setCellValue(String.valueOf(despesa.getCartaoCredito()));
		despesaLinha.createCell(91).setCellValue(String.valueOf(despesa.getEmprestimo()));
		despesaLinha.createCell(92).setCellValue(String.valueOf(despesa.getTvCabo()));
		despesaLinha.createCell(93).setCellValue(String.valueOf(despesa.getEducacao()));
		despesaLinha.createCell(94).setCellValue(String.valueOf(despesa.getPensao()));
		despesaLinha.createCell(95).setCellValue(String.valueOf(despesa.getConvenioMedico()));
	}
	
	public void preencherObservacoes(HSSFSheet sheet, int linha, AlunoDTO aluno) {
		HSSFRow rowHead = sheet.getRow(1);
		rowHead.createCell(96).setCellValue("Observações");
		
		HSSFRow obsLinha = sheet.getRow(linha);
		
		obsLinha.createCell(96).setCellValue(aluno.getAluno().getObservacoes());
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
