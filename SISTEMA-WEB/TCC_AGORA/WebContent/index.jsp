<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
   <head>

  	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
   		
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
      <title>REDE DE PADARIAS AGORA</title>
      
      <link rel="shortcut icon" href="img/Logo_Padaria.png"/>
      <link rel="stylesheet" href="css/bootstrap.min.css"/>
      <link rel="stylesheet" href="css/estilo.css"/>
      <link rel="stylesheet" type="text/css" href="css/style.css" media="screen" /> 
      <style>
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
  width: 15em;
 
}

.dropdown-content a{
  color: #fff;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.dropdown-content a:hover{
 background-color: ##1d2124;
}

.dropdown:hover .dropdown-content{
display: block;
}

.dropdown:hover .dropbtn{

}
      </style>
   </head>
   <body> 

     <div class="navbar navbar-dark bg-dark" style="padding: 2em;">
     	<h5><a href="ControlCompra?action=Tabela" class="nav-link text-light bg-dark d-inline margin">Compra</a>
            <a href="ControlProduto?action=Tabela" class="nav-link text-light bg-dark d-inline">Produtos</a>
            <a href="ControlFornecedores?action=Tabela" class="nav-link text-light bg-dark d-inline">Fornecedores</a>
            <a href="ControlCliente?action=Tabela" class="nav-link text-light bg-dark d-inline">Clientes</a>
            <a href="ControlEstoque?action=Tabela" class="nav-link text-light bg-dark d-inline">Estoque</a> 
            <a href="ControlFuncionario?action=Tabela" class="nav-link text-light bg-dark d-inline">Funcionarios</a>
            <a href="ControlEstabelecimento?action=Tabela" class="nav-link text-light bg-dark d-inline">Filiais</a>
            <a href="ControlVenda?action=Caixa" class="nav-link text-light bg-dark d-inline">Venda</a></h5>
             <div class="dropdown">
  			<button class="dropbtn  text-light bg-dark d-inline font-weight-bold nav-link" style="margin-bottom: 2em"><h5>Financeiro</h5></button>
  			<div class="dropdown-content bg-dark text-white">
   				 <a href="ControlContasPagar?action=Tabela">Contas a pagar</a>
   			 	  <a href="ControlContasReceber?action=Tabela">Contas a receber</a>
  				</div>
			</div>
           <h5> <a href="ControlLogin?action=Deslogar" class="nav-link text-light bg-dark d-inline">Sair</a></h5>
  </div>

      <!-- Fim Cabeçalho -->
      
   </body>
</html>