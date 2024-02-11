package moduloFaturamento.service.impl.common;

import moduloFaturamento.dto.servico.projection.ServicoDropDownDescricaoPrefixadoComCodigoDTO;
import moduloFaturamento.repository.common.ImovelRepository;
import moduloFaturamento.repository.common.ServicoRepository;
import moduloFaturamento.service.common.ServicoService;
import moduloFaturamento.validacoes.imovel.ImovelConsultaValidacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicoServiceImpl implements ServicoService {
    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private ImovelRepository imovelRepository;
    @Autowired
    private ImovelConsultaValidacao imovelConsultaValidacao;

    @Override
    public List<ServicoDropDownDescricaoPrefixadoComCodigoDTO> buscarServicosParaDropDownCadastroCobrancaEmFatura(Integer matriculaImovel, Short dv) {
        this.imovelConsultaValidacao.validarMatriculaImovel(matriculaImovel, dv);
        Short cdCidade = this.imovelRepository.buscarCidadeDoImovelExistentePelaMatricula(matriculaImovel, dv);
        return this.servicoRepository.buscarServicosParaDropDownCadastroCobrancaEmFatura(cdCidade);
    }

    @Override
    public List<ServicoDropDownDescricaoPrefixadoComCodigoDTO> buscarServicosParaDropDownEdicaoCobrancaEmFatura(Integer matriculaImovel, Short dv) {
        this.imovelConsultaValidacao.validarMatriculaImovel(matriculaImovel, dv);
        Short cdCidade = this.imovelRepository.buscarCidadeDoImovelExistentePelaMatricula(matriculaImovel, dv);
        return this.servicoRepository.buscarServicosParaDropDownEdicaoCobrancaEmFatura(cdCidade);
    }
}
