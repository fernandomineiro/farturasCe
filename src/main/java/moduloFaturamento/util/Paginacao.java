package moduloFaturamento.util;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;

import moduloFaturamento.comparator.AbstractDTOComparator;
import moduloFaturamento.dto.GenericoWrapperDTO;

public class Paginacao {
	
	private Paginacao() {
	}
	
	public static <T> GenericoWrapperDTO<List<T>> paginarCampoUnico (
			AbstractDTOComparator<T> comparator, Pageable pageable, List<T> listaDto) {

		GenericoWrapperDTO<List<T>> lista = new GenericoWrapperDTO<>();

		lista.setDados(listaDto);
		lista.setTotalRegistro(listaDto.size());

		if (comparator != null && pageable != null && pageable.getSort() != null && pageable.getSort().iterator().hasNext()) {
			Order order = pageable.getSort().iterator().next();
			String campo = order.getProperty();
			String ordem = (order.isAscending() ? "asc" : "desc");
			comparator.setCampo(campo);
			comparator.setOrdem(ordem);
			Collections.sort(lista.getDados(), comparator);
		}

		int indice = pageable.getPageSize() * pageable.getPageNumber();
		
		lista.setDados(
				lista.getDados().stream().skip(indice).limit(pageable.getPageSize()).collect(Collectors.toList()));

		return lista;
	}
}
