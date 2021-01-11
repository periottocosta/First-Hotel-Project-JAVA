<%-- 
    Document   : pesqFuncao
    Created on : 27/02/2013, 10:27:57
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="imagens/favicon.ico" type="image/x-icon" />
        <link rel="stylesheet" type="text/css" href="css/Styles.css">
        <link rel="stylesheet" type="text/css" href="cssMenu/styles.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pesquisar Função</title>
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
                    <form method="post" action="FuncaoControle?cmd=pesqFuncao">
                        <fieldset >      
                            <div id="faserPesquisa">
                                <legend  class="legendPesq">Pesquisar Função</legend>
                                <label class="labelPesq">Função :</label>
                                <select name="funcao"> 
                                    <option value="null">
                                        Escolha um Função
                                    </option>
                                    <c:forEach var="funcao" items="${requestScope.funcoes}">   
                                        <option value="${funcao.id}" >
                                            ${funcao.nome}                            
                                        </option>                        
                                    </c:forEach>
                                </select>
                                <input id="pesqimg" type="submit" title="Pesquisar"value=""/>  
                            </div>
                        </fieldset>
                    </form>
                </div>
                <div id="rotornoPesq">
                    <c:if test="${!empty funcionarios}">
                        <table>
                            <tr>
                                <th>Função</th>
                                <th>Turno</th>
                                <th>Nome do Funcionario</th>
                                <th>Cpf</th>
                                <th>Telefone</th>
                                <th>E-Mail</th>
                            </tr>
                            <c:forEach var="func" items="${requestScope.funcionarios}">
                                <tr>                                                                      
                                    <td>${func.idFuncao.nome}</td>
                                    <td>${func.idTurno.perfio}</td>
                                    <td>${func.nome}</td>
                                    <td>${func.cpf}</td>
                                    <td>${func.telefone}</td>
                                    <td>${func.email}</td>
                                </tr>
                            </c:forEach>
                        </table>            
                    </c:if>
                    <input type="hidden" name="id"  value="${func.id}"/><br />
                    <input type="hidden" name="idTurno"  value="${func.idTurno.id}"/><br />
                </div>
            </div>
        </div>
    </body>
</html>
