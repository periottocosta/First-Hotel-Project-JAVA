<%-- 
    Document   : reservar
    Created on : 27/02/2013, 21:21:03
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script src="js/jquery.maskedinput.js" type="text/javascript"></script>
        <link rel="shortcut icon" href="imagens/favicon.ico" type="image/x-icon" />
        <link rel="stylesheet" type="text/css" href="css/Styles.css">
        <link rel="stylesheet" type="text/css" href="cssMenu/styles.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript">
            $(document).ready(function(){
                
                $("#data").mask("99/99/9999");
                $("#data2").mask("99/99/9999");
                $("#data3").mask("99/99/9999");
                  });
                function clickMe()  
                {  
                   // var form = "#Salvar";
                    var dataR = (document.forms[0]["data"].value).split("/");  
                    var dataE = (document.forms[0]["data2"].value).split("/");  
                    var dataS = (document.forms[0]["data3"].value).split("/");                    
                    var dataReserva = new Date(dataR[2], dataR[1]-1, dataR[0]);  
                    var dataEntrada = new Date(dataE[2], dataE[1]-1, dataE[0]);  
                    var dataSaida = new Date(dataS[2], dataS[1]-1, dataS[0]);  
                    
                    if ( dataEntrada.getDate() >= dataReserva.getDate() ){  
                        if(dataSaida.getDate() <= dataEntrada.getDate()){
                            alert("Data de saida não pode ser menor que a data de entrada");
                            location.href="reserva.jsp";
                        }else {
                           
                           // form.action = "ReservaControle?cmd=salvar";
                        }
                    }else{  
                        alert("Data de entrada tem que ser maior ou igual a data de Reserva"); 
                        location.href="reserva.jsp";
                    }  
                }  
             //   onClick="clickMe()"
          
        </script>
        <title>Reservar</title>
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
                    <form id="Salvar" method="post" action="ReservaControle?cmd=salvar">
                        <fieldset >      
                            <div id="cadReserva">
                                <legend class="legendPesq">Reservas</legend>
                                <input type="hidden" name="idCliente" value="${idCliente}"/><br />
                                <input type="hidden" name="email" value="${email}"/><br />                      
                                <label>Nome do Locador </label><br/>
                                <input class="cad" type="text" readonly="readonly" name="nome" value="${nome}"/><br />
                                <label>Cpf </label><br/>
                                <input class="cad" type="text" readonly="readonly" name="cpf" value="${cpf}"/><br />
                                <label>Numero do quarto </label><br/>
                                <select class="select" name="quarto">   
                                    <option>Escolha o quarto</option>
                                    <c:forEach var="quarto" items="${requestScope.quartos}">                           
                                        <option value="${quarto.id}" >
                                            numero ${quarto.numero}                              
                                        </option>                        
                                    </c:forEach>
                                </select><br />
                                <label>Data da Reserva </label><br/>
                                <input class="cad" id="data" type="text" name="dataR" value="${data}"/><br />
                                <label>Data Entrada </label><br/>
                                <input id="data2" class="cad"  type="text" name="dataE"/><br />
                                <label>Data Saida </label><br/>
                                <input id="data3"class="cad" type="text" name="dataS"/><br />    
                                <input class="salvarimg" type="submit"  title="Salvar Dados" value=""/>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
