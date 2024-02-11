package moduloFaturamento.regras.tarifa;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.model.IdTarifa;
import moduloFaturamento.model.Tarifa;
import moduloFaturamento.regras.mecanicas.tarifa.spec.TarifaArquivoEntidadeSpec;
import moduloFaturamento.regras.mecanicas.tarifa.spec.TarifaArquivoParcelaSpec;
import moduloFaturamento.regras.mecanicas.tarifa.spec.TarifaArquivoValoresSpec;
import moduloFaturamento.util.ConverterData;

@Service
public class TarifaRegra {

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

    public List<Tarifa>  gerarRegistrosNovasTarifasUpload(Workbook workbook) {

		List<Tarifa> listAmostrasExigidas = new ArrayList<>();

		Sheet sheet = workbook.getSheetAt(0);
		try {

			for (int i = 3; i <= sheet.getLastRowNum(); i++) {

				Row row = sheet.getRow(i);
				Cell cellZero = row.getCell(0);

				if (cellZero == null) {

					break;
				}

				Tarifa tarifa = new Tarifa();
                IdTarifa idTarifa = new IdTarifa();

                idTarifa.setIdTarifa((int) cellZero.getNumericCellValue());

				LocalDateTime teste = row.getCell(1).getLocalDateTimeCellValue();
				LocalDate localDate = teste.toLocalDate();
				Integer dataInteger = ConverterData.localDateEmIntegerDataFormatoDB(localDate);

				idTarifa.setGrupoConsumo((int) row.getCell(2).getNumericCellValue());
                idTarifa.setLimiteFaixa((int) row.getCell(3).getNumericCellValue());

                idTarifa.setDataTarifa(dataInteger);

                tarifa.setId(idTarifa);

                tarifa.setValorAguaParcelaFixa((BigDecimal.valueOf(row.getCell(4).getNumericCellValue()).setScale(2,RoundingMode.HALF_EVEN)));
                tarifa.setValorAgua(BigDecimal.valueOf(row.getCell(5).getNumericCellValue()).setScale(2,RoundingMode.HALF_EVEN));

				tarifa.setValorEsgotoTratadoParcelaFixa(BigDecimal.valueOf(row.getCell(6).getNumericCellValue()).setScale(2,RoundingMode.HALF_EVEN));
				tarifa.setValorEsgotoTratado(BigDecimal.valueOf(row.getCell(7).getNumericCellValue()).setScale(2,RoundingMode.HALF_EVEN));

				tarifa.setValorEsgotoNaoTratadoParcelaFixa(BigDecimal.valueOf(row.getCell(8).getNumericCellValue()).setScale(2,RoundingMode.HALF_EVEN));
				tarifa.setValorEsgotoNaoTratado(BigDecimal.valueOf(row.getCell(9).getNumericCellValue()).setScale(2,RoundingMode.HALF_EVEN));

				tarifa.setValorDisponibilidadeEsgotoParcelaFixa(BigDecimal.valueOf(row.getCell(10).getNumericCellValue()).setScale(2,RoundingMode.HALF_EVEN));
				tarifa.setValorDisponibilidadeEsgoto(BigDecimal.valueOf(row.getCell(11).getNumericCellValue()).setScale(2,RoundingMode.HALF_EVEN));

				listAmostrasExigidas.add(tarifa);
			}

			return listAmostrasExigidas;
		} catch (Exception e) {

			throw new MsgGenericaPersonalizadaException("Existe erro no leiaute do arquivo.");
		}
	}
    
	public Workbook criarArquivoModeloAmostrasMinimasExigidas() {
		
		List<String> cabecario_entidade = Arrays.asList(TarifaArquivoEntidadeSpec.class.getEnumConstants()).stream().map(e -> e.getNomeCabecalho()).collect(Collectors.toList());
		List<String> cabecario_parcela = Arrays.asList(TarifaArquivoParcelaSpec.class.getEnumConstants()).stream().map(e -> e.getNomeCabecalho()).collect(Collectors.toList());
		List<String> cabecario_valor = Arrays.asList(TarifaArquivoValoresSpec.class.getEnumConstants()).stream().map(e -> e.getNomeCabecalho()).collect(Collectors.toList());
		
		return criarWorkbookPorCabeçalho(cabecario_entidade, cabecario_parcela, cabecario_valor);
	}
	
	
	private Workbook criarWorkbookPorCabeçalho(List<String> entidade, List<String> parcela, List<String> valor) {

		Workbook workbook = new XSSFWorkbook();

		Sheet sheet = workbook.createSheet();
		Row row = sheet.createRow(0);
		Row row2 = sheet.createRow(1);
		Row row3 = sheet.createRow(2);

		Font font = workbook.createFont();
		font.setBold(true);

		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		cellStyle.setFont(font);

		for (int i = 0; i < entidade.size(); i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(entidade.get(i));
			cell.setCellStyle(cellStyle);
			sheet.autoSizeColumn(i);
		}
		
		
		for (int i = 0; i < parcela.size(); i++) {
			Cell cell = row2.createCell(i);
			cell.setCellValue(parcela.get(i));
			cell.setCellStyle(cellStyle);
			sheet.autoSizeColumn(i);
		}
		
		for (int i = 0; i < valor.size(); i++) {
			Cell cell = row3.createCell(i);
			cell.setCellValue(valor.get(i));
			cell.setCellStyle(cellStyle);
			sheet.autoSizeColumn(i);
		}
		
		sheet.addMergedRegion(new CellRangeAddress(0,0,4,5));
		sheet.addMergedRegion(new CellRangeAddress(0,0,6,7));
		sheet.addMergedRegion(new CellRangeAddress(0,0,8,9));
		sheet.addMergedRegion(new CellRangeAddress(0,0,10,11));
		
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
    
}
