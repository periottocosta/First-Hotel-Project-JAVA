<?

// aqui come�a o script
//pega as variaveis por POST
$nome      = $_POST["nome"];
$site     = $_POST["site"];
$email   = $_POST["email"];
$fone  = $_POST["tel"];
$assunto   = $_POST["assunto"];
$mensagem  = $_POST["mensagem"];
$entrada  = $_POST["checkin"];
$saida  = $_POST["checkout"];
$adulto = $_POST["adultos"];
$crianca = $_POST["criancas"];

global $email; //fun��o para validar a vari�vel $email no script todo

$data      = date("d/m/y");                     //fun��o para pegar a data de envio do e-mail
//$ip        = $_SERVER['REMOTE_ADDR'];           //fun��o para pegar o ip do usu�rio
//$navegador = $_SERVER['HTTP_USER_AGENT'];       //fun��o para pegar o navegador do visitante
$hora      = date("H:i");                       //para pegar a hora com a fun��o date
  
//aqui envia o e-mail para voc�
mail ("hotel.flipper@hotmail.com",                       //email aonde o php vai enviar os dados do form
      "$assunto",
      "Nome: $nome\nData: $data\nHora: $hora\nE-mail: $email\nTelefone: $fone\nCheck In: $entrada\nCheck Out: $saida\nN� de adultos: $adulto\nN� de crian�a: $crianca\nMensagem: $mensagem",
      "From: $email"
     );

//aqui s�o as configura��es para enviar o e-mail para o visitante
$site   = "hotel.flipper@hotmail.com";                    //o e-mail que aparecer� na caixa postal do visitante
$titulo = "Hotel Flipper";                  //titulo da mensagem enviada para o visitante
$msg    = "$nome, obrigado por entrar em contato conosco, em breve entraremos em contato";

//aqui envia o e-mail de auto-resposta para o visitante
mail("$email",
     "$titulo",
     "$msg",
     "From: $site"
    );
#echo "<p align=center>$nome, sua mensagem foi enviada com sucesso!</p>";
#echo "<p align=center>Estaremos retornando em breve.</p>";
#echo "<script language=javascript>alert( '$nome, sua mensagem foi enviada com sucesso!' );</script>";
echo "<script>window.location.href='contato.html'</script>";


#echo "<script language=javascript>alert( 'Alerta Vermelho!' );</script>";
##########################################################
/////////////////////////////////////////////////////  ###
/// Autor: Mateus Campos                         ////  ###
/// E-mail: mateuscampos@globo.com               ////  ###
/// Site: www.centralwarez.com                   ////  ###
/// Msn: mateus@centralwarez.com                 ////  ###
/// Obs: favor n�o retirar os nossos cr�ditos!!! ////  ###
?>
