<%-- 
    Document   : atuaUsuario
    Created on : 27/02/2013, 15:21:18
    Author     : Aluno
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>Altera Usuario</title>
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
                    <form method="post" action="UsuarioControle?cmd=alterar">
                        <fieldset >
                            <div id="cadReserva">
                                <legend class="legendPesq">Painel do Usuario</legend>
                                <input type="hidden" name="id" value="${funcionario.usuario.id}"/><br />   
                                <label>Nome</label><br/>
                                <input class="cad" type="text" readonly="readonly" name="nome" value="${funcionario.nome}" /><br />                           
                                <label>E-mail</label><br/>
                                <input class="cad" type="text" readonly="readonly" name="email" value="${funcionario.email}" /><br />   
                                <label>Novo Login </label><br/>
                                <input class="cad"type="text" name="login" /><br />                       
                                <input type="hidden" name="login1" value="${funcionario.usuario.login}"/>                       
                                <label> Nova senha </label><br/>
                                <input class="cad" type="text" name="senha" /><br />                    
                                <input type="hidden" name="senha1" value="${funcionario.usuario.senha}"/>   
                                  <input class="salvarimg" type="submit" title="Salvar Dados" value=""/>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
