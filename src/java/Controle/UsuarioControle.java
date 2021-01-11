package Controle;

import Dao.PerfilDao;
import Dao.PerfilDaoImp;
import Dao.UsuarioDao;
import Dao.UsuarioDaoImp;
import entidadesDoBanco.Funcionario;
import entidadesDoBanco.Perfil;
import entidadesDoBanco.Usuario;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UsuarioControle", urlPatterns = {"/UsuarioControle"})
public class UsuarioControle extends HttpServlet {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher rd;
    private UsuarioDao usuarioDao;
    private JavaMailApp email;

    protected void processRequest()
            throws ServletException, IOException {
        String cmd = request.getParameter("cmd");
        switch (cmd) {

            case "pesq":
                pesquisa();
                break;
            case "novoUsuario":
                criarUsuario();
                break;
            case "login":
                login();
                break;
            case "alterar":
                alterar();
                break;
            case "comboPerfil":
                comboPerfil();
                break;
            case "salvar":
                salvar();
                break;
            case "excluir":
                excluir();
                break;
            case "sair":
                sair();
                break;
        }
        rd.forward(request, response);
    }

    private void sair() {
        HttpSession session = request.getSession(true);
        session.setAttribute("funcionario", null);
          request.setAttribute("mens", "Logout com sucesso");
        rd = request.getRequestDispatcher("login.jsp");

    }

    private void alterar() {
        UsuarioDao uDao = new UsuarioDaoImp();
        Usuario usuario = new Usuario();
        Funcionario funcionario = new Funcionario();
        funcionario.setUsuario(carregaUsuario2());
        /////E-MAIL
        String senhaGerada = request.getParameter("senha");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String mensg = "Dados da alteraçao do usuario \n Senha :" + senhaGerada + "\n Login :" + login + "\n Serem mostrados so os dados alterados";
        JavaMailApp email2 = new JavaMailApp();
        email2.Email(email, mensg);
        /////
        try {
            uDao.alterar(funcionario);
            rd = request.getRequestDispatcher("menu.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(FuncionarioControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Usuario carregaUsuario2() {
        Usuario usuario = new Usuario();
        String senha = request.getParameter("senha");
        String login = request.getParameter("login");
        String idUsuario = request.getParameter("id");
        if ("".equals(senha)) {
            usuario.setSenha(request.getParameter("senha1"));
        } else {
            String senhaGerada = request.getParameter("senha");
            String senhaCript = senha(senhaGerada);
            usuario.setSenha(senhaCript);
        }
        if ("".equals(login)) {
            usuario.setLogin(request.getParameter("login1"));
        } else {
            usuario.setLogin(request.getParameter("login"));
        }
        if (idUsuario != null) {
            usuario.setId(Integer.parseInt(idUsuario));
        }

        return usuario;
    }

    private void excluir() {
        UsuarioDao uDao = new UsuarioDaoImp();
        String id = request.getParameter("id");
        String idUsuario = request.getParameter("idUsuario");
        try {
            uDao.excluirUsuario(Integer.parseInt(id), Integer.parseInt(idUsuario));
            rd = request.getRequestDispatcher("pesqUsuario.jsp");
        } catch (Exception ex) {
            Logger.getLogger(UsuarioControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void salvar() {
        usuarioDao = new UsuarioDaoImp();
        Funcionario funcionario = new Funcionario();
        funcionario.setUsuario(carregaUsuario());
        funcionario.setId(Integer.parseInt(request.getParameter("id")));

        String senhaGerada = request.getParameter("senha");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String mensg = "Olá " + nome + "\n\n Segue seus dados de usuários do sistema Hotel Flipper "
                + "\n\n Senha :" + senhaGerada + "\n Login :" + login
                + "\n\n Atenciosamente Adiministrador Hotel Flipper \n\nNão responder a esse e-mail";
        JavaMailApp email2 = new JavaMailApp();
        email2.Email(email, mensg);

        String senhaCript = senha(senhaGerada);
        funcionario.getUsuario().setSenha(senhaCript);
        try {
            usuarioDao.salvar(funcionario);
            rd = request.getRequestDispatcher("menu.jsp");
            request.setAttribute("msg", "Usuario salvo com sucesso!!!!");
        } catch (Exception e) {
        }
    }

    private String senha(String senhaGerada) {
//    String senha = "";
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioControle.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        m.update(senhaGerada.getBytes(), 0, senhaGerada.length());
        senhaGerada = new BigInteger(1, m.digest()).toString(16);
        return senhaGerada;
    }

    public String geraSenha(String senha) {
        String[] carct = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
//
        //"a", "b", "c", "d", "e", "h", "i", "j", "k", "l", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"

        for (int x = 0; x < 5; x++) {
            int j = (int) (Math.random() * carct.length);
            senha += carct[j];
        }

        //        System.out.println("A SENHA GERADA É: " + senha);
        return senha;
    }

    private Usuario carregaUsuario() {
        Usuario usuario = new Usuario();
        Perfil perfil = new Perfil();
        String senha = request.getParameter("senha");
        String idUsuario = request.getParameter("id");
        if (senha != null) {
            usuario.setSenha(senha);
        }
        if (idUsuario != null) {
            usuario.setId(Integer.parseInt(idUsuario));
        }

        usuario.setSenha(request.getParameter("senha"));
        usuario.setLogin(request.getParameter("login"));
        perfil.setId(Integer.parseInt(request.getParameter("idPerfil")));
        usuario.setIdPerfil(perfil);

        return usuario;
    }

    private void login() {
        usuarioDao = new UsuarioDaoImp();

        try {
            String login = request.getParameter("login");
            String senhaGerada = request.getParameter("senha");
            String senha = senha(senhaGerada);
            //     senha(senha);
            Funcionario funcionario = usuarioDao.logar(login, senha);
            if (funcionario.getUsuario().isLogado()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("funcionario", funcionario);
                rd = request.getRequestDispatcher("menu.jsp");

            } else {
                request.setAttribute("alerta", "Login ou senha incorretos!!! ");
                rd = request.getRequestDispatcher("login.jsp");
            }
        } catch (Exception e) {
        }

    }

    private void comboPerfil() {
        PerfilDao perfilDao = new PerfilDaoImp();

        try {
            List perfis = perfilDao.listar();
            request.setAttribute("perfis", perfis);
            int id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");

            String senha = geraSenha("");
            request.setAttribute("id", id);
            request.setAttribute("nome", nome);
            request.setAttribute("email", email);
            request.setAttribute("senha", senha);
            rd = request.getRequestDispatcher("cadUsuario.jsp");
        } catch (Exception e) {
        }
    }

    private void criarUsuario() {
        usuarioDao = new UsuarioDaoImp();

        try {
            List<Funcionario> funcionarios = usuarioDao.usuarioN(request.getParameter("nome"));
            if (funcionarios.isEmpty()){
                request.setAttribute("alerta", "ATENÇÃO!!  Usuário não encontrado");
            }
            request.setAttribute("funcionarios", funcionarios);
            rd = request.getRequestDispatcher("novoUsuario.jsp");
        } catch (Exception e) {
        }

    }

    private void pesquisa() {
        usuarioDao = new UsuarioDaoImp();
        try {
            List<Funcionario> funcionarios = usuarioDao.pesquisa(request.getParameter("nome"));
            if(funcionarios.isEmpty()){
                request.setAttribute("alerta", "ATENÇÃO!!   Usuário não encontrado");
            }
            request.setAttribute("funcionarios", funcionarios);
            rd = request.getRequestDispatcher("pesqUsuario.jsp");
            rd.forward(request, response);


        } catch (Exception ex) {
            Logger.getLogger(UsuarioControle.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.request = request;
        this.response = response;
        processRequest();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.request = request;
        this.response = response;
        processRequest();
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
