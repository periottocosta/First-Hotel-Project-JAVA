<%-- 
    Document   : pesqQuarto
    Created on : 27/02/2013, 21:39:15
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
        <title>Pesquisar Quarto</title>
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
                    <form method="post" action="QuartoControle?cmd=comboQuarto">
                        <fieldset >      
                            <div id="faserPesquisa">
                                <legend class="legendPesq">Pesquisar Turno</legend>
                                <label class="labelPesq">Quarto :</label>                               
                                   <input id="pesqimg" type="submit" title="Pesquisar"value=""/>  
                            </div>
                        </fieldset>
                    </form>
                </div>
                <div id="rotornoPesq">
                    <c:if test="${!empty quartos}">
                        <table>
                            <tr>                              
                                <th>Numero</th>
                                <th>Capacidade</th>
                                <th>Descrição</th>
                                <th>Estatus</th>                                
                                <th>Excluir</th>
                            </tr>
                            <c:forEach var="quarto" items="${quartos}">
                                <tr>                                                                      
                                    <td>${quarto.numero}</td>
                                    <td>${quarto.capacidade}</td>
                                    <td>${quarto.descricao}</td>                                    
                                    <td>${quarto.idEstatusQuarto.status}</td>
                                    <td>  
                                        <a href="TurnoControle?cmd=excluir&id=${func.id}&idTurno=${func.idTurno.id}"><input id="buton2" type="submit" value="EXCLUIR"/></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>            
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
