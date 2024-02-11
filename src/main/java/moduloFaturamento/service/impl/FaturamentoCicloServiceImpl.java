package moduloFaturamento.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import moduloFaturamento.autenticacao.JwtTokenProvider;
import moduloFaturamento.dto.diasCiclos.FaturamentoDiasVencimentoDeletarDTO;
import moduloFaturamento.dto.diasCiclos.FaturamentoDiasVencimentoListaDTO;
import moduloFaturamento.dto.diasCiclos.FaturamentoDiasVencimentoNovoDTO;
import moduloFaturamento.infra.service.AuditoriaService;
import moduloFaturamento.mapper.DiasVencimentoListaMapper;
import moduloFaturamento.mapper.DiasVencimentoNovoMapper;
import moduloFaturamento.model.DiasVencimento;
import moduloFaturamento.model.IdDiasVencimento;
import moduloFaturamento.repository.FaturamentoDiasVencimentoRepository;
import moduloFaturamento.service.FaturamentoCicloService;
import moduloFaturamento.validacoes.diasVencimento.DiasVencimentoCadastrarValidacao;
import moduloFaturamento.validacoes.diasVencimento.DiasVencimentoRemoverValidacao;

@Service
@Transactional
public class FaturamentoCicloServiceImpl implements FaturamentoCicloService {

    @Autowired
    private FaturamentoDiasVencimentoRepository repository;
    @Autowired
    private DiasVencimentoListaMapper diasVencimentoListaMapper;
    @Autowired
    private DiasVencimentoCadastrarValidacao validacaoCadastrar;
    @Autowired
    private DiasVencimentoRemoverValidacao validacaoRemover;
    @Autowired
    private DiasVencimentoNovoMapper diasVencimentoNovoMapper;
    @Autowired
    private AuditoriaService auditoriaService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public List<Integer> buscarListaCiclos() {    
        return repository.listaCiclos();
    }

    @Override
    public List<FaturamentoDiasVencimentoListaDTO> buscarDiasDosCiclos(List<Short> ciclos) {
       List<DiasVencimento> lista =  repository.findByIdDiasVencimentoFaturamentoCicloIn(ciclos);
       return lista.stream()
                    .map(ciclo -> diasVencimentoListaMapper.toDto(ciclo))
                    .collect(Collectors.toList());
    }

    @Override
    public FaturamentoDiasVencimentoListaDTO novoDiaPorCiclo(FaturamentoDiasVencimentoNovoDTO ciclo, String token) {

        validacaoCadastrar.gerenciarValidacaoParaCadastrar(ciclo.getCiclo(), ciclo.getDia(), "criar");

        DiasVencimento diaNovoNoCiclo = diasVencimentoNovoMapper.toEntity(ciclo);

        DiasVencimento salvoNovoDiasVencimento = salvarDiaNoCicloValido(diaNovoNoCiclo, token);

        return diasVencimentoListaMapper.toDto(salvoNovoDiasVencimento);
    }

    @Override
    public void deletarDiaDoCiclo(FaturamentoDiasVencimentoDeletarDTO ciclo, String token) {
        
        validacaoRemover.gerenciarValidacaoParaRemover(ciclo.getCiclo(), ciclo.getDia(), "deletar");
        
        deletarDiaNoCicloValido(new IdDiasVencimento(ciclo.getCiclo(), ciclo.getDia()), token);
    }
    
    public DiasVencimento salvarDiaNoCicloValido(DiasVencimento diaNovoNoCiclo, String token) {

        Long idUsuario = jwtTokenProvider.buscarIdUsuario(token);
        String jsonDiaNoCicloAntesDeCadstrar = rotinaParaAutitoria(diaNovoNoCiclo.getIdDiasVencimentoFaturamento().getCiclo());

        DiasVencimento salvoNovoDiasVencimento = repository.save(diaNovoNoCiclo);

        String jsonDiaNoCicloDepoisDoCadastrado = rotinaParaAutitoria(diaNovoNoCiclo.getIdDiasVencimentoFaturamento().getCiclo()); 
        
        auditoriaService.gerarAuditoria(diaNovoNoCiclo.getIdDiasVencimentoFaturamento().getCiclo().longValue(), jsonDiaNoCicloAntesDeCadstrar, jsonDiaNoCicloDepoisDoCadastrado,
		 		60L, "ciclo", idUsuario);

        return salvoNovoDiasVencimento;
    }

    public void deletarDiaNoCicloValido(IdDiasVencimento ciclo, String token){

        Long idUsuario = jwtTokenProvider.buscarIdUsuario(token);
        String jsonDiaNoCicloAntesDeletar = rotinaParaAutitoria(ciclo.getCiclo());

        repository.deleteById(new IdDiasVencimento(ciclo.getCiclo(), ciclo.getDia()));

        String jsonDiaNoCicloDepoisDeDeeletar = rotinaParaAutitoria(ciclo.getCiclo());

        auditoriaService.gerarAuditoria(ciclo.getCiclo().longValue(), jsonDiaNoCicloAntesDeletar, jsonDiaNoCicloDepoisDeDeeletar, 60L, "ciclo", idUsuario);
    }

    private String rotinaParaAutitoria(Short ciclo) {
        return gerarDiasNoCicloParaAuditoria(ciclo);              
         
    }    

    private String gerarDiasNoCicloParaAuditoria(Short ciclo) {
        List<Short> listarCiclo = new ArrayList<>();
        listarCiclo.add(ciclo);
        List<Short> listaDeDiasNoCiclo = repository
                            .findByIdDiasVencimentoFaturamentoCicloIn(listarCiclo)
                            .stream()
                            .map(listaciclo -> listaciclo.getIdDiasVencimentoFaturamento().getDia())
                            .collect(Collectors.toList());

        StringBuilder linkarDiasEmString = new StringBuilder();

        linkarDiasEmString.append("{ \"DiaVencimento\":\"");

        for (int i=0; i < listaDeDiasNoCiclo.size(); i++) {

            linkarDiasEmString.append(listaDeDiasNoCiclo.get(i));
            
            if (listaDeDiasNoCiclo.size() > i+1) {
                linkarDiasEmString.append(", ");
            }
        }

        linkarDiasEmString.append("\"}");

        return linkarDiasEmString.toString();
    }
}
