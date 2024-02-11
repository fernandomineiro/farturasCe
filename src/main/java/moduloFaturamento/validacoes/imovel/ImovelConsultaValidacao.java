package moduloFaturamento.validacoes.imovel;

import org.springframework.stereotype.Service;

@Service
public class ImovelConsultaValidacao extends ImovelValidacao{

    public void validarMatriculaImovel(Integer matriculaImovel, Short dv){
        super.gerarExcecaoMatriculaImovelInexistente(matriculaImovel, dv);
    }

    public void validarMatriculaImovel(Integer matriculaImovel){
        super.gerarExcecaoMatriculaImovelInexistente(matriculaImovel);
    }
}
