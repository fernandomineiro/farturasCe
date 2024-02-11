package moduloFaturamento.validacoes.diasVencimento;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.model.DiasVencimento;
import moduloFaturamento.repository.FaturamentoDiasVencimentoRepository;

@Service
public abstract class DiasVencimentoValidacao {

    @Autowired
    private FaturamentoDiasVencimentoRepository repository;

    protected void verificarSeDiaExisteDiaNoCiclo(Short ciclo, Short dia, String acao) {
        List<Short> pegarCicloParaConsulta = new ArrayList<>();
        pegarCicloParaConsulta.add(ciclo);
        List<DiasVencimento> consultarCicloeDia = repository
                .findByIdDiasVencimentoFaturamentoCicloIn(pegarCicloParaConsulta).stream()
                .filter(dias -> dias.getIdDiasVencimentoFaturamento().getDia().equals(dia)).collect(Collectors.toList());

        switch (acao) {
            case "criar":
                if (!consultarCicloeDia.isEmpty()) {
                    throw new MsgGenericaPersonalizadaException("Dia já criado.");
                }
                break;
            case "deletar":
                if (consultarCicloeDia.isEmpty()) {
                    throw new MsgGenericaPersonalizadaException("Dia não existente para deletar.");
                }
                break;
        }

    }

}
