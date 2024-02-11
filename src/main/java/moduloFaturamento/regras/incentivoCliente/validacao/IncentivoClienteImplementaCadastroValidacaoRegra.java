package moduloFaturamento.regras.incentivoCliente.validacao;

import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.model.IncentivoCliente;
import moduloFaturamento.model.TipoIncentivoCliente;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
abstract class IncentivoClienteImplementaCadastroValidacaoRegra extends IncentivoClienteValidacaoRegra {


    protected void gerarExecaoTodosTiposIncentivoIncluindoNuloJaEstaEmAberto(LocalDateTime dataVigenteInicio, LocalDateTime dataVigenteFim) {
        List<Integer> idsTipoIncentivo = this.tipoIncentivoClienteRepository.buscarIdTodosIncentivoCLiente();

        boolean todosOsTiposIncentivoJaEstaPresenteEmAlgumCadastro = true;
        Integer idSemTipoSelecionado = null;
        idsTipoIncentivo.add(idSemTipoSelecionado);

        for (Integer idTipoIncentivo : idsTipoIncentivo) {
            Optional<List<IncentivoCliente>> incentivoOptional;
            if (idTipoIncentivo == null) {
                incentivoOptional = this.incentivoClienteRepository.buscarIncentivoPeloSeuTipoEmAbertoCadastro(dataVigenteInicio, dataVigenteFim);
            } else {
                incentivoOptional = this.incentivoClienteRepository.buscarIncentivoPeloSeuTipoEmAbertoCadastro(dataVigenteInicio, dataVigenteFim, idTipoIncentivo);
            }

            if (incentivoOptional.isEmpty()) {
                todosOsTiposIncentivoJaEstaPresenteEmAlgumCadastro = false;
            }
        }

        if (todosOsTiposIncentivoJaEstaPresenteEmAlgumCadastro) {
            throw new MsgGenericaPersonalizadaException("Todos os tipos de incentivo já estão em aberto para o período vigente", HttpStatus.CONFLICT);
        }
    }


    protected void gerarExecaoTipoInformadoPossuiRegistroComPeriodoVigenteEmAbertoJaExistente(LocalDateTime dataVigenteInicio, LocalDateTime dataVigenteFim, Integer idTipoIncentivo) {
        Optional<List<IncentivoCliente>> incentivoOptional;
        if (idTipoIncentivo == null) {
            incentivoOptional = this.incentivoClienteRepository.buscarIncentivoPeloSeuTipoEmAbertoCadastro(dataVigenteInicio, dataVigenteFim);
        } else {
            incentivoOptional = this.incentivoClienteRepository.buscarIncentivoPeloSeuTipoEmAbertoCadastro(dataVigenteInicio, dataVigenteFim, idTipoIncentivo);
        }

        if (incentivoOptional.isPresent()) {
            if (idTipoIncentivo != null) {
                TipoIncentivoCliente tipo = this.tipoIncentivoClienteRepository.buscarTipoExistentePorId(idTipoIncentivo);
                throw new MsgGenericaPersonalizadaException("Já existe um tipo " + tipo.getDescricao() + " em aberto para o período vigente", HttpStatus.CONFLICT);
            } else {
                throw new MsgGenericaPersonalizadaException("Já existe um incentivo em aberto para o período vigente", HttpStatus.CONFLICT);
            }
        }
    }
}
