/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.QuartoDao;
import Dao.QuartoDaoImp;
import entidadesDoBanco.EstatusQuarto;
import entidadesDoBanco.Quarto;
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
@WebServlet(name = "QuartoControle", urlPatterns = {"/QuartoControle"})
public class QuartoControle extends HttpServlet {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher rd;
    private Quarto quarto;
    private EstatusQuarto eq;

    protected void processRequest()
            throws ServletException, IOException {
        String cmd = request.getParameter("cmd");
        switch (cmd) {

            case "pesqQuarto":
//                pesquisa();
                break;
            case "comboQuarto":
                comboQuarto();
                break;
            case "salvar":
                salvar();
                break;
            case "excluir":
                excluir();
                break;
        }

    }

    private void excluir() {
    }

//    private void pesquisa() {
//        QuartoDao qDao = new QuartoDaoImp();
//        try {
//            List<Quarto> quantos = qDao.procurar();
//        } catch (Exception e) {
//        }
//    }

    private void salvar() {
        QuartoDao qDao = new QuartoDaoImp();
        try {
            qDao.salvar(carregaQuarto());
            rd = request.getRequestDispatcher("menu.jsp");
            request.setAttribute("msg", "Quarto salvo com sucesso!!!!");
            rd.forward(request, response);
        } catch (Exception e) {
        }
    }

    private Quarto carregaQuarto() {

        quarto = new Quarto();
        eq = new EstatusQuarto();
        String id = request.getParameter("id");
        if (id != null) {
            quarto.setId(Integer.parseInt(id));
        }
        quarto.setNumero(request.getParameter("numero"));
        quarto.setCapacidade(request.getParameter("capacidade"));
        quarto.setDescricao(request.getParameter("descricao"));
        eq.setId(2);
        quarto.setIdEstatusQuarto(eq);
        return quarto;

    }

    private void comboQuarto() {
          QuartoDao qDao = new QuartoDaoImp();
        try {
            List quartos = qDao.listarQuarto();
            request.setAttribute("quartos", quartos);
            rd = request.getRequestDispatcher("pesqQuarto.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
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
