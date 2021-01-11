function verificaForm(){
   var formulario = document.getElementById('form');
   
   var email = formulario.email.value;
   var assunto = formulario.assunto.value;
   var nome = formulario.nome.value;
  
   
   
   
   var mens = "Os campos são obrigatórios";
   var opcao = false;
     if(nome == ""){
    opcao = true;
	mens = mens + "\n * Nome";
   }   
   
   if(email == ""){
	opcao = true;
	mens = mens + "\n * E-mail";
   }   
   if(assunto == ""){
	opcao = true;
	mens = mens + "\n * assunto";
   }   
   if(opcao){
   alert(mens);
   }else{
   
      
    alert(nome + ",e-mail enviado com sucesso!");
   }
};