package moduloFaturamento.dto.faturaAvulsaImovel.projection;

import lombok.Data;

@Data
public class FaturaAvulsaCriadaDTO {

     private Integer matriculaImovel;

     private Short origemFatura;

     private Integer refFatura;

     private Long id;

     private short dv;
}
