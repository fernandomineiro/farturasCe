package moduloFaturamento.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloFaturamento.dto.atualizarCiclo.AtualizarCicloAuditoriaDTO;
import moduloFaturamento.infra.service.AuditoriaService;
import moduloFaturamento.model.AtualizarCiclo;
import moduloFaturamento.model.common.DossieImovel;
import moduloFaturamento.model.common.Imovel;
import moduloFaturamento.regras.atualizarCiclo.AtualizarCicloAgendamentoRegra;
import moduloFaturamento.regras.dossie.DossieImovelAtualizarCiclo;
import moduloFaturamento.regras.leitura.LeituraRegraPesquisaLeitura;
import moduloFaturamento.repository.AtualizarCicloRepository;
import moduloFaturamento.repository.ClienteRepository;
import moduloFaturamento.repository.CronogramaFaturaRepository;
import moduloFaturamento.repository.common.DossieImovelRepository;
import moduloFaturamento.repository.common.ImovelRepository;
import moduloFaturamento.service.AtualizacaoCicloService;
import moduloFaturamento.util.ConvertObjectToJsonCesan;
import moduloFaturamento.util.ConverterData;

@Transactional
@Service
public class AtualizacaoCicloServiceImpl implements AtualizacaoCicloService {

    @Autowired
    private AtualizarCicloRepository repository;
    @Autowired
    private LeituraRegraPesquisaLeitura leituraRegraPesquisaLeitura;
    @Autowired
    private ImovelRepository imovelRepository;
    @Autowired
    private CronogramaFaturaRepository cronogramaFaturaRepository;
    @Autowired
    private AuditoriaService auditoriaService;
    @Autowired
    private DossieImovelAtualizarCiclo dossieImovelAtualizarCiclo;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private AtualizarCicloAgendamentoRegra atualizarCicloAgendamentoRegra;
    @Autowired
    private DossieImovelRepository dossieImovelRepository;

    @Override
    public void executarAtualizacaoDeCiclo() {

        verificarSeMatriculaImovelEstaEmDublicidadeNaRegraDeNegocio();
    
        List<Integer> listaDeMatricula = repository.buscarMatriculasComSituacaoZero();

        List<Integer> listaMatriculasValidas = verificarMatriculasQuePodemAtualizarCiclo(listaDeMatricula);

        executarRotinaParaAtualizarCicloDasMatriculas(listaMatriculasValidas);
    }
    
     /** METODOS PRIVADOS
     * 
     */
    private List<Integer> verificarMatriculasQuePodemAtualizarCiclo(List<Integer> listaDeMatricula) {

        List<Integer> listaMatriculasValidadasParaTrocarCiclo = new ArrayList<>();
        
        for(Integer matriculaImovel : listaDeMatricula) {

            Short cicloNoRegistroImovel = imovelRepository.buscarCicloDoImovelPelaMatricula(matriculaImovel);
            Short cicloNoRegistroAtualizarCiclo = repository.buscarCicloNaTabelaAtualizarCiclo(matriculaImovel);

            boolean atualizarCicloDuplicado = verificarSeCiclooSaoIguais(cicloNoRegistroImovel, cicloNoRegistroAtualizarCiclo,matriculaImovel);

            if (atualizarCicloDuplicado) continue;

            Short cidade = imovelRepository.buscarCidadeDoImovelExistentePelaMatricula(matriculaImovel);

            boolean atualizarCicloNaoConformeComRegraDeNegocios = 
                verificarSeExisteCicloCadastradoNoCronogramaDeFaturaNaRefernciaAtualEPosterior(matriculaImovel, cidade, cicloNoRegistroAtualizarCiclo);

            if (atualizarCicloNaoConformeComRegraDeNegocios) continue;  

            adicionarMatriculasValidas
                (cicloNoRegistroAtualizarCiclo, cidade,  buscarListaDeReferencias(), listaMatriculasValidadasParaTrocarCiclo, matriculaImovel );
        }
        
        return listaMatriculasValidadasParaTrocarCiclo;
    }

    private void  adicionarMatriculasValidas( Short cicloNoRegistroAtualizarCiclo, Short cidade, List<Integer> buscarListaDeReferencias, 
        List<Integer> listaMatriculasValidadasParaTrocarCiclo, Integer matriculaImovel) {

        boolean matriculaPodeTrocarCicloNoRegistro = repository.verificarSeMatriculaPodeTrocarCiclo(cidade, cicloNoRegistroAtualizarCiclo, buscarListaDeReferencias );

        if (matriculaPodeTrocarCicloNoRegistro ) {

            listaMatriculasValidadasParaTrocarCiclo.add(matriculaImovel);
        }
    }

    private boolean verificarSeCiclooSaoIguais(Short cicloNoRegistroImovel, Short cicloNoRegistroAtualizarCiclo, Integer matriculaImovel) {

        if (cicloNoRegistroImovel == cicloNoRegistroAtualizarCiclo) {

            AtualizarCiclo atualizarCiclo = repository.buscarPorMatriculaESituacaoZero(matriculaImovel);

            AtualizarCiclo atualizarCicloModificado = atualizarCicloAgendamentoRegra.atualizarCicloNoAgendamentoNaSituacaoDoisCoincidente(atualizarCiclo);            
            repository.save(atualizarCicloModificado);
            return true;
        }
        return false;
    }

    private boolean verificarSeExisteCicloCadastradoNoCronogramaDeFaturaNaRefernciaAtualEPosterior(Integer matriculaImovel, Short cidade, Short cicloNoRegistroAtualizarCiclo) {

        boolean naoExisteNasTresReferencias = cronogramaFaturaRepository.verificarSeExisteCicloECidadeNasTresReferencias
            (cidade, cicloNoRegistroAtualizarCiclo, buscarListaDeReferencias());

        if(naoExisteNasTresReferencias) {

            AtualizarCiclo atualizarCiclo = repository.buscarPorMatriculaESituacaoZero(matriculaImovel);

            AtualizarCiclo atualizarCicloModificado = atualizarCicloAgendamentoRegra.atualizarCicloNoAgendamentoNaSituacaoDoisInexistente(atualizarCiclo);
            repository.save(atualizarCicloModificado);
            return true;
        }
        return false;
    }

    private void verificarSeMatriculaImovelEstaEmDublicidadeNaRegraDeNegocio() {
        
        List<Integer> listaDeMatricula = repository.buscarMatriculasComSituacaoZero();

        List<Integer> listaMatriculasNaoPesquisarNovamente = new ArrayList<>();

        for (Integer matriculaImovel : listaDeMatricula) {
            
            List<AtualizarCiclo> listaAtualizarCiclo = repository.findByMatriculaImovelAndSituacao(matriculaImovel, (short) 0);

            if (listaAtualizarCiclo.size() > 1 && !listaMatriculasNaoPesquisarNovamente.contains(matriculaImovel)) {
                
                Collections.sort(listaAtualizarCiclo, Comparator.comparingInt(AtualizarCiclo::getDataInclusao).reversed() );

                //Removendo a primeira depois de ordenador desc, pois será ela avaliado pelo sistema
                listaAtualizarCiclo.remove(0);

                atualizarCiclosDuplicados(listaAtualizarCiclo);

                listaMatriculasNaoPesquisarNovamente.add(matriculaImovel);
            }
        }
    }

    private void atualizarCiclosDuplicados(List<AtualizarCiclo> listaAtualizarCiclo) {
        for (AtualizarCiclo atualizarCiclo : listaAtualizarCiclo) {
            
            AtualizarCiclo atualizarCicloModificado = atualizarCicloAgendamentoRegra.atualizarCicloNoAgendamentoNaSituacaoDoisDuplicidade(atualizarCiclo);
            repository.save(atualizarCicloModificado);           
        }
    }

    private List<Integer> buscarListaDeReferencias() {

        Integer referenciaAtual = ConverterData.localDateEmIntegerReferenciaFormatoDB(LocalDate.now());
        Integer refenciaAnteriorDaAtual = leituraRegraPesquisaLeitura.buscarReferenciaAnterior(referenciaAtual);
        Integer referenciaPosteriorAtual = buscarReferenciaPosterior(referenciaAtual);
        
        return List.of(referenciaAtual, refenciaAnteriorDaAtual, referenciaPosteriorAtual);
    }


    private void executarRotinaParaAtualizarCicloDasMatriculas(List<Integer> listaDeMatricula) {

        for (Integer matriculaImovel : listaDeMatricula) {
            
            Short cicloNoRegistroImovel = imovelRepository.buscarCicloDoImovelPelaMatricula(matriculaImovel);
            AtualizarCiclo atualizarCicloAnterior = repository.buscarPorMatriculaESituacaoZero(matriculaImovel);

            AtualizarCiclo atualizarCicloNovo = atualizarCicloAgendamentoRegra.atualizarCicloNoAgendamentoNaSituacaoUm(atualizarCicloAnterior);
            repository.save(atualizarCicloNovo);

            Imovel imovel = alterarCicloDoImovel(matriculaImovel, atualizarCicloNovo);
            imovelRepository.save(imovel);

            DossieImovel construirDossieImovel = executarRotinaDossieImovel(matriculaImovel, cicloNoRegistroImovel, imovel.getCiclo());
            dossieImovelRepository.save(construirDossieImovel);

            executarRotinaDaAuditoria(matriculaImovel, cicloNoRegistroImovel, imovel.getCiclo());
        }
    }

    private void executarRotinaDaAuditoria(Integer matriculaImovel, Short cicloNoRegistroImovel, Short ciclo) {

        Long idUsuarioAlteracaoAuditoria = 1L;

        AtualizarCicloAuditoriaDTO entidadeParaAtenderAuditoriaAntes = new AtualizarCicloAuditoriaDTO(cicloNoRegistroImovel);
        String jsonAntes = ConvertObjectToJsonCesan.execute(entidadeParaAtenderAuditoriaAntes);

        AtualizarCicloAuditoriaDTO entidadeParaAtenderAuditoriaPosterior = new AtualizarCicloAuditoriaDTO(ciclo);
        String jsonPosterior = ConvertObjectToJsonCesan.execute(entidadeParaAtenderAuditoriaPosterior);

        auditoriaService.gerarAuditoriaAlteracao(matriculaImovel.longValue(), jsonAntes, jsonPosterior, 23L, "Imóvel", idUsuarioAlteracaoAuditoria);
    }

    private Imovel alterarCicloDoImovel(Integer matriculaImovel, AtualizarCiclo atualizarCicloNovo) {
        Imovel imovel = imovelRepository.findByMatriculaImovelAndDataHoraExclusaoIsNull(matriculaImovel);
        imovel.setCiclo(atualizarCicloNovo.getCicloNovo());
        return imovel;
    }

    private DossieImovel executarRotinaDossieImovel(Integer matriculaImovel, Short cicloNoRegistroImovel, Short ciclo) {

        String idUsuarioAlteracaoDossie = "SISCOM";

        Short dvDaMatricula = imovelRepository.buscarDvDoImovelPelaMatricula(matriculaImovel);
        Integer clienteDaMatricula = imovelRepository.buscarCdClienteDoImovel(matriculaImovel);
        Short dvDoCliente = clienteRepository.buscarDVDoCliente(clienteDaMatricula);

        return dossieImovelAtualizarCiclo.construirDossieImovel
            ("MUDANÇA DE CICLO DE " +cicloNoRegistroImovel+" PARA "+ciclo, matriculaImovel, dvDaMatricula, clienteDaMatricula, dvDoCliente, idUsuarioAlteracaoDossie);
    }

    private Integer buscarReferenciaPosterior(Integer referencia) {

        Integer ano = referencia / 100;
        Integer mes = referencia % 100;

        if ((mes + 1) == 13) {
            ano ++;
            mes = 01;            
        } else {
            mes ++;
        }

        String format = String.format("%d%02d", ano, mes);

        return Integer.valueOf(format);
    }
}
