package moduloFaturamento.dto.bairroCiclo;

public class CicloBairroRespostaDTO {

    private Short id;

    private String nome;

    private Short ciclo;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Short getCiclo() {
        return ciclo;
    }

    public void setCiclo(Short ciclo) {
        this.ciclo = ciclo;
    }

    public CicloBairroRespostaDTO(Short id, String nome, Short ciclo) {
        this.id = id;
        this.nome = nome;
        this.ciclo = ciclo;
    }

    
    
}
