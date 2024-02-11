package moduloFaturamento.validacoes.leitura;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LeituraConsumoImovelConsultaValidacao extends LeituraValidacao{

    public void gerenciarValidacao(Long id){
        super.gerarExcecaoLeituraNaoEncontrada(id);
    }

    public void gerenciarValidacao(Integer matricula, LocalDate refFatura){
        super.gerarExcecaoLeituraNaoEncontrada(matricula, refFatura);
    }
}
