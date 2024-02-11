package moduloFaturamento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CDTGC")
public class GrupoDeConsumo {

	@Id
	@Column(name = "GRUPO_CONSUMO")
	private Short grupoDeConsumo;
	@Column(name = "BASICO")
	private Integer basico;
	@Column(name = "CATEGORIA")
	private Short categoria;
	@Column(name = "DC_GRUPO_CONSUMO")
	private String dcGrupoDeConsumo;
	@Column(name = "MAINT")
	private String maint;
	@Column(name = "MINIMO")
	private Integer minimo;
	@Column(name = "PERM_SEL_CAD_NOVA_LIGACAO")
	private String permiteSelecaoCadastroNovaLigacao;
	@Column(name = "PERM_SEL_FILTRO_PESQUISA")
	private String permiteSelecaoFiltroPesquisa;

	public Short getGrupoDeConsumo() {

		return grupoDeConsumo;
	}

	public void setGrupoDeConsumo(Short grupoDeConsumo) {

		this.grupoDeConsumo = grupoDeConsumo;
	}

	public Integer getBasico() {

		return basico;
	}

	public void setBasico(Integer basico) {

		this.basico = basico;
	}

	public Short getCategoria() {

		return categoria;
	}

	public void setCategoria(Short categoria) {

		this.categoria = categoria;
	}

	public String getDcGrupoDeConsumo() {

		return dcGrupoDeConsumo;
	}

	public void setDcGrupoDeConsumo(String dcGrupoDeConsumo) {

		this.dcGrupoDeConsumo = dcGrupoDeConsumo;
	}

	public String getMaint() {

		return maint;
	}

	public void setMaint(String maint) {

		this.maint = maint;
	}

	public Integer getMinimo() {

		return minimo;
	}

	public void setMinimo(Integer minimo) {

		this.minimo = minimo;
	}

	public String getPermiteSelecaoCadastroNovaLigacao() {

		return permiteSelecaoCadastroNovaLigacao;
	}

	public void setPermiteSelecaoCadastroNovaLigacao(String permiteSelecaoCadastroNovaLigacao) {

		this.permiteSelecaoCadastroNovaLigacao = permiteSelecaoCadastroNovaLigacao;
	}

	public String getPermiteSelecaoFiltroPesquisa() {

		return permiteSelecaoFiltroPesquisa;
	}

	public void setPermiteSelecaoFiltroPesquisa(String permiteSelecaoFiltroPesquisa) {

		this.permiteSelecaoFiltroPesquisa = permiteSelecaoFiltroPesquisa;
	}

}
