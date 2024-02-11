package moduloFaturamento.dto.parcelamentoFatura;

import java.util.List;

import javax.validation.constraints.Email;

public class ParcelamentoFaturaListaEmailDTO {

    @Email
    List<String> listaEmail;

    public ParcelamentoFaturaListaEmailDTO() {
    }

    public ParcelamentoFaturaListaEmailDTO(List<String> listaEmail) {
        this.listaEmail = listaEmail;
    }

    public List<String> getListaEmail() {
        return listaEmail;
    }

    public void setListaEmail(List<String> listaEmail) {
        this.listaEmail = listaEmail;
    }
}
