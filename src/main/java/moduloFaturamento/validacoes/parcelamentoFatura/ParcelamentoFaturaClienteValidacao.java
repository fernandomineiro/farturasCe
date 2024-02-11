package moduloFaturamento.validacoes.parcelamentoFatura;

import org.springframework.stereotype.Service;

@Service
public class ParcelamentoFaturaClienteValidacao extends ParcelamentoFaturaValidacao {

    public void verificarClienteValidao (Integer cdCliente, Short dvCliente, String cpfOuCnpj){

        super.gerarExcecaoValidarCdCliente(cdCliente, dvCliente, cpfOuCnpj);
    }
    
}
