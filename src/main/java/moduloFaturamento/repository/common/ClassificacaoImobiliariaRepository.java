package moduloFaturamento.repository.common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloFaturamento.model.common.ClassificacaoImobiliaria;

@Repository
public interface ClassificacaoImobiliariaRepository extends JpaRepository<ClassificacaoImobiliaria, Integer> {

    List<ClassificacaoImobiliaria> findByImovel_MatriculaImovelAndAndDataHoraFimIsNull(Integer matriculaImovel);
}
