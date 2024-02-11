package moduloFaturamento.regras.leituraConsumoImovel.consumoMedidoMacro;

import moduloFaturamento.model.Leitura;
import moduloFaturamento.repository.LeituraRepository;
import moduloFaturamento.repository.common.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class LeituraConsumoMedidoMacroRegra {

    @Autowired
    private ImovelRepository imovelRepository;
    @Autowired
    private LeituraRepository leituraRepository;

    protected Boolean retornarSeMatriculaInformadaEstaComoMacro(Integer matricula){
        Integer matriculaMacro = this.imovelRepository.buscarSeMatriculaEhMacro(matricula);
        return matriculaMacro != null;
    }

    protected List<Integer> listarTodasAsMatriculasMicroDeUmaMatriculaMacro(Integer matriculaMacro){
        return this.imovelRepository.buscarMatriculaMicroDeImovelMacro(matriculaMacro);
    }


    protected Integer somarValorMedidoMacro(List<Integer> matriculasMicroDeUmaMatriculaMacro, Integer refFatura){
        Integer consumoMedidoMacroSomatorio = 0;
        for (Integer matriculaMicro : matriculasMicroDeUmaMatriculaMacro) {
            Leitura leitura = this.leituraRepository.buscarLeituraExistentePorMatriculaEReferencia(matriculaMicro, refFatura);
            if(leitura != null){
                consumoMedidoMacroSomatorio += leitura.getMedido();
            }
        }

        return consumoMedidoMacroSomatorio;
    }

    protected boolean retornarSeHouveAlteracaoMatriculaMicro(List<Integer> matriculasMicroDeUmaMatriculaMacro, Integer refFatura){
        boolean houveAlteracao = false;
        for (Integer matriculaMicro : matriculasMicroDeUmaMatriculaMacro) {
            Leitura leitura = this.leituraRepository.buscarLeituraExistentePorMatriculaEReferencia(matriculaMicro, refFatura);
            if(leitura != null){
               String flagConsumoMedidoMicroModificado = Optional.ofNullable(leitura.getConsumoMedidoMicroModificado()).orElse("N");
               if(flagConsumoMedidoMicroModificado.equals("S")){
                   houveAlteracao = true;
               }
            }
        }
        return houveAlteracao;
    }
}
