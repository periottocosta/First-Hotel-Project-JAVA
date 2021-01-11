/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.PerfilDao;
import Dao.PerfilDaoImp;
import entidadesDoBanco.Funcionario;
import entidadesDoBanco.Perfil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aluno
 */
@WebServlet(name = "PerfilControle", urlPatterns = {"/PerfilControle"})
public class PerfilControle extends HttpServlet {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher rd;
    private Perfil perfil;
    private PerfilDaoImp PerfilDao;

    protected void processRequest()
            throws ServletException, IOException {
        String cmd = request.getParameter("cmd");
        switch (cmd) {

            case "salvar":
                salvar();
                break;
            case "comboPerfil":
                listar();
                break;
//            case "excluir":
//                excluir();
//                break;
//            case "alterar":
//                alterar();
//                break;
        }
        rd.forward(request, response);

    }
   private void listar(){
     PerfilDao pDao = new PerfilDaoImp();
       try {
           List<Funcionario> perfis = pDao.listarPesq();
           request.setAttribute("perfis", perfis);
           rd = request.getRequestDispatcher("pesqPerfil.jsp");
       } catch (Exception e) {
       }
   }
    private void salvar() {
        PerfilDao pDao = new PerfilDaoImp();
        try {
            pDao.salvar(carregaPerfil());
            rd = request.getRequestDispatcher("cadPerfil.jsp");

        } catch (Exception e) {
        }
    }

    private Perfil carregaPerfil() {
        perfil = new Perfil();
        String id = request.getParameter("id");
        
        if(id != null){
          perfil.setId(Integer.parseInt(id));
        }
        perfil.setTipo(request.getParameter("perfil"));
        perfil.setDescricao(request.getParameter("descricao"));
        return perfil;

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
