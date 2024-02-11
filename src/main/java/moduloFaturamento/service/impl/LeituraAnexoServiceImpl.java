package moduloFaturamento.service.impl;

import moduloFaturamento.anexos.model.LeituraAnexo;
import moduloFaturamento.anexos.repository.LeituraAnexoRepository;
import moduloFaturamento.autenticacao.JwtTokenProvider;
import moduloFaturamento.dto.GenericoDownloadArquivoAnexoDTO;
import moduloFaturamento.dto.leitura.LeituraAnexoDTO;
import moduloFaturamento.dto.leituraAnexo.LeituraAnexoRespostaWrapperDTO;
import moduloFaturamento.dto.leituraAnexo.projection.LeituraAnexoRespostaGridProjectionDTO;
import moduloFaturamento.dto.leituraConsumoImovel.LeituraConsumoImovelSalvarAnexoDTO;
import moduloFaturamento.infra.service.AuditoriaService;
import moduloFaturamento.model.Leitura;
import moduloFaturamento.repository.LeituraRepository;
import moduloFaturamento.service.LeituraAnexoService;
import moduloFaturamento.util.ConvertObjectToJsonCesan;
import moduloFaturamento.validacoes.leitura.LeituraConsumoImovelConsultaValidacao;
import moduloFaturamento.validacoes.leituraAnexo.LeituraAnexoCadastroValidacao;
import moduloFaturamento.validacoes.leituraAnexo.LeituraAnexoConsultaValidacao;
import moduloFaturamento.validacoes.leituraAnexo.LeituraAnexoRemocaoValidacao;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class LeituraAnexoServiceImpl implements LeituraAnexoService {

    @Autowired
    private LeituraAnexoRepository leituraAnexoRepository;
    @Autowired
    private LeituraRepository leituraRepository;
    @Autowired
    private LeituraConsumoImovelConsultaValidacao leituraConsumoImovelConsultaValidacao;
    @Autowired
    private LeituraAnexoConsultaValidacao leituraAnexoConsultaValidacao;
    @Autowired
    private LeituraAnexoCadastroValidacao leituraAnexoCadastroValidacao;
    @Autowired
    private LeituraAnexoRemocaoValidacao leituraAnexoRemocaoValidacao;
    @Autowired
    private AuditoriaService auditoriaService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @Override
    @Transactional(value = "chainedTransactionManager")
    public LeituraAnexoRespostaWrapperDTO buscarLeiturasPorIdLeitura(Long idLeitura, Pageable pageable){
        this.leituraConsumoImovelConsultaValidacao.gerenciarValidacao(idLeitura);
        
        LeituraAnexoRespostaWrapperDTO wrapper = new LeituraAnexoRespostaWrapperDTO();
        List<LeituraAnexoRespostaGridProjectionDTO> dto = this.leituraAnexoRepository.buscarAnexosDeUmaLeitura(idLeitura);
        Integer totalArquivo = this.leituraAnexoRepository.buscarAnexosDeUmaLeitura(idLeitura).stream().mapToInt(LeituraAnexoRespostaGridProjectionDTO::getTamanhoArquivo).sum();
        long totalRegistro = this.leituraAnexoRepository.countByLeituraId(idLeitura);

        wrapper.setAnexos(dto);
        wrapper.setTotalRegistro(totalRegistro);
        wrapper.setTotalArquivo(totalArquivo);

        return  wrapper;
    }

    @Override
    @Transactional(value = "chainedTransactionManager")
    public void executarFluxoSalvarAnexoConsumoImovel(LeituraConsumoImovelSalvarAnexoDTO dto, String token){
        this.leituraConsumoImovelConsultaValidacao.gerenciarValidacao(dto.getIdLeitura());

        Leitura leitura = this.leituraRepository.buscarLeituraExistentePorId(dto.getIdLeitura());

        LeituraAnexo leituraAnexo = this.retornarConstrucaoLeituraAnexo(leitura, dto.getAnexoDTO(), token);
        this.leituraAnexoCadastroValidacao.validarCadastro(leituraAnexo.getNomeArquivo(), leituraAnexo.getTamanhoArquivo());
        this.salvarAnexoValido(leituraAnexo, token);
    }

    @Override
    @Transactional(value = "chainedTransactionManager")
    public void executarFluxoSalvarAnexoLeituraFatura(Leitura leitura, LeituraAnexoDTO anexoDTO , String token){
        LeituraAnexo leituraAnexo = this.retornarConstrucaoLeituraAnexo(leitura, anexoDTO, token);
        this.leituraAnexoCadastroValidacao.validarCadastro(leituraAnexo.getNomeArquivo(), leituraAnexo.getTamanhoArquivo());
        this.salvarAnexoValido(leituraAnexo, token);
    }

    @Override
    @Transactional(value = "chainedTransactionManager")
    public void executarFluxoRemoverAnexo(Long id, String token){
        this.leituraAnexoRemocaoValidacao.validarConsulta(id);

        LeituraAnexo leituraAnexo = this.leituraAnexoRepository.buscarAnexoLeituraExistentePorId(id);

        this.removerAnexoValido(leituraAnexo, this.jwtTokenProvider.buscarIdUsuario(token));
    }

    @Override
    @Transactional(value = "chainedTransactionManager")
    public GenericoDownloadArquivoAnexoDTO retornarArquivoParaDownload(Long id){
        this.leituraAnexoConsultaValidacao.validarConsulta(id);

        LeituraAnexo leituraAnexo = this.leituraAnexoRepository.buscarAnexoLeituraExistentePorId(id);
        GenericoDownloadArquivoAnexoDTO dto = new GenericoDownloadArquivoAnexoDTO();

        dto.setData(Base64.encodeBase64String(leituraAnexo.getArquivo()));
        dto.setNomeArquivo(leituraAnexo.getNomeArquivo());

        return dto;
    }

    private void removerAnexoValido(LeituraAnexo leituraAnexo, Long idUsuario) {
        this.leituraAnexoRepository.delete(leituraAnexo);

        this.auditoriaService.gerarAuditoriaRemocao(leituraAnexo.getId(), ConvertObjectToJsonCesan.execute(leituraAnexo), 68L, "Anexos Leitura", idUsuario);
    }

    private void salvarAnexoValido(LeituraAnexo leituraAnexo, String token) {
        Long idUsuario = this.jwtTokenProvider.buscarIdUsuario(token);
        this.leituraAnexoRepository.save(leituraAnexo);
        this.auditoriaService.gerarAuditoriaCadastramento(leituraAnexo.getId(), ConvertObjectToJsonCesan.execute(leituraAnexo), 68L, "Anexos Leitura", idUsuario);
    }

    private LeituraAnexo retornarConstrucaoLeituraAnexo(Leitura leitura, LeituraAnexoDTO dto, String token){
        String loginUsuario = this.jwtTokenProvider.buscarLogin(token);

        byte[] arquivo = Base64.decodeBase64(dto.getData());

        LeituraAnexo leituraAnexo =  new LeituraAnexo();

        leituraAnexo.setNomeArquivo(dto.getNomeArquivo());
        leituraAnexo.setDescricao(dto.getDescricao());
        leituraAnexo.setTamanhoArquivo(arquivo.length);
        leituraAnexo.setDataHoraInclusao(LocalDateTime.now());
        leituraAnexo.setLeituraId(leitura.getId());
        leituraAnexo.setArquivo(arquivo);
        leituraAnexo.setUsuario(loginUsuario);
        leituraAnexo.setAcessoRestrito("N");

        return leituraAnexo;
    }



}
