/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesDoBanco;

import java.util.Date;

/**
 *
 * @author Aluno
 */
public class Usuario {
  
    private int id;
    private String login;
    private String  senha;
    private Date ultimoAcesso;
    private Perfil idPerfil;
    private boolean logado;
    public Usuario() {
    }

    public Usuario(int id, String login, String senha, Date ultimoAcesso, Perfil idPerfil, boolean logado) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.ultimoAcesso = ultimoAcesso;
        this.idPerfil = idPerfil;
        this.logado = logado;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(Date ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }

    public Perfil getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Perfil idPerfil) {
        this.idPerfil = idPerfil;
    }
    
    
}
