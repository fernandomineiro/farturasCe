package moduloFaturamento.dto.classificacaoImobiliaria;

public class LeituraConsumoImovelCabecalhoClassificacaoImovelDTO {

    private Integer idGrupoConsumo;
    private Short economias;
    private String descricaoGrupoConsumo;
    private String descricaoCategoriaGrupoConsumo;

    public Integer getIdGrupoConsumo() {
        return idGrupoConsumo;
    }

    public void setIdGrupoConsumo(Integer idGrupoConsumo) {
        this.idGrupoConsumo = idGrupoConsumo;
    }

    public Short getEconomias() {
        return economias;
    }

    public void setEconomias(Short economias) {
        this.economias = economias;
    }

    public String getDescricaoGrupoConsumo() {
        return descricaoGrupoConsumo;
    }

    public void setDescricaoGrupoConsumo(String descricaoGrupoConsumo) {
        this.descricaoGrupoConsumo = descricaoGrupoConsumo;
    }

    public String getDescricaoCategoriaGrupoConsumo() {
        return descricaoCategoriaGrupoConsumo;
    }

    public void setDescricaoCategoriaGrupoConsumo(String descricaoCategoriaGrupoConsumo) {
        this.descricaoCategoriaGrupoConsumo = descricaoCategoriaGrupoConsumo;
    }
}
