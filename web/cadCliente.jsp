<%-- 
    Document   : cadCliente
    Created on : 26/02/2013, 22:35:33
    Author     : User
--%>

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
               / ///$("#rg").mask("99.999.999-9");
            });
        </script>
        <title>Cadastro de Cliente</title>
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
                    <form method="post" action="ClienteControle?cmd=salvar">
                        <fieldset >
                            <div id="formCad">
                                <legend>Cadastro de Cliente</legend>

                                <label>Nome :</label><br />
                                <input class="cad"type="text" placeholder="Nome :" name="nome"/><br />
                                <label>Cpf :</label><br />
                                <input id="cpf" class="cad" placeholder="Cpf :"type="text" name="cpf"/><br />
                                <label>Rg :</label><br />
                                <input class="cad" placeholder="Rg :"type="text" name="rg"/><br />
                                <label>Sexo:</label>
                                <input  type="radio" name="sexo" value="Feminino"/>Feminino
                                <input  type="radio" name="sexo" value="Masculino"/>Masculino<br />
                                <label>Data Nascimento :</label><br />
                                <input id="data" class="cad" placeholder="Data de Nascimento :"type="text" name="nascimento"/><br />
                                <label>Telefone :</label><br />
                                <input id="tel" class="cad" placeholder="Telefone :"type="text" name="telefone"/><br />
                                <label>celular :</label><br />
                                <input id="tel2" class="cad" placeholder="Celular :"type="text"  name="celular"/><br />
                                <label>E-Mail :</label><br />
                                <input type="text" class="cad" placeholder="E-Mail :"name="email"/><br />
                                <label>Cracha :</label><br />
                                <input type="text" class="cad" placeholder="Cracha :"name="cracha"/><br />
                                <label>Salario R$ :</label><br />
                                <input type="text" class="cad" placeholder="Salario R$ :"name="salario"/><br />

                            </div>
                            <div id="formCad2">
                                <legend>Endereço</legend>
                                <label>Rua :</label><br />
                                <input type="text" placeholder="Rua :" class="cad"name="rua"/><br />
                                <label>Cep :</label><br />
                                <input id="cep" class="cad" placeholder="Cep :"type="text"  name="cep"/><br /> 
                                <label>Bairro :</label><br />
                                <input type="text" class="cad"placeholder="Bairro :" name="bairro"/><br />
                                <label>Cidade :</label><br />
                                <input type="text" class="cad"placeholder="Cidade :" name="cidade"/><br />
                                <label>Estado:</label><br />
                                <input type="text" class="cad"placeholder="Estado :"name="estado"/><br />
                                <label>Pais :</label><br />
                                <input type="text" class="cad"placeholder="Pais :" name="pais"/><br />
                                <label>Sigla :</label><br />
                                <input type="text"  class="cad" placeholder="Sigla :"name="sigla"/><br />    
                                <input class="salvarimg" type="submit" title="Salvar Dados" value=""/>
                            </div>

                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
