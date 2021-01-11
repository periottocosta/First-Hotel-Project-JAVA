/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.TurnoDao;
import Dao.TurnoDaoImp;
import entidadesDoBanco.Funcionario;
import entidadesDoBanco.Turno;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "TurnoControle", urlPatterns = {"/TurnoControle"})
public class TurnoControle extends HttpServlet {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher rd;
    private Turno turno = null;

    protected void processRequest()
            throws ServletException, IOException {
        String cmd = request.getParameter("cmd");
        switch (cmd) {

            case "salvar":
                salvar();
                break;
            case "comboTurno":
                comboTurno();
                break;
            case "pesqTurno":
                pesqTurno();
                break;
            case "excluir":
                excluir();
                break;

        }
        rd.forward(request, response);
    }
   private void excluir(){
      TurnoDao tDao = new TurnoDaoImp();
      String id = request.getParameter("id");
      String idTurno = request.getParameter("idTurno");
        String mens = null;
       try {
           tDao.excluirTruno(Integer.parseInt(id), Integer.parseInt(idTurno),  mens);
           
           rd = request.getRequestDispatcher("TurnoControle?cmd=comboTurno");
              request.setAttribute("mens", mens);
       } catch (Exception e) {
       }
   }
    private void comboTurno() {

        TurnoDao tDao = new TurnoDaoImp();
        try {
           
            List<Turno> turnos = tDao.listar();
            request.setAttribute("turnos", turnos);
            rd = request.getRequestDispatcher("pesqTurno.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(FuncionarioControle.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void  pesqTurno(){
        TurnoDao tDao = new TurnoDaoImp();
        try {
            List<Funcionario> turnos = tDao.pesquisa(Integer.parseInt(request.getParameter("turno")));
            request.setAttribute("funcionarios", turnos);
            rd = request.getRequestDispatcher("TurnoControle?cmd=comboTurno");
        } catch (Exception ex) {
            Logger.getLogger(TurnoControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void salvar() {
        TurnoDao tDao = new TurnoDaoImp();

        try {
            tDao.salvar(carregaTurno());
            rd = request.getRequestDispatcher("cadTurno.jsp");
        } catch (Exception e) {
        }
    }

    private Turno carregaTurno() {
        turno = new Turno();

        String id = request.getParameter("id");
        if (id != null) {
            turno.setId(Integer.parseInt(id));
        }
        turno.setPerfio(request.getParameter("nome"));
        turno.setDescricao(request.getParameter("descricao"));
        return turno;

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
