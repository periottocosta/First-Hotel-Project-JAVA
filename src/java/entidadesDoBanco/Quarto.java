/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesDoBanco;

/**
 *
 * @author User
 */
public class Quarto {
    private int id;
    private String numero;
    private String capacidade;
    private String descricao;
    private EstatusQuarto idEstatusQuarto;

    public Quarto() {
    }

    public Quarto(int id, String numero, String capacidade, String descricao, EstatusQuarto idEstatusQuarto) {
        this.id = id;
        this.numero = numero;
        this.capacidade = capacidade;
        this.descricao = descricao;
        this.idEstatusQuarto = idEstatusQuarto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(String capacidade) {
        this.capacidade = capacidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public EstatusQuarto getIdEstatusQuarto() {
        return idEstatusQuarto;
    }

    public void setIdEstatusQuarto(EstatusQuarto idEstatusQuarto) {
        this.idEstatusQuarto = idEstatusQuarto;
    }
    
    
}
