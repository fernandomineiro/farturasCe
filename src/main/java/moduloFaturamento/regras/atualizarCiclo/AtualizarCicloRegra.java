package moduloFaturamento.regras.atualizarCiclo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import moduloFaturamento.model.AtualizarCiclo;
import moduloFaturamento.repository.common.ImovelRepository;
import moduloFaturamento.util.ConverterData;

public abstract class AtualizarCicloRegra {

    @Autowired
    private ImovelRepository imovelRepository;

    protected AtualizarCiclo atualizarCicloParaSituacaoUm(AtualizarCiclo atualizarCiclo){

        atualizarCiclo.setSituacao((short) 1); 
        atualizarCiclo.setDescricaoSituacao("ATUALIZADO");
        executarAlteracoesNaEntidades(atualizarCiclo);

        executarMudandaCsoCicloAntigoEZero(atualizarCiclo);
        return atualizarCiclo;
    }

    protected AtualizarCiclo atualizarCicloParaSituacaoDoisDuplicidade(AtualizarCiclo atualizarCiclo){

        atualizarCiclo.setSituacao((short)2);
        atualizarCiclo.setDescricaoSituacao("NÃO EFETIVADA POR DUPLICIDADE");
        executarAlteracoesNaEntidades(atualizarCiclo);

        executarMudandaCsoCicloAntigoEZero(atualizarCiclo);
        return atualizarCiclo;
    }

    protected AtualizarCiclo atualizarCicloParaSituacaoDoisInexistente(AtualizarCiclo atualizarCiclo){

        atualizarCiclo.setSituacao((short)2);
        atualizarCiclo.setDescricaoSituacao("CICLO INEXISTENTE");
        executarAlteracoesNaEntidades(atualizarCiclo);
        
        executarMudandaCsoCicloAntigoEZero(atualizarCiclo);
        return atualizarCiclo;
    }

    protected AtualizarCiclo atualizarCicloParaSituacaoDoisCoincidente(AtualizarCiclo atualizarCiclo){

        atualizarCiclo.setSituacao((short)2);
        atualizarCiclo.setDescricaoSituacao("CICLO COINCIDENTE"); 
        executarAlteracoesNaEntidades(atualizarCiclo);

        executarMudandaCsoCicloAntigoEZero(atualizarCiclo);
        return atualizarCiclo;
    }

    /**
     * MÉTODOS PRIVADOS
     */
    private void executarAlteracoesNaEntidades(AtualizarCiclo atualizarCiclo){

        LocalDateTime hoje = LocalDateTime.now();

        atualizarCiclo.setDataHoraExecucao(hoje);  
        atualizarCiclo.setDataExecucao(ConverterData.localDateEmIntegerDataFormatoDB(LocalDate.now()) );
        atualizarCiclo.setHoraExecucao((short) hoje.getHour());
        atualizarCiclo.setMinExecucao((short) hoje.getMinute());
    }

    private void executarMudandaCsoCicloAntigoEZero(AtualizarCiclo atualizarCiclo) {
        if (atualizarCiclo.getCicloAntigo() == (short) 0) {

            Short cicloNoRegistroImovel = imovelRepository.buscarCicloDoImovelPelaMatricula(atualizarCiclo.getMatriculaImovel());
            atualizarCiclo.setCicloAntigo(cicloNoRegistroImovel);
            
        }
    }
}
