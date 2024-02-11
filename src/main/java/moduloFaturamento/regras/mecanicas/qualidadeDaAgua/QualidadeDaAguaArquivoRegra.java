package moduloFaturamento.regras.mecanicas.qualidadeDaAgua;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.model.AmostrasExigidas;
import moduloFaturamento.model.AmostrasRealizadas;
import moduloFaturamento.model.IdAmostrasExigidas;
import moduloFaturamento.model.IdAmostrasRealizadas;
import moduloFaturamento.regras.mecanicas.qualidadeDaAgua.spec.AmostrasMinimasExigidasSpec;
import moduloFaturamento.regras.mecanicas.qualidadeDaAgua.spec.AmostrasMinimasRealizadasSpec;

@Service
public class QualidadeDaAguaArquivoRegra {

	public Workbook criarWorkbookDoUpload(MultipartFile file) {

		try {

			return new XSSFWorkbook(file.getInputStream());
		} catch (Exception e1) {

			try {

				return new HSSFWorkbook(file.getInputStream());
			} catch (Exception e2) {

				throw new MsgGenericaPersonalizadaException("O arquivo enviado não é um arquivo Excel válido.");
			}
		}
	}

	public Workbook criarArquivoModeloAmostrasMinimasExigidas() {

		List<String> collect = Arrays.asList(AmostrasMinimasExigidasSpec.class.getEnumConstants()).stream().map(e -> e.getNomeCabecalho())
				.collect(Collectors.toList());

		return criarWorkbookPorCabeçalho(collect);
	}

	public Workbook criarArquivoModeloAmostrasMinimasRealizadas() {

		List<String> collect = Arrays.asList(AmostrasMinimasRealizadasSpec.class.getEnumConstants()).stream().map(e -> e.getNomeCabecalho())
				.collect(Collectors.toList());

		return criarWorkbookPorCabeçalho(collect);
	}

	private Workbook criarWorkbookPorCabeçalho(List<String> nomesCabecalho) {

		Workbook workbook = new XSSFWorkbook();

		Sheet sheet = workbook.createSheet();
		Row row = sheet.createRow(0);

		Font font = workbook.createFont();
		font.setBold(true);

		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		cellStyle.setFont(font);

		for (int i = 0; i < nomesCabecalho.size(); i++) {

			Cell cell = row.createCell(i);
			cell.setCellValue(nomesCabecalho.get(i));
			cell.setCellStyle(cellStyle);
			sheet.autoSizeColumn(i);
		}

		return workbook;
	}

	public Resource transformarWorkBookEmResource(Workbook workbook) {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try (workbook) {

			workbook.write(bos);
		} catch (IOException e) {

			throw new MsgGenericaPersonalizadaException("Falha ao gerar o arquivo xlsx.");
		}
		return new ByteArrayResource(bos.toByteArray());
	}

	public Workbook gerarArquivoAmostrasRealizadasDownload(List<AmostrasRealizadas> listAmostrasRealizadas) {

		Workbook workbook = this.criarArquivoModeloAmostrasMinimasRealizadas();
		Sheet sheet = workbook.getSheetAt(0);

		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

		for (int i = 1; i <= listAmostrasRealizadas.size(); i++) {

			AmostrasRealizadas amostrasRealizadas = listAmostrasRealizadas.get(i - 1);
			Row row = sheet.createRow(i);
			row.setRowStyle(cellStyle);

			row.createCell(0).setCellValue(amostrasRealizadas.getIdAmostrasRealizadas().getRefAmostras());
			row.createCell(1).setCellValue(amostrasRealizadas.getIdAmostrasRealizadas().getCdCidade());
			row.createCell(2).setCellValue(amostrasRealizadas.getCloroR());
			row.createCell(3).setCellValue(amostrasRealizadas.getColiformesR());
			row.createCell(4).setCellValue(amostrasRealizadas.getCorR());
			row.createCell(5).setCellValue(amostrasRealizadas.getFluorR());
			row.createCell(6).setCellValue(amostrasRealizadas.getEscherColiR());
			row.createCell(7).setCellValue(amostrasRealizadas.getTurbidezR());
			
			row.createCell(8).setCellValue(amostrasRealizadas.getCloroA());
			row.createCell(9).setCellValue(amostrasRealizadas.getColiformesA());
			row.createCell(10).setCellValue(amostrasRealizadas.getCorA());
			row.createCell(11).setCellValue(amostrasRealizadas.getFluorA());
			row.createCell(12).setCellValue(amostrasRealizadas.getEscherColiA());
			row.createCell(13).setCellValue(amostrasRealizadas.getTurbidezA());
			row.createCell(14).setCellValue(amostrasRealizadas.getConclusao());
		}

		return workbook;
	}

	public Workbook gerarArquivoAmostrasExigidasDownload(List<AmostrasExigidas> listAmostrasExigidas) {

		Workbook workbook = this.criarArquivoModeloAmostrasMinimasExigidas();
		Sheet sheet = workbook.getSheetAt(0);

		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

		for (int i = 1; i <= listAmostrasExigidas.size(); i++) {

			AmostrasExigidas amostrasExigidas = listAmostrasExigidas.get(i - 1);
			Row row = sheet.createRow(i);
			row.setRowStyle(cellStyle);

			row.createCell(0).setCellValue(amostrasExigidas.getIdAmostrasExigidas().getDtInicio());
			row.createCell(1).setCellValue(amostrasExigidas.getIdAmostrasExigidas().getCdCidade());
			row.createCell(2).setCellValue(amostrasExigidas.getCloro());
			row.createCell(3).setCellValue(amostrasExigidas.getColiformesTotais());
			row.createCell(4).setCellValue(amostrasExigidas.getCor());
			row.createCell(5).setCellValue(amostrasExigidas.getFluor());
			row.createCell(6).setCellValue(amostrasExigidas.getTurbidez());
			row.createCell(7).setCellValue(amostrasExigidas.getEscherColi());
		}

		return workbook;

	}

	public List<AmostrasExigidas> gerarRegistrosAmostrasExigidasUpload(Workbook workbook, String usuario) {

		List<AmostrasExigidas> listAmostrasExigidas = new ArrayList<>();

		Sheet sheet = workbook.getSheetAt(0);
		try {

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {

				Row row = sheet.getRow(i);
				Cell cellZero = row.getCell(0);

				if (cellZero == null) {

					break;
				}

				AmostrasExigidas amostrasExigidas = new AmostrasExigidas();

				IdAmostrasExigidas idAmostrasExigidas = new IdAmostrasExigidas();
				idAmostrasExigidas.setDtInicio((int) cellZero.getNumericCellValue());
				idAmostrasExigidas.setCdCidade((short) row.getCell(1).getNumericCellValue());

				amostrasExigidas.setIdAmostrasExigidas(idAmostrasExigidas);

				amostrasExigidas.setCloro((short) row.getCell(2).getNumericCellValue());
				amostrasExigidas.setColiformesTotais((short) row.getCell(3).getNumericCellValue());
				amostrasExigidas.setCor((short) row.getCell(4).getNumericCellValue());
				amostrasExigidas.setFluor((short) row.getCell(5).getNumericCellValue());
				amostrasExigidas.setTurbidez((short) row.getCell(6).getNumericCellValue());
				amostrasExigidas.setEscherColi((short) row.getCell(7).getNumericCellValue());

				amostrasExigidas.setMaint("A");
				amostrasExigidas.setUsuario(usuario);
				amostrasExigidas.setDataInsercao(LocalDate.now());

				listAmostrasExigidas.add(amostrasExigidas);
			}

			return listAmostrasExigidas;
		} catch (Exception e) {

			throw new MsgGenericaPersonalizadaException("Existe erro no leiaute do arquivo.");
		}
	}

	public List<AmostrasRealizadas> gerarRegistrosAmostrasRealizadasUpload(Workbook workbook, String usuario) {

		List<AmostrasRealizadas> listAmostrasRealizadas = new ArrayList<>();

		Sheet sheet = workbook.getSheetAt(0);
		try {

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {

				Row row = sheet.getRow(i);
				Cell cellZero = row.getCell(0);

				if (cellZero == null) {

					break;
				}

				AmostrasRealizadas amostrasRealizadas = new AmostrasRealizadas();

				IdAmostrasRealizadas idAmostrasRealizadas = new IdAmostrasRealizadas();
				idAmostrasRealizadas.setRefAmostras((int) cellZero.getNumericCellValue());
				idAmostrasRealizadas.setCdCidade((short) row.getCell(1).getNumericCellValue());

				amostrasRealizadas.setIdAmostrasRealizadas(idAmostrasRealizadas);

				amostrasRealizadas.setCloroR((short) row.getCell(2).getNumericCellValue());
				amostrasRealizadas.setColiformesR((short) row.getCell(3).getNumericCellValue());
				amostrasRealizadas.setCorR((short) row.getCell(4).getNumericCellValue());
				amostrasRealizadas.setFluorR((short) row.getCell(5).getNumericCellValue());
				amostrasRealizadas.setEscherColiR((short) row.getCell(6).getNumericCellValue());
				amostrasRealizadas.setTurbidezR((short) row.getCell(7).getNumericCellValue());
				
				amostrasRealizadas.setCloroA((short) row.getCell(8).getNumericCellValue());
				amostrasRealizadas.setColiformesA((short) row.getCell(9).getNumericCellValue());
				amostrasRealizadas.setCorA((short) row.getCell(10).getNumericCellValue());
				amostrasRealizadas.setFluorA((short) row.getCell(11).getNumericCellValue());
				amostrasRealizadas.setEscherColiA((short) row.getCell(12).getNumericCellValue());
				amostrasRealizadas.setTurbidezA((short) row.getCell(13).getNumericCellValue());
				amostrasRealizadas.setConclusao(row.getCell(14).getStringCellValue());

				amostrasRealizadas.setMaint("A");
				amostrasRealizadas.setUsuario(usuario);
				amostrasRealizadas.setDataInsercao(LocalDate.now());

				listAmostrasRealizadas.add(amostrasRealizadas);
			}

			return listAmostrasRealizadas;
		} catch (Exception e) {

			throw new MsgGenericaPersonalizadaException("Existe erro no leiaute do arquivo.");
		}
	}
}
