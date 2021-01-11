/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesDoBanco;

import java.util.Date;

/**
 *
 * @author User
 */
public class Cliente extends Pessoa {

    private String codigo;
    private int depedentes;

    public Cliente() {
    }

    public Cliente(String codigo, int depedentes, int id, String cpf, String rg, String email, String telefone, String celular, String sexo, Date nascimento, String nome, Endereco idEndereco, Usuario usuario) {
        super(id, cpf, rg, email, telefone, celular, sexo, nascimento, nome, idEndereco, usuario);
        this.codigo = codigo;
        this.depedentes = depedentes;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getDepedentes() {
        return depedentes;
    }

    public void setDepedentes(int depedentes) {
        this.depedentes = depedentes;
    }
}
