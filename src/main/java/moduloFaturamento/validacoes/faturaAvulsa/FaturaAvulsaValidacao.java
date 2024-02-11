package moduloFaturamento.validacoes.faturaAvulsa;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.model.FaturaAvulsa;
import moduloFaturamento.model.common.Cliente;
import moduloFaturamento.model.common.Imovel;
import moduloFaturamento.repository.ClienteRepository;
import moduloFaturamento.repository.FaturaAvulsaRepository;
import moduloFaturamento.repository.common.ImovelRepository;
import moduloFaturamento.validacoes.common.CommonUtilValidacoes;
import moduloFaturamento.validacoes.solicitacaoServico.SolicitacaoServicoConsultaValidacao;

public abstract class FaturaAvulsaValidacao {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CommonUtilValidacoes commonUtilValidacoes;
    @Autowired
    private SolicitacaoServicoConsultaValidacao solicitacaoServicoConsultaValidacao;
    @Autowired 
    private ImovelRepository imovelRepository;
    @Autowired
    private FaturaAvulsaRepository repository;

    protected void gerarExcecaoSeCdClienteExiste(Integer cdCliente, Short dvCliente){

        Optional<Cliente> cliente = clienteRepository.findById(cdCliente);

        if (cliente.isEmpty() || !cliente.get().getDvCliente().equals(dvCliente)) {
            throw new MsgGenericaPersonalizadaException("Código do cliente inválido.");
        }
    }

    protected void gerarExcecaoSeCampoPreenchidoVerificarMatriculaImovelSeExiste(Integer matriculaImovel, Short dvMatriculaImovel){

        if (Optional.ofNullable(matriculaImovel).orElse(0) != 0 ) {

            Optional<Imovel> verificarSeExisteMatricula = imovelRepository.findByMatriculaImovelAndDvAndDataHoraExclusaoIsNull(matriculaImovel, dvMatriculaImovel);

            if (verificarSeExisteMatricula.isEmpty()) {
                throw new MsgGenericaPersonalizadaException("Matricula ou dv não existe.");
            }
        }
    }

    protected void gerarExcecaoSeCampoPreenchidoVerificarReferenciaDataValido(LocalDate referenciaData){

        if (referenciaData != null) {
            commonUtilValidacoes.gerarExcessaoReferenciaInvalidaFormatoDB(referenciaData);
        }
    }

    protected void gerarExcecaoSeExisteSSValida(Integer refAtendimento, Integer cdAtendimento, Short seqSS) {
        if (Optional.ofNullable(refAtendimento).orElse(0) != 0) {
            solicitacaoServicoConsultaValidacao.gerarExcecaoSSNaoEncontrada(refAtendimento, cdAtendimento, seqSS);
        }  
    }

    protected void gerarExcecaoSeExisteUmaFaturaAvulsa(Long id) {

        Optional<FaturaAvulsa> buscarFaturaAvulsa = repository.findById(id);

        if (buscarFaturaAvulsa.isEmpty()) {
            throw new MsgGenericaPersonalizadaException("Fatura avulsa não existe.");
        }

        
    }
    
    
}
