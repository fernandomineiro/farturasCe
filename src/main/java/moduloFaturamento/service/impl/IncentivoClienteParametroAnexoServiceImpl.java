package moduloFaturamento.service.impl;

import moduloFaturamento.anexos.model.ParametroIncentivoClienteAnexo;
import moduloFaturamento.anexos.repository.ParametroIncentivoClienteAnexoRepository;
import moduloFaturamento.autenticacao.JwtTokenProvider;
import moduloFaturamento.dto.GenericoDownloadArquivoAnexoDTO;
import moduloFaturamento.dto.incentivoCliente.IncentivoClienteParametroRespostaCadastroIdentidadeDTO;
import moduloFaturamento.dto.incentivoCliente.anexo.ParametroIncentivoAnexoDTO;
import moduloFaturamento.dto.incentivoCliente.anexo.ParametroIncentivoClienteAnexoRespostaWrapperDTO;
import moduloFaturamento.dto.incentivoCliente.anexo.ParametroIncentivoClienteRespostaAnexoDTO;
import moduloFaturamento.dto.incentivoCliente.anexo.projection.ParametroIncentivoAnexoRespostaGridProjectionDTO;
import moduloFaturamento.infra.service.AuditoriaService;
import moduloFaturamento.model.IncentivoCliente;
import moduloFaturamento.service.IncentivoClienteParametroAnexoService;
import moduloFaturamento.util.ConvertObjectToJsonCesan;
import moduloFaturamento.validacoes.incentivoCliente.incentivoClienteParametroAnexo.IncentivoClienteParametroAnexoCadastroValidacao;
import moduloFaturamento.validacoes.incentivoCliente.incentivoClienteParametroAnexo.IncentivoClienteParametroAnexoConsultaValidacao;
import moduloFaturamento.validacoes.incentivoCliente.incentivoClienteParametroAnexo.IncentivoClienteParametroAnexoDownloadValidacao;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class IncentivoClienteParametroAnexoServiceImpl implements IncentivoClienteParametroAnexoService {

    @Autowired
    private ParametroIncentivoClienteAnexoRepository parametroIncentivoClienteAnexoRepository;
    @Autowired
    private AuditoriaService auditoriaService;
    @Autowired
    private IncentivoClienteParametroAnexoCadastroValidacao incentivoClienteParametroAnexoCadastroValidacao;
    @Autowired
    private IncentivoClienteParametroAnexoConsultaValidacao incentivoClienteParametroAnexoConsultaValidacao;
    @Autowired
    private IncentivoClienteParametroAnexoDownloadValidacao incentivoClienteParametroAnexoDownloadValidacao;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public ParametroIncentivoClienteRespostaAnexoDTO executarFluxoSalvarAnexo(ParametroIncentivoAnexoDTO dto, IncentivoCliente dtoParametroIncentivoCliente, String token){
        ParametroIncentivoClienteAnexo parametroIncentivoClienteAnexo = this.retornarConstrucaoAnexo(dtoParametroIncentivoCliente.getId(), dto, token);

        this.incentivoClienteParametroAnexoCadastroValidacao.validarCadastro(dto.getNomeArquivo(), parametroIncentivoClienteAnexo.getTamanhoArquivo());

        List<ParametroIncentivoClienteAnexo> anexosDoIncentivo = this.parametroIncentivoClienteAnexoRepository.findByIdParametroIncentivoCliente(dtoParametroIncentivoCliente.getId());
        for(ParametroIncentivoClienteAnexo entidade : anexosDoIncentivo){
            this.removerAnexoValido(entidade, this.jwtTokenProvider.buscarIdUsuario(token));
        }

        ParametroIncentivoClienteAnexo salvo = this.cadastrarAnexoValido(parametroIncentivoClienteAnexo, token);

        return new ParametroIncentivoClienteRespostaAnexoDTO(salvo.getId());
    }


    @Override
    public ParametroIncentivoClienteAnexoRespostaWrapperDTO buscarAnexoIncentivoCliente(Integer idIncentivoCliente, Pageable pageable){
        this.incentivoClienteParametroAnexoConsultaValidacao.validar(idIncentivoCliente);

        ParametroIncentivoClienteAnexoRespostaWrapperDTO wrapper = new ParametroIncentivoClienteAnexoRespostaWrapperDTO();

        List<ParametroIncentivoAnexoRespostaGridProjectionDTO> dto = this.parametroIncentivoClienteAnexoRepository.buscarAnexos(idIncentivoCliente);
        Integer totalArquivo = this.parametroIncentivoClienteAnexoRepository.buscarAnexos(idIncentivoCliente).stream().mapToInt(ParametroIncentivoAnexoRespostaGridProjectionDTO::getTamanhoArquivo).sum();
        long totalRegistro = this.parametroIncentivoClienteAnexoRepository.countByIdParametroIncentivoCliente(idIncentivoCliente);

        wrapper.setAnexos(dto);
        wrapper.setTotalRegistro(totalRegistro);
        wrapper.setTotalArquivo(totalArquivo);

        return  wrapper;
    }

    @Override
    public GenericoDownloadArquivoAnexoDTO retornarArquivoParaDownload(Long id){
        this.incentivoClienteParametroAnexoDownloadValidacao.validar(id);

        ParametroIncentivoClienteAnexo incentivoAnexo = this.parametroIncentivoClienteAnexoRepository.buscarAnexoExistentePorId(id);
        GenericoDownloadArquivoAnexoDTO dto = new GenericoDownloadArquivoAnexoDTO();

        dto.setData(Base64.encodeBase64String(incentivoAnexo.getArquivo()));
        dto.setNomeArquivo(incentivoAnexo.getNomeArquivo());

        return dto;
    }


    private ParametroIncentivoClienteAnexo cadastrarAnexoValido(ParametroIncentivoClienteAnexo anexo, String token) {
        Long idUsuario = this.jwtTokenProvider.buscarIdUsuario(token);


        this.parametroIncentivoClienteAnexoRepository.save(anexo);
        this.auditoriaService.gerarAuditoriaCadastramento(anexo.getId(), ConvertObjectToJsonCesan.execute(anexo), 73L, "Parâmetro de Negociação", idUsuario);

        return anexo;
    }

    private void removerAnexoValido(ParametroIncentivoClienteAnexo anexo, Long idUsuario) {
        this.parametroIncentivoClienteAnexoRepository.delete(anexo);

        this.auditoriaService.gerarAuditoriaRemocao(anexo.getId(), ConvertObjectToJsonCesan.execute(anexo), 73L, "Parâmetro de Negociação", idUsuario);
    }

    private ParametroIncentivoClienteAnexo retornarConstrucaoAnexo(Integer idParametroIncentivo, ParametroIncentivoAnexoDTO dto, String token){
        String loginUsuario = this.jwtTokenProvider.buscarLogin(token);

        byte[] arquivo = Base64.decodeBase64(dto.getData());

        ParametroIncentivoClienteAnexo anexo =  new ParametroIncentivoClienteAnexo();

        anexo.setNomeArquivo(dto.getNomeArquivo());
        anexo.setDescricao(dto.getDescricao());
        anexo.setTamanhoArquivo(arquivo.length);
        anexo.setDataHoraInclusao(LocalDateTime.now());
        anexo.setIdParametroIncentivoCliente(idParametroIncentivo);
        anexo.setArquivo(arquivo);
        anexo.setUsuario(loginUsuario);

        return anexo;
    }
}
