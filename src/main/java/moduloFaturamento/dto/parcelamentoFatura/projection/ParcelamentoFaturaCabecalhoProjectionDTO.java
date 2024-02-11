package moduloFaturamento.dto.parcelamentoFatura.projection;

public interface ParcelamentoFaturaCabecalhoProjectionDTO {

    // Dados dono do imovel (pode ou não ser o mesmo que o cliente)
    Integer getCdClienteImovel();
    Short getDvClienteImovel();
    String getNomeClienteImovel();

    // imovel selecionado
    Integer getMatriculaImovel();
    Short getDvMatriculaImovel();

    // dados do imovel
	String getDescricaoGrupoDeConsumo();
	String getNomeDaCidade();
	String getNomeDoBairro();
	String getNomeDaRua();
	Short getNumeroImovel();
    String getComplemento();

    // Cliente devedor
    Integer getCdClienteFatura();
    Short getDvClienteFatura();
    String getNomeClienteFatura();
    Long getCpfOuCnpj();
    
}
