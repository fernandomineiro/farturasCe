package moduloFaturamento.dto.leitura;

import java.time.LocalDate;

public class LeituraMostraDataDTO {

    private LocalDate data;

    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public LeituraMostraDataDTO(LocalDate data) {
        this.data = data;
    }
}
