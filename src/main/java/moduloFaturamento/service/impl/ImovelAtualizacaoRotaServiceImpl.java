package moduloFaturamento.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloFaturamento.dto.imovelAtualizacaoRota.SiscomGisRotaProjection;
import moduloFaturamento.repository.ImovelAtualizacaoRotaRepository;
import moduloFaturamento.service.ImovelAtualizacaoRotaService;

@Service
public class ImovelAtualizacaoRotaServiceImpl implements ImovelAtualizacaoRotaService {

	@Autowired
	private ImovelAtualizacaoRotaRepository imovelAtualizacaoRotaRepository;

	@Override
	public void executarAtualizacaoRota(Integer cdCidade, Integer ciclo) {

		List<SiscomGisRotaProjection> listProjection = imovelAtualizacaoRotaRepository.buscarAtualizacaoSiscomGisRotaProjection(cdCidade, ciclo);

		List<SiscomGisRotaProjection> gisNaoTemMatricula = listProjection.stream().filter(proj -> proj.getGisMatricula() == null).collect(Collectors.toList());
		List<SiscomGisRotaProjection> siscomNaoTemMatricula = listProjection.stream().filter(proj -> proj.getSiscomMatricula() == null)
				.collect(Collectors.toList());

		List<SiscomGisRotaProjection> localidadeDivergente = listProjection.stream().filter(proj -> {

			if (proj.getSiscomMatricula() == null || proj.getGisMatricula() == null) {

				return false;
			}
			
			return proj.getSiscomCdCidade() != proj.getGisCdCidade();
		}).collect(Collectors.toList());

		List<SiscomGisRotaProjection> cicloDivergente = listProjection.stream().filter(proj -> {

			if (proj.getSiscomMatricula() == null || proj.getGisMatricula() == null) {

				return false;
			}
			
			return proj.getSiscomCiclo() != proj.getGisCiclo();
		}).collect(Collectors.toList());
		
		
		
		listProjection = null;

		System.gc();

		System.out.println();
	}
}
