package moduloFaturamento.repository;

import moduloFaturamento.model.IncentivoClienteSituacaoAgua;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncentivoClienteSituacaoAguaRepository extends JpaRepository<IncentivoClienteSituacaoAgua, Integer> {

    List<IncentivoClienteSituacaoAgua> findByIncentivoClienteDetalhe_Id(Integer idIncentivoClienteDetalhe);
}
