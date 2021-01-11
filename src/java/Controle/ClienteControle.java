package Controle;

import Dao.ClienteDao;
import Dao.ClienteDaoImp;
import Dao.PessoaDao;
import Dao.PessoaDaoImp;
import entidadesDoBanco.Cliente;
import entidadesDoBanco.Endereco;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ClienteControle", urlPatterns = {"/ClienteControle"})
public class ClienteControle extends HttpServlet {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher rd;
    private Endereco endereco = null;
    private Cliente cliente = null;

    protected void processRequest()
            throws ServletException, IOException {
        String cmd = request.getParameter("cmd");
        switch (cmd) {

            case "salvar":
                salvar();
                break;
            case "atualizar":
                atualizar();
                break;
            case "excluir":
                excluir();
                break;
            case "pesqCliente":
                pesqCliente();
                break;
            case "atuaCliente":
                atuaCliente();
                break;
        }
    }

    private Cliente carregaCliente2() {
        cliente = new Cliente();
        endereco = new Endereco();

        String id = request.getParameter("id");
        String idEndereco = request.getParameter("idEndereco");
        String sexo = request.getParameter("sexo");
        if (id != null) {
            cliente.setId(Integer.parseInt(id));
        }
        if (idEndereco != null) {
            endereco.setId(Integer.parseInt(idEndereco));
        }
        if (sexo == null) {
            cliente.setSexo(request.getParameter("sexo1"));
        } else {
            cliente.setSexo(request.getParameter("sexo"));
        }
        cliente.setNome(request.getParameter("nome"));
        cliente.setCpf(request.getParameter("cpf"));
        cliente.setRg(request.getParameter("rg"));
        // cliente.setSexo(request.getParameter("sexo"));
        cliente.setTelefone(request.getParameter("telefone"));
        cliente.setCelular(request.getParameter("celular"));
        cliente.setEmail(request.getParameter("email"));
        cliente.setCodigo(request.getParameter("codigo"));
        cliente.setDepedentes(Integer.parseInt(request.getParameter("depedencia")));
        String datanascimento = request.getParameter("nascimento");

        try {
            Date data = new SimpleDateFormat("dd/MM/yyyy").parse(datanascimento);
            cliente.setNascimento(data);
        } catch (Exception e) {
        }
        endereco.setBairro(request.getParameter("bairro"));
        endereco.setCep(request.getParameter("cep"));
        endereco.setCidade(request.getParameter("cidade"));
        endereco.setEstado(request.getParameter("estado"));
        endereco.setPais(request.getParameter("pais"));
        endereco.setRua(request.getParameter("rua"));
        endereco.setSigla(request.getParameter("sigla"));
        cliente.setIdEndereco(endereco);
        return cliente;
    }

    private void atualizar() {
        ClienteDao cDao = new ClienteDaoImp();
        try {
            cDao.alterar(carregaCliente2());
            rd = request.getRequestDispatcher("menu.jsp");
            request.setAttribute("msg", "Cliente alterado com sucesso!!!!");
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void atuaCliente() {
        ClienteDao cDao = new ClienteDaoImp();

        String idForm = request.getParameter("id");
        try {
            cliente = (Cliente) cDao.procurar(Integer.parseInt(idForm));
            request.setAttribute("cliente", cliente);
            rd = request.getRequestDispatcher("atuaCliente.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
        }
    }

    private void excluir() {
        PessoaDao pDao = new PessoaDaoImp();

        String idCmd = request.getParameter("id");
        try {
            pDao.excluir(Integer.parseInt(idCmd));
            pesqCliente();
        } catch (Exception e) {
        }
    }

    private void pesqCliente() {
        ClienteDao cDao = new ClienteDaoImp();
        try {
            List<Cliente> clientes = cDao.pesquisa(request.getParameter("nome"));
            if (clientes.isEmpty()) {
                request.setAttribute("alerta", "ATENÇÃO!!  Cliente não encontrado");
            }
            request.setAttribute("clientes", clientes);
            rd = request.getRequestDispatcher("pesqCliente.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
        }
    }

    private void salvar() {
        ClienteDao pDao = new ClienteDaoImp();
        try {
            pDao.salvar(carregaCliente());
            rd = request.getRequestDispatcher("menu.jsp");
            request.setAttribute("msg", "Cliente salvo com sucesso!!!!");
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(FuncionarioControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Cliente carregaCliente() {
        cliente = new Cliente();
        endereco = new Endereco();

        String id = request.getParameter("id");

        if (id != null) {
            cliente.setId(Integer.parseInt(id));
        }
        cliente.setNome(request.getParameter("nome"));
        cliente.setCpf(request.getParameter("cpf"));
        cliente.setRg(request.getParameter("rg"));
        cliente.setSexo(request.getParameter("sexo"));
        cliente.setTelefone(request.getParameter("telefone"));
        cliente.setCelular(request.getParameter("celular"));
        cliente.setEmail(request.getParameter("email"));
        cliente.setCodigo(request.getParameter("codigo"));
        cliente.setDepedentes(Integer.parseInt(request.getParameter("depedencia")));
        String datanascimento = request.getParameter("nascimento");

        try {
            Date data = new SimpleDateFormat("dd/MM/yyyy").parse(datanascimento);
            cliente.setNascimento(data);
        } catch (Exception e) {
        }
        endereco.setBairro(request.getParameter("bairro"));
        endereco.setCep(request.getParameter("cep"));
        endereco.setCidade(request.getParameter("cidade"));
        endereco.setEstado(request.getParameter("estado"));
        endereco.setPais(request.getParameter("pais"));
        endereco.setRua(request.getParameter("rua"));
        endereco.setSigla(request.getParameter("sigla"));
        cliente.setIdEndereco(endereco);
        return cliente;
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
