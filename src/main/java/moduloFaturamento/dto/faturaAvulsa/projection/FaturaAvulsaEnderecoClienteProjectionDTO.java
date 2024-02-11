package moduloFaturamento.dto.faturaAvulsa.projection;

public interface FaturaAvulsaEnderecoClienteProjectionDTO {

    Short getDvCliente();
    String getNomeCliente();
    String getRua();
    Integer getNumeroEndereco();
    String getComplementoEndereco();
    String getBairro();
    String getLocalidade();
    String getEstado();
}
