<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
   <head>

  	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
   		
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
      <title>REDE DE PADARIAS AGORA</title>
      
      <link rel="shortcut icon" href="img/Logo_Padaria.png"/>
      <link rel="stylesheet" href="css/bootstrap.min.css"/>
      <link rel="stylesheet" type="text/css" href="css/estilo.css" media="screen" /> 
      <style>
      
     @import url('https://fonts.googleapis.com/css2?family=Special+Elite&display=swap');
      
      .dropbtn {
  
  color: white;
  padding: 16px;
  font-size: 16px;
  border: none;
}

.dropdown {
  position: relative;
  display: inline-block; 
  text-align: center;  
}

.dropdown-content {
  display: none;
  position: absolute;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
  width: 10em;
 
}

.dropdown-content a{
  color: white; 
  text-decoration: none;
  display: block; 
  text-align: left;
  padding: 5px;
}

.dropdown-content a:hover{
 background-color: rgb(107,57,23);
}

.dropdown:hover .dropdown-content{
display: block;
 background-color: rgb(99,48,14);
} 
 
      </style>

   </head>
   <body> 
   	
	
     <div class="navbar" style="padding: 2em; background-color: rgb(107,57,23)">
     	<h5><a href="ControlCompra?action=Tabela" class="nav-link text-light   d-inline margin">Compra</a>
            <a href="ControlProduto?action=Tabela" class="nav-link text-light   d-inline">Produtos</a>
            <a href="ControlFornecedores?action=Tabela" class="nav-link text-light   d-inline">Fornecedores</a>
            <a href="ControlCliente?action=Tabela" class="nav-link text-light   d-inline">Clientes</a>
            <a href="ControlEstoque?action=Tabela" class="nav-link text-light   d-inline">Estoque</a> 
            <a href="ControlFuncionario?action=Tabela" class="nav-link text-light   d-inline">Funcionarios</a>
            <a href="ControlEstabelecimento?action=Tabela" class="nav-link text-light   d-inline">Filiais</a>
            <a href="ControlVenda?action=Caixa" class="nav-link text-light   d-inline">Venda</a></h5>
            
             <div class="dropdown">
  			 <h5 class="text-light">Financeiro</h5> 
  			<div class="dropdown-content text-white">
   				 <a href="ControlContasPagar?action=Tabela">Contas a pagar</a>
   			 	  <a href="ControlContasReceber?action=Tabela">Contas a receber</a>
  				</div>
			 </div>
           <h5> <a href="ControlLogin?action=Deslogar" class="nav-link text-light   d-inline">Sair</a></h5>
  </div> 
     
   </body>
</html>