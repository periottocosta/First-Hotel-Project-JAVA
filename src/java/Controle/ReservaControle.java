/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.ClienteDao;
import Dao.ClienteDaoImp;
import Dao.QuartoDao;
import Dao.QuartoDaoImp;
import Dao.ReservaDao;
import Dao.ReservaDaoImp;
import entidadesDoBanco.Cliente;
import entidadesDoBanco.EstatusQuarto;
import entidadesDoBanco.EstatusReserva;
import entidadesDoBanco.Quarto;
import entidadesDoBanco.Reserva;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
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
@WebServlet(name = "ReservaControle", urlPatterns = {"/ReservaControle"})
public class ReservaControle extends HttpServlet {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher rd;
    private Reserva reserva;
    private Quarto quarto;
    private EstatusQuarto eQuarto;
    private Cliente cliente;
    private EstatusReserva eRserva;
    private EstatusQuarto eq;

    protected void processRequest()
            throws ServletException, IOException {
        String cmd = request.getParameter("cmd");
        switch (cmd) {

            case "pesq":
                pesquisa();
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
            case "pesqCliente":
                pesqCliente();
                break;
            case "alterar":
                alterar();
                break;
            case "confAltera":
                confAltera();
                break;
        }
        rd.forward(request, response);
    }

    private void confAltera() {
        ReservaDao rDao = new ReservaDaoImp();

        try {
            String idQuarto = request.getParameter("quartoOrig");
            rDao.alterarReserva(carregaRserva2(), Integer.parseInt(idQuarto));
            rd = request.getRequestDispatcher("menu.jsp");
        } catch (Exception e) {
        }
    }

    private Reserva carregaRserva2() {
        reserva = new Reserva();
//        eQuarto = new EstatusQuarto();
        quarto = new Quarto();
//        eRserva = new EstatusReserva();
//        cliente = new Cliente();

        String quarto1 = request.getParameter("quarto");
        String id = request.getParameter("id");
        reserva.setId(Integer.parseInt(id));
        quarto.setId(Integer.parseInt(quarto1));
        reserva.setIdQuarto(quarto);

        String dataR = request.getParameter("dataR");
        try {
            Date data1 = new SimpleDateFormat("dd/MM/yyyy").parse(dataR);
            reserva.setDataReserva(data1);
        } catch (Exception e) {
        }
        String dataE = request.getParameter("dataE");
        try {
            Date data2 = new SimpleDateFormat("dd/MM/yyyy").parse(dataE);
            reserva.setDataEntrada(data2);
        } catch (Exception e) {
        }
        String dataS = request.getParameter("dataS");
        try {
            Date data3 = new SimpleDateFormat("dd/MM/yyyy").parse(dataS);
            reserva.setDataSaida(data3);
        } catch (Exception e) {
        }

        return reserva;

    }

    private void alterar() {
        ReservaDao rDao = new ReservaDaoImp();
        QuartoDao qDao = new QuartoDaoImp();
        String id = request.getParameter("id");
        String idQuarto = request.getParameter("idQuarto");
        try {
            List<Quarto> quartos = qDao.listar();
            reserva = (Reserva) rDao.procurar(Integer.parseInt(id));
            request.setAttribute("reserva", reserva);
            request.setAttribute("quartos", quartos);
            request.setAttribute("idQuarto", idQuarto);
            rd = request.getRequestDispatcher("atuaReserva.jsp");
        } catch (Exception e) {
        }
    }

    private void pesqCliente() {
        ClienteDao cDao = new ClienteDaoImp();
        try {
            List<Cliente> clientes = cDao.pesquisa(request.getParameter("nome"));
            request.setAttribute("clientes", clientes);
            rd = request.getRequestDispatcher("fazReserva.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
        }
    }

    private void salvar() {
        ReservaDao rDao = new ReservaDaoImp();
        JavaMailApp java = new JavaMailApp();
        try {
            String idQuarto = request.getParameter("quarto");
            rDao.salvarRserva(carregaRserva(), Integer.parseInt(idQuarto));
            String nome = request.getParameter("nome");
            String dataR = request.getParameter("dataR");
            String dataE = request.getParameter("dataE");
            String dataS = request.getParameter("dataS");
            String Nquarto = request.getParameter("");
            String email = request.getParameter("email");
            String mensg = "Olá " + nome + "\n\n Dados da Reserva \n\n Data da reserva :" + dataR
                    + "\n Data Entrada :" + dataE + "\n Data saída :" + dataS;
            java.Email(email, mensg);
            rd = request.getRequestDispatcher("pesqCliente.jsp");

        } catch (Exception e) {
        }
    }

    private Reserva carregaRserva() {
        reserva = new Reserva();
        eQuarto = new EstatusQuarto();
        quarto = new Quarto();
        eRserva = new EstatusReserva();
        cliente = new Cliente();
        String id = request.getParameter("id");
        if (id != null) {
            reserva.setId(Integer.parseInt(id));
        }
        cliente.setId(Integer.parseInt(request.getParameter("idCliente")));
        reserva.setIdCliente(cliente);
        quarto.setId(Integer.parseInt(request.getParameter("quarto")));
        reserva.setIdQuarto(quarto);
        eRserva.setId(2);
        reserva.setIdEstatusReser(eRserva);
        String dataR = request.getParameter("dataR");
        try {
            Date data1 = new SimpleDateFormat("dd/MM/yyyy").parse(dataR);
            reserva.setDataReserva(data1);
        } catch (Exception e) {
        }
        String dataE = request.getParameter("dataE");
        try {
            Date data2 = new SimpleDateFormat("dd/MM/yyyy").parse(dataE);
            reserva.setDataEntrada(data2);
        } catch (Exception e) {
        }
        String dataS = request.getParameter("dataS");
        try {
            Date data3 = new SimpleDateFormat("dd/MM/yyyy").parse(dataS);
            reserva.setDataSaida(data3);
        } catch (Exception e) {
        }


        return reserva;

    }

    private void pesquisa() {
        ReservaDao rDao = new ReservaDaoImp();

        try {
            List<Reserva> reservas = rDao.pesquisa(request.getParameter("nome"));
            request.setAttribute("reservas", reservas);
            rd = request.getRequestDispatcher("pesqReserva.jsp");
        } catch (Exception e) {
        }
    }

    private void comboQuarto() {
        QuartoDao qDao = new QuartoDaoImp();

        try {
            List quartos = qDao.listar();
            request.setAttribute("quartos", quartos);
            String nome = request.getParameter("nome");
            String cpf = request.getParameter("cpf");
            String idCliente = request.getParameter("idCliente");
            String email = request.getParameter("email");
            Date data = new Date(System.currentTimeMillis());
            SimpleDateFormat formatarDate = new SimpleDateFormat("dd-MM-yyyy");

            request.setAttribute("data", formatarDate.format(data));
            request.setAttribute("email", email);
            request.setAttribute("idCliente", idCliente);
            request.setAttribute("nome", nome);
            request.setAttribute("cpf", cpf);
            rd = request.getRequestDispatcher("reserva.jsp");
        } catch (Exception ex) {
            Logger.getLogger(ReservaControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void excluir() {
        ReservaDao rDao = new ReservaDaoImp();
        String id = request.getParameter("id");
        String idQuarto = request.getParameter("idQuarto");

        try {
            rDao.excluirRserva(Integer.parseInt(id), Integer.parseInt(idQuarto));
            rd = request.getRequestDispatcher("menu.jsp");
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
