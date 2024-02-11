package moduloFaturamento.validacoes.incentivoCliente.incentivoClienteParametroDetalhe;

import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.model.IncentivoClienteDetalhe;
import moduloFaturamento.repository.IncentivoClienteDetalheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
abstract class IncentivoClienteParametroDetalheValidacao {

    @Autowired
    private IncentivoClienteDetalheRepository incentivoClienteDetalheRepository;

    protected void gerarExcecaoRegistroNaoEncontrado(Integer id){
        Optional<IncentivoClienteDetalhe> incentivoOptional = this.incentivoClienteDetalheRepository.findByIdAndDataHoraExclusaoIsNull(id);

        if(incentivoOptional.isEmpty()){
            throw new MsgGenericaPersonalizadaException("Detalhe do parametro de incentivo não encontrato ou foi removido");
        }
    }


}

