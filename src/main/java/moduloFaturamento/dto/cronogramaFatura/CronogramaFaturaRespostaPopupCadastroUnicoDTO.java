package moduloFaturamento.dto.cronogramaFatura;

public class CronogramaFaturaRespostaPopupCadastroUnicoDTO {

    private Boolean abrirPopup;

    public CronogramaFaturaRespostaPopupCadastroUnicoDTO(Boolean abrirPopup) {
        this.abrirPopup = abrirPopup;
    }

    public Boolean getAbrirPopup() {
        return abrirPopup;
    }

    public void setAbrirPopup(Boolean abrirPopup) {
        this.abrirPopup = abrirPopup;
    }
}
