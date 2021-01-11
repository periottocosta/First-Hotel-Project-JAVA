package Controle;

import Dao.FuncaoDao;
import Dao.FuncaoDaoImp;
import Dao.FuncionarioDao;
import Dao.FuncionarioDaoImp;
import Dao.PessoaDao;
import Dao.PessoaDaoImp;
import Dao.TurnoDao;
import Dao.TurnoDaoImp;
import entidadesDoBanco.Endereco;
import entidadesDoBanco.Funcao;
import entidadesDoBanco.Funcionario;
import entidadesDoBanco.Turno;
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

/**
 *
 * @author User
 */
@WebServlet(name = "FuncionarioControle", urlPatterns = {"/FuncionarioControle"})
public class FuncionarioControle extends HttpServlet {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private Funcionario funcionario = null;
    private RequestDispatcher rd;
    private Turno turno = null;
    private Funcao funcao = null;
    private Endereco endereco = null;

    protected void processRequest()
            throws ServletException, IOException {
        String cmd = request.getParameter("cmd");
        switch (cmd) {

            case "salvar":
                salvar();
                break;
            case "atuaFuncionario":
                atuaFuncionario();
                break;
            case "cadCompleto":
                cadCompleto();
                break;
            case "excluir":
                excluir();
                break;
            case "combo":
                combo();
                break;
            case "pesquisar":
                pesquisar();
                break;
            case "atualiza":
                atualiza();
                break;
        }

    }

    private void cadCompleto() {
        FuncionarioDao fDao = new FuncionarioDaoImp();
        String id = request.getParameter("id");
        try {
            funcionario = (Funcionario) fDao.procurar(Integer.parseInt(id));
            request.setAttribute("funcionario", funcionario);
            rd = request.getRequestDispatcher("cadFuncCompleto.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
        }

    }

    private Funcionario carregaFuncionario2() {
        funcionario = new Funcionario();
        endereco = new Endereco();
        turno = new Turno();
        funcao = new Funcao();
        String id = request.getParameter("id");
        String idEndereco = request.getParameter("idEndereco");
        String sexo = request.getParameter("sexo");
        if (id != null) {
            funcionario.setId(Integer.parseInt(id));
        }
        if (idEndereco != null) {
            endereco.setId(Integer.parseInt(idEndereco));
        }
        if (sexo == null) {
            funcionario.setSexo(request.getParameter("sexo1"));
        } else {
            funcionario.setSexo(request.getParameter("sexo"));
        }

        funcionario.setNome(request.getParameter("nome"));
        funcionario.setCpf(request.getParameter("cpf"));
        funcionario.setRg(request.getParameter("rg"));
        funcionario.setTelefone(request.getParameter("telefone"));
        funcionario.setCelular(request.getParameter("celular"));
        funcionario.setEmail(request.getParameter("email"));
        funcionario.setCracha(request.getParameter("cracha"));
        funcionario.setSalario(Double.parseDouble(request.getParameter("salario")));
        turno.setId(Integer.parseInt(request.getParameter("turno")));
        funcionario.setIdTurno(turno);
        funcao.setId(Integer.parseInt(request.getParameter("funcao")));
        funcionario.setIdFuncao(funcao);
        String datanascimento = request.getParameter("nascimento");

        try {

            Date data = new SimpleDateFormat("dd/MM/yyyy").parse(datanascimento);
            funcionario.setNascimento(data);
        } catch (Exception e) {
        }
        endereco.setBairro(request.getParameter("bairro"));
        endereco.setCep(request.getParameter("cep"));
        endereco.setCidade(request.getParameter("cidade"));
        endereco.setEstado(request.getParameter("estado"));
        endereco.setPais(request.getParameter("pais"));
        endereco.setRua(request.getParameter("rua"));
        endereco.setSigla(request.getParameter("sigla"));

        funcionario.setIdEndereco(endereco);
        return funcionario;
    }

    private void atualiza() {
        FuncionarioDao fDao = new FuncionarioDaoImp();
        try {
            fDao.alterar(carregaFuncionario2());
            rd = request.getRequestDispatcher("menu.jsp");
            request.setAttribute("msg", "Funcionario alterado com sucesso!!!!");
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(FuncionarioControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void atuaFuncionario() {
        FuncionarioDao fDao = new FuncionarioDaoImp();
        TurnoDao tDao = new TurnoDaoImp();
        FuncaoDao fuDao = new FuncaoDaoImp();
        String id = request.getParameter("id");
        try {
            List<Turno> turnos = tDao.listar();
            List<Funcao> funcoes = fuDao.listar();
            funcionario = (Funcionario) fDao.procurar(Integer.parseInt(id));

            request.setAttribute("funcionario", funcionario);

            request.setAttribute("turnos", turnos);
            request.setAttribute("funcoes", funcoes);
            rd = request.getRequestDispatcher("atuaFunc.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
        }

    }

    private void combo() {
        FuncaoDao fDao = new FuncaoDaoImp();
        TurnoDao tDao = new TurnoDaoImp();
        try {
            List<Funcao> funcoes = fDao.listar();
            request.setAttribute("funcoes", funcoes);
            List<Turno> turnos = tDao.listar();
            request.setAttribute("turnos", turnos);
            rd = request.getRequestDispatcher("cadFunc.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(FuncionarioControle.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Funcionario carregaFuncionario() {
        funcionario = new Funcionario();
        endereco = new Endereco();
        turno = new Turno();
        funcao = new Funcao();
        String id = request.getParameter("id");
        String idEndereco = request.getParameter("idEndereco");
        if (id != null) {
            funcionario.setId(Integer.parseInt(id));
        }
        if (idEndereco != null) {
            endereco.setId(Integer.parseInt(idEndereco));
        }


        funcionario.setNome(request.getParameter("nome"));
        funcionario.setCpf(request.getParameter("cpf"));
        funcionario.setRg(request.getParameter("rg"));
        funcionario.setSexo(request.getParameter("sexo"));
        funcionario.setTelefone(request.getParameter("telefone"));
        funcionario.setCelular(request.getParameter("celular"));
        funcionario.setEmail(request.getParameter("email"));
        funcionario.setCracha(request.getParameter("cracha"));
        funcionario.setSalario(Double.parseDouble(request.getParameter("salario")));
        turno.setId(Integer.parseInt(request.getParameter("turno")));
        funcionario.setIdTurno(turno);
        funcao.setId(Integer.parseInt(request.getParameter("funcao")));
        funcionario.setIdFuncao(funcao);
        String datanascimento = request.getParameter("nascimento");

        try {

            Date data = new SimpleDateFormat("dd/MM/yyyy").parse(datanascimento);
            funcionario.setNascimento(data);
        } catch (Exception e) {
        }
        endereco.setBairro(request.getParameter("bairro"));
        endereco.setCep(request.getParameter("cep"));
        endereco.setCidade(request.getParameter("cidade"));
        endereco.setEstado(request.getParameter("estado"));
        endereco.setPais(request.getParameter("pais"));
        endereco.setRua(request.getParameter("rua"));
        endereco.setSigla(request.getParameter("sigla"));

        funcionario.setIdEndereco(endereco);
        return funcionario;
    }

    private void salvar() {
        FuncionarioDao fDao = new FuncionarioDaoImp();
        try {
            fDao.salvar(carregaFuncionario());
            rd = request.getRequestDispatcher("menu.jsp");
            request.setAttribute("msg", "Funcionario salvo com sucesso!!!!");
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(FuncionarioControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void excluir() {
        PessoaDao fDao = new PessoaDaoImp();

        String idCmd = request.getParameter("id");
        try {
            fDao.excluir(Integer.parseInt(idCmd));
            request.setAttribute("msg", "Funcinario excluido com sucesso!!!!");
            rd = request.getRequestDispatcher("menu.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
        }
    }

    private void listar() {
        FuncionarioDao fDao = new FuncionarioDaoImp();
        try {
            List funcionarios = fDao.listar();
            request.setAttribute("funcionarios", funcionarios);
            rd = request.getRequestDispatcher("pesqFunc.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
        }
    }

    private void pesquisar() {
        FuncionarioDao fDao = new FuncionarioDaoImp();


        try {
            List<Funcionario> funcionarios = fDao.pesquisa(request.getParameter("nome"));
            if (funcionarios.isEmpty()){
                request.setAttribute("alerta", "ATENÇÃO!!  Funcionário não encontrado");
            }
            request.setAttribute("funcionarios", funcionarios);
            rd = request.getRequestDispatcher("pesqFunc.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(FuncionarioControle.class.getName()).log(Level.SEVERE, null, ex);
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
