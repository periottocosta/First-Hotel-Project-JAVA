package Controle;

import entidadesDoBanco.Funcionario;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "filtraLogin", value = "*.jsp")
public class FiltraUsuario implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        String cmd = request.getParameter("cmd");
        HttpServletRequest httReq = null;
        httReq = (HttpServletRequest) request;
        System.out.println(httReq.getServletPath());
        if(cmd == null){
            cmd = "";
        }
        HttpSession session = ((HttpServletRequest) request).getSession() ;
        RequestDispatcher rd;
        Funcionario funcionario = (Funcionario) session.getAttribute("funcionario");
        if(httReq.getServletPath().equals("login.jsp")){
          chain.doFilter(request, response);         
        }else if(funcionario == null && cmd.equals("login")){
            chain.doFilter(request, response);
        }else if(funcionario !=null){
            chain.doFilter(request, response);
        }else{
          request.setAttribute("mens", "Você não esta logado no sistema!!!!!");
          rd = request.getRequestDispatcher("login.jsp");
          rd.forward(request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("");
    }
}
