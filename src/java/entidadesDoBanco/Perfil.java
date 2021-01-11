/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesDoBanco;

/**
 *
 * @author Aluno
 */
public class Perfil {
    
    private int id;
    private String tipo;
    private String descricao;

    public Perfil() {
    }

    public Perfil(int id, String tipo, String descricao) {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
