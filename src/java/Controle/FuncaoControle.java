/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.FuncaoDao;
import Dao.FuncaoDaoImp;
import entidadesDoBanco.Funcao;
import entidadesDoBanco.Funcionario;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(name = "FuncaoControle", urlPatterns = {"/FuncaoControle"})
public class FuncaoControle extends HttpServlet {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher rd;
    private Funcao funcao;

    protected void processRequest()
            throws ServletException, IOException {
        String cmd = request.getParameter("cmd");
        switch (cmd) {

            case "salvar":
                salvar();
                break;
//            case "listar":
//                listar();
            case "comboFuncao":
                comboFuncao();
                break;
            case "pesqFuncao":
                pesqFuncao();
                break;
        }
        rd.forward(request, response);
    }
    private void pesqFuncao(){
     FuncaoDao fDao = new FuncaoDaoImp();
        try {
            List<Funcionario> funcionarios = fDao.pesquisa(Integer.parseInt(request.getParameter("funcao")));
               request.setAttribute("funcionarios", funcionarios);
            if(funcionarios == null){
               
               request.setAttribute("mens","Não existe funcionario nessa função");
            }
          
            rd = request.getRequestDispatcher("FuncaoControle?cmd=comboFuncao");
        } catch (Exception e) {
        }
    }
    private void comboFuncao() {
        FuncaoDao fDao = new FuncaoDaoImp();

        try {
            List<Funcao> funcoes = fDao.listar();
            request.setAttribute("funcoes", funcoes);
            rd = request.getRequestDispatcher("pesqFuncao.jsp");
        } catch (Exception e) {
        }
    }

    private void salvar() {
        FuncaoDao fDao = new FuncaoDaoImp();
        try {
            fDao.salvar(carregaFuncao());
            rd = request.getRequestDispatcher("cadFuncao.jsp");

        } catch (Exception e) {
        }


    }

    private Funcao carregaFuncao() {
        funcao = new Funcao();

        String id = request.getParameter("id");

        if (id != null) {
            funcao.setId(Integer.parseInt(id));
        }
        funcao.setNome(request.getParameter("nome"));
        funcao.setDescricao(request.getParameter("descricao"));
        return funcao;
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
