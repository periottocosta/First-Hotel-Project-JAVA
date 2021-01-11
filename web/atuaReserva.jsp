<%-- 
    Document   : atuaReserva
    Created on : 27/02/2013, 15:17:25
    Author     : Aluno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="imagens/favicon.ico" type="image/x-icon" />
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script src="js/jquery.maskedinput.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="css/Styles.css">
        <link rel="stylesheet" type="text/css" href="cssMenu/styles.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript">
            $(document).ready(function(){
                $("#tel").mask("(99) 9999-9999");
                $("#tel2").mask("(99) 9999-9999");
                $("#data").mask("99/99/9999");
                $("#cep").mask("99.999-999");
                $("#cpf").mask("999.999.999-99");
                $("#rg").mask("99.999.999-9");
            });
        </script>
        <title>Cadastro de Funcinoario</title>
    </head>
    <body>
        <div id="Principal">
            <div id="Conteudo">
                <div id='cssmenu'>
                    <ul>
                        <li class='active'><a href='menu.jsp'><span>Home</span></a></li>
                        <li class='has-sub'><a href='#'><span>Cadastrar</span></a>
                            <ul>
                                <li><a href='cadCliente.jsp'><span>Clientes</span></a></li>
                                <li><a href='FuncionarioControle?cmd=combo'><span>Funcionarios</span></a></li>
                                <li><a href='cadQuarto.jsp'><span>Quartos</span></a></li>
                                <li><a href='novoUsuario.jsp'><span> Usuário</span></a></li>
                                <li><a href='cadFuncao.jsp'><span>Função</span></a></li>
                                <li><a href='cadTurno.jsp'><span>Turno</span></a></li>
                                <li class='last'><a href='cadPerfil.jsp'><span>Perfil</span></a></li>
                            </ul>
                        </li>
                        <li class='has-sub'><a href='#'><span>Pesquisar</span></a>
                            <ul>
                                <li><a href='pesqCliente.jsp'><span>Cliente</span></a></li>
                                <li><a href='pesqFunc.jsp'><span>Funcionario</span></a></li>
                                <li><a href='pesqQuarto.jsp'><span>Quarto</span></a></li>
                                <li><a href='pesqUsuario.jsp'><span> Usuário</span></a></li>
                                <li><a href='FuncaoControle?cmd=comboFuncao'><span>Função</span></a></li>
                                <li><a href='TurnoControle?cmd=comboTurno'><span>Turno</span></a></li>
                                <li class='last'><a href='PerfilControle?cmd=comboPerfil'><span>Perfil</span></a></li>
                            </ul>
                        </li>
                        <li class='has-sub last'><a href='#'><span>Reservar</span></a>
                            <ul>
                                <li><a href='fazReserva.jsp'><span>Fazer Reserva</span></a></li>
                                <li class='last'><a href='pesqReserva.jsp'><span>Pesquisar Reserva</span></a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
                  <div id="pequenocad">
                    <form method="post" action="ReservaControle?cmd=confAltera">
                        <fieldset >
                            <div id="cadReserva">
                                <input type="hidden" name="email" value="${reserva.idCliente.email}"/><br />                      
                                               
                                <label>Nome do Locador </label><br/>
                                <input class="cad" type="text" readonly="readonly" name="nome" value="${reserva.idCliente.nome}"/><br />
                                <label>Cpf </label><br/>
                                <input class="cad" type="text" readonly="readonly" name="cpf" value="${reserva.idCliente.cpf}"/><br />
                                <input type="hidden"  name="quartoOrig" value="${reserva.idQuarto.id}"/>
                                <label>Numero do quarto </label><br/>
                                <select class="select" name="quarto">   
                                    <option value="${reserva.idQuarto.id}">
                                        ${reserva.idQuarto.numero}
                                    </option>   
                                    <c:forEach var="quarto" items="${requestScope.quartos}">                           
                                        <option value="${quarto.id}" >
                                            ${quarto.numero}                              
                                        </option>                        
                                    </c:forEach>
                                </select><br />
                                <label>Data da Reserva </label><br/>
                                <input id="data" type="text"  readonly="readonly" class="cad" name="dataR" value="<fmt:formatDate  value="${reserva.dataReserva}" type="date" pattern="dd/MM/yyyy"/>"/><br />
                                <label>Data Entrada </label><br/>
                                <input id="data2" type="text" class="cad" name="dataE" value="<fmt:formatDate  value="${reserva.dataEntrada}" type="date" pattern="dd/MM/yyyy"/>"/><br />
                                <label>Data Saida </label><br/>
                                <input id="data3" type="text" class="cad" name="dataS" value="<fmt:formatDate  value="${reserva.dataSaida}" type="date" pattern="dd/MM/yyyy"/>"/><br />    
                                <input class="salvarimg" type="submit" title="Salvar Dados" value=""/>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

