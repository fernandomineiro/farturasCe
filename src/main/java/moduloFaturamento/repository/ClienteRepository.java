package moduloFaturamento.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import moduloFaturamento.dto.faturaAvulsa.projection.FaturaAvulsaEnderecoClienteProjectionDTO;
import moduloFaturamento.model.common.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query(nativeQuery = true, value = "SELECT DV FROM CDACL WHERE CD_CLIENTE = :cdCliente")
    Short buscarDVDoCliente(Integer cdCliente);

    @Query(nativeQuery = true, value = "SELECT DV AS dvCliente,"
            + " NOME AS nomeCliente,"
            + " LOGRADOURO_CORRESP AS rua,"
            + " NUM_ENDERECO AS numeroEndereco,"
            + " COMPL_ENDERECO AS complementoEndereco,"
            + " BAIRRO_CORRESP AS bairro,"
            + " LOCALIDADE_CORRESP AS localidade,"
            + " ESTADO_CORRESP AS estado"
            + " FROM CDACL WHERE CD_CLIENTE = :cdCliente")
    FaturaAvulsaEnderecoClienteProjectionDTO buscarEnderecoCliente(Integer cdCliente);

    @Query(nativeQuery = true, value = "SELECT CASE WHEN EXISTS (SELECT TOP 1 *"
            + " FROM CDACL"
            + " WHERE RETER_IMPOSTOS = 'S' AND CD_CLIENTE = :cdCliente )"
            + " THEN CAST (1 AS BIT)"
            + " ELSE CAST (0 AS BIT) END")
    boolean verificarSeClienteTemRetecaoImposto(Integer cdCliente);

    Optional<Cliente> findByCdCliente(Integer cdCliente);
        
    Optional<List<Cliente>> findByCpfCnpj(String cpfOuCnpj);

    @Query(nativeQuery = true, value = "SELECT CD_CLIENTE FROM CDACL WHERE CPF_CNPJ = :cpfOuCnpj")
    Integer buscarCdClientePorCpfOuCnpj(String cpfOuCnpj);

    Optional<Cliente> findByCdClienteAndDvCliente(Integer cdCliente, Short dvCliente);

    @Query(nativeQuery = true, value = "SELECT EMAIL FROM CDACL WHERE CD_CLIENTE = :cdCliente AND EMAIL <> ' '")
    List<String> buscarEmailDoCliente(Integer cdCliente);

    @Query(nativeQuery = true, value = "SELECT NOME FROM CDACL WHERE  CD_CLIENTE = :cdCliente")
    String buscarNomeCliente(Integer cdCliente);

}
