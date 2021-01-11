<%-- 
    Document   : atuaCliente
    Created on : 27/02/2013, 15:12:57
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
               // $("#rg").mask("99.999.999-9");
            });
        </script>
        <title>Atualiza Cliente</title>
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
                    <form method="post" action="ClienteControle?cmd=atualizar">
                        <fieldset >
                            <div id="formCad">
                                   <legend>Alterar Clientes</legend>
                            <input type="hidden" name="id"  value="${cliente.id}"/><br />
                            <label>Nome :</label><br />
                            <input type="text" name="nome" class="cad" value="${cliente.nome}"/><br />
                            <label>Cpf :</label><br />
                            <input id="cpf" type="text" class="cad" name="cpf" value="${cliente.cpf}"/><br />
                            <label>Rg :</label><br />
                            <input  type="text" class="cad" name="rg" value="${cliente.rg}"/><br />
                            <label>Sexo:</label><br/>
                            <input type="text" name="sexo1" class="cad" readonly="readonly"value="${cliente.sexo}"/><br />
                            <input  type="radio" name="sexo" value="Feminino"/>Feminino
                            <input  type="radio" name="sexo" value="Masculino"/>Masculino<br />
                            <label>Data Nascimento :</label><br />
                            <input id="data" class="cad" name="nascimento" value="<fmt:formatDate  value="${cliente.nascimento}" type="date" pattern="dd/MM/yyyy"/>"  />                           
                          <br />
                            <label>Telefone :</label><br />
                            <input id="tel" class="cad"type="text" name="telefone" value="${cliente.telefone}"/><br />
                            <label>celular :</label><br />
                            <input id="tel2" class="cad"type="text"  name="celular" value="${cliente.celular}"/><br />
                            <label>E-Mail :</label><br />
                            <input type="text" class="cad" name="email" value="${cliente.email}"/><br />
                            <label>Codeigo :</label><br />
                            <input type="text" class="cad" name="codigo" value="${cliente.codigo}"/><br />
                            <label>Depedencia:</label><br />
                            <input type="text" class="cad" name="depedencia"value="${cliente.depedentes}"/><br />
                            </div>
                            <div id="formCad2">
                                 <legend>Endereço</legend>
                            <input type="hidden" class="cad"name="idEndereco"  value="${cliente.idEndereco.id}"/><br />
                            <label>Rua :</label><br />
                            <input type="text" class="cad"name="rua" value="${cliente.idEndereco.rua}"/><br />
                            <label>Cep :</label><br />
                            <input id="cep" type="text" class="cad" name="cep" value=" ${cliente.idEndereco.cep}"/><br /> 
                            <label>Bairro :</label><br />
                            <input type="text" name="bairro"class="cad" value="${cliente.idEndereco.bairro}"/><br />
                            <label>Cidade :</label><br />
                            <input type="text" name="cidade"class="cad" value="${cliente.idEndereco.cidade}"/><br />
                            <label>Estado:</label><br />
                            <input type="text" name="estado"class="cad" value="${cliente.idEndereco.estado}"/><br />
                            <label>Pais :</label><br />
                            <input type="text"class="cad" name="pais"value="${cliente.idEndereco.pais}"/><br />
                            <label>Sigla :</label><br />
                            <input type="text" class="cad" name="sigla" value="${cliente.idEndereco.sigla}"/><br />    
                             <input class="salvarimg" type="submit" title="Salvar Dados" value=""/>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

