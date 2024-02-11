package moduloFaturamento.validacoes.incentivoCliente.incentivoClienteParametro;

import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.model.IncentivoCliente;
import moduloFaturamento.model.TipoIncentivoCliente;
import moduloFaturamento.repository.IncentivoClienteRepository;
import moduloFaturamento.repository.TipoIncentivoClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
abstract class IncentivoClienteParametroValidacao {

    @Autowired
    private IncentivoClienteRepository incentivoClienteRepository;
    @Autowired
    private TipoIncentivoClienteRepository tipoIncentivoClienteRepository;

    protected void gerarExcecaoRegistroNaoEncontrado(Integer id){
        Optional<IncentivoCliente> incentivoOptional = this.incentivoClienteRepository.findByIdAndDataHoraExclusaoIsNull(id);

        if(incentivoOptional.isEmpty()){
            throw new MsgGenericaPersonalizadaException("Parâmetro de incentivo não encontrato ou foi removido");
        }
    }

    protected void gerarExcecaoTipoIncentivoNaoEncontrado(Integer id){
        if(id != null){
            Optional<TipoIncentivoCliente> tipoIncentivoClienteOptional = this.tipoIncentivoClienteRepository.findById(id);

            if(tipoIncentivoClienteOptional.isEmpty()){
                throw new MsgGenericaPersonalizadaException("Tipo de incentivo não encontrado ou foi removido");
            }
        }

    }
}

