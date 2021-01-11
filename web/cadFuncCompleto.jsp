<%-- 
    Document   : cadFuncCompleto
    Created on : 27/02/2013, 21:45:02
    Author     : User
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                <div id="cadastros">
                    <form method="post" action="javascript:window.history.go(-1)">
                        <fieldset >
                               <div id="formCad">
                                <legend class="legendPesq">Cadastro de Funcionário</legend>

                                <p class="cadCompleto"> Nome : ${funcionario.nome}</p><br/>
                                <p class="cadCompleto"> Cpf :${funcionario.cpf}</p><br/>
                                <p class="cadCompleto">  Rg :${funcionario.rg}</p><br/>
                                <p class="cadCompleto">  Sexo: ${funcionario.sexo}</p><br/>                           
                                <p class="cadCompleto"> Data Nascimento : <fmt:formatDate  value="${funcionario.nascimento}" type="date" pattern="dd/MM/yyyy"/></p><br />
                                <p class="cadCompleto">  Telefone : ${funcionario.telefone}</p><br/>
                                <p class="cadCompleto"> celular : ${funcionario.celular}</p><br/>
                                <p class="cadCompleto"> E-Mail : ${funcionario.email}</p><br/>
                                <p class="cadCompleto"> Cracha : ${funcionario.cracha}</p><br/>
                                <p class="cadCompleto"> Salario R$ : ${funcionario.salario}</p><br/>
                                <p class="cadCompleto" > Turno : ${funcionario.idTurno.perfio}</p><br/>
                                <p class="cadCompleto"> Função : ${funcionario.idFuncao.nome}</p>
                            </div>
                            <div id="formCad2">
                                <legend class="legendPesq">Endereço</legend>
                                <p class="cadCompleto">  Rua : ${funcionario.idEndereco.rua}</p><br />
                                <p class="cadCompleto">  Cep : ${funcionario.idEndereco.cep}</p><br /> 
                                <p class="cadCompleto">  Bairro :${funcionario.idEndereco.bairro}</p><br />
                                <p class="cadCompleto">  Cidade : ${funcionario.idEndereco.cidade}</p><br />
                                 <p class="cadCompleto"> Estado: ${funcionario.idEndereco.estado}</p><br />
                                <p class="cadCompleto">  Pais : ${funcionario.idEndereco.pais}</p><br />
                                <p class="cadCompleto">  Sigla : ${funcionario.idEndereco.sigla}</p><br />  
                                  <input class="bottomVoltar" type="submit" title="Voltar para a página anterior" value=""/>
                            </div>
                        </fieldset>

                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
