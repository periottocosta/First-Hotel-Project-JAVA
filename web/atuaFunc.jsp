<%-- 
    Document   : atuaFunc
    Created on : 27/02/2013, 15:07:07
    Author     : Aluno
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
                    <form method="post" action="FuncionarioControle?cmd=atualiza">
                        <fieldset >
                            <div id="formCad">
                                <legend>Alterar funcionarios</legend>
                                <input type="hidden" name="id" value="${funcionario.id}" />
                                <label>Nome :</label><br />
                                <input type="text" name="nome" class="cad" value="${funcionario.nome}" /><br />
                                <label>Cpf :</label><br />
                                <input id="cpf" type="text"class="cad" name="cpf" value="${funcionario.cpf}"/><br />
                                <label>Rg :</label><br />
                                <input id="rg" type="text" class="cad"name="rg"value="${funcionario.rg}"/><br />
                                <label>Sexo:</label><br/>
                                <input type="text" class="cad" name="sexo1" readonly="readonly"value="${funcionario.sexo}"/><br />
                                <input  type="radio" name="sexo" value="Feminino"/>Feminino
                                <input  type="radio" name="sexo" value="Masculino"/>Masculino<br />
                                <label>Data Nascimento :</label><br />
                                <input id="data"class="cad" name="nascimento" value="<fmt:formatDate  value="${funcionario.nascimento}" type="date" pattern="dd/MM/yyyy"/>"/>
                                <br />
                                <label>Telefone :</label><br />
                                <input id="tel"class="cad" type="text" name="telefone" value="${funcionario.telefone}"/><br />
                                <label>celular :</label><br />
                                <input id="tel2" class="cad"type="text"  name="celular" value="${funcionario.celular}"/><br />
                                <label>E-Mail :</label><br />
                                <input type="text" class="cad" name="email" value="${funcionario.email}"/><br />
                                <label>Cracha :</label><br />
                                <input type="text" class="cad" name="cracha" value="${funcionario.cracha}"/><br />
                                <label>Salario R$ :</label><br />
                                <input type="text" class="cad" name="salario" value="${funcionario.salario}"/><br />

                                <label>Turno</label><br />
                                <select name="turno">  
                                    <option value="${funcionario.idTurno.id}">
                                        ${funcionario.idTurno.perfio}
                                    </option>
                                    <c:forEach var="turno" items="${requestScope.turnos}">   
                                        <c:if test="${funcacionario.idTurno.perfio != turno.perfio}">
                                            <option value="${turno.id}" >
                                                ${turno.perfio}                            
                                            </option>   
                                        </c:if>
                                    </c:forEach>
                                </select><br />             
                                <label>Função</label><br />
                                <select name="funcao">   
                                    <option value="${funcionario.idFuncao.id}">
                                        ${funcionario.idFuncao.nome}
                                    </option>
                                    <c:forEach var="funcao" items="${requestScope.funcoes}"> 
                                        <c:if test="${funcionario.idFuncao.nome != fucao.nome}">
                                            <option value="${funcao.id}" >
                                                ${funcao.nome}                            
                                            </option>                        
                                        </c:if>
                                    </c:forEach>
                                </select><br />
                            </div>
                            <div id="formCad2">
                                <legend>Endereço</legend>
                                <input type="hidden"class="cad" name="idEndereco" value="${funcionario.idEndereco.id}" /><br />
                                <label>Rua :</label><br />
                                <input type="text" class="cad"name="rua" value="${funcionario.idEndereco.rua}"/><br />
                                <label>Cep :</label><br />
                                <input id="cep"class="cad" type="text"  name="cep" value="${funcionario.idEndereco.cep}"/><br /> 
                                <label>Bairro :</label><br />
                                <input type="text" class="cad" name="bairro" value="${funcionario.idEndereco.bairro}"/><br />
                                <label>Cidade :</label><br />
                                <input type="text" class="cad" name="cidade" value="${funcionario.idEndereco.cidade}"/><br />
                                <label>Estado:</label><br />
                                <input type="text" class="cad" name="estado" value="${funcionario.idEndereco.estado}"/><br />
                                <label>Pais :</label><br />
                                <input type="text" class="cad" name="pais" value="${funcionario.idEndereco.pais}"/><br />
                                <label>Sigla :</label><br />
                                <input type="text"class="cad" name="sigla" value="${funcionario.idEndereco.sigla}"/><br />  
                                <input class="salvarimg" type="submit" title="Salvar Dados" value=""/>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>