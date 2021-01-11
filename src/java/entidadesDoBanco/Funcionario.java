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
public class Funcionario extends Pessoa {

    private String cracha;
    private double salario;
    private Turno idTurno;
    private Funcao idFuncao;

    public Funcionario() {
    }

    public Funcionario(String cracha, double salario, Turno idTurno, Funcao idFuncao, int id, String cpf, String rg, String email, String telefone, String celular, String sexo, Date nascimento, String nome, Endereco idEndereco, Usuario usuario) {
        super(id, cpf, rg, email, telefone, celular, sexo, nascimento, nome, idEndereco, usuario);
        this.cracha = cracha;
        this.salario = salario;
        this.idTurno = idTurno;
        this.idFuncao = idFuncao;
    }

    public String getCracha() {
        return cracha;
    }

    public void setCracha(String cracha) {
        this.cracha = cracha;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Turno getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(Turno idTurno) {
        this.idTurno = idTurno;
    }

    public Funcao getIdFuncao() {
        return idFuncao;
    }

    public void setIdFuncao(Funcao idFuncao) {
        this.idFuncao = idFuncao;
    }
}
