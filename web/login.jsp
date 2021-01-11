<%-- 
    Document   : login
    Created on : 26/02/2013, 20:27:27
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/Styles.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="Principal" >
            <div id="Conteudo">
                <c:if test="${mens != null}">
                    <script type="text/javascript">                   
                        alert('${mens}');                   
                    </script>
                </c:if>
                <div id="logo" >
                </div>
                <div id="loginSub">
                    <p>Login</p>
                </div>
                <div id="login">
                    <div id="menss">
                        <c:if test="${alerta != null}">
                            <div class="error">
                                <P class="ppf">${alerta}</p>
                            </div>
                        </c:if>
                    </div>
                    <form  method="post" action="UsuarioControle?cmd=login">  
                        <div id="formLogin">
                            <div id="inpnt_login"> <input class="inputLogin"  placeholder="Usuário"type="text" name="login"/></div>
                            <label> </label><br/>
                            <div id="inpnt_login2">  <input class="inputLogin"  placeholder="Senha"type="password" name="senha"/></div>
                            <input id="butonLogin" type="submit" value="Logar"/>
                        </div>
                    </form>   
                </div>

            </div>
        </div>
    </body>
</html>
