<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
   <head>

  	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
   		
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
      <title>REDE DE PADARIAS ÀGORA</title>
      
      <link rel="shortcut icon" href="img/Logo_Padaria.png"/>
      <link rel="stylesheet" href="css/bootstrap.min.css"/>
      <link rel="stylesheet" href="css/estilo.css"/>
      <link rel="stylesheet" type="text/css" href="css/style.css" media="screen" /> 
   </head>
   <body>
   <%
   	String usuario = (String) session.getAttribute("usuario");
   if(usuario == null){
	   response.sendRedirect("Login.xhtml");
   }
   %>

     <div class="navbar navbar-dark bg-dark" style="padding: 2em;">
     	<h5><a href="ControlCompra?action=Tabela" class="nav-link text-light bg-dark d-inline margin">Compra</a>
            <a href="ControlProduto?action=Tabela" class="nav-link text-light bg-dark d-inline">Produtos</a>
            <a href="ControlFornecedores?action=Tabela" class="nav-link text-light bg-dark d-inline">Fornecedores</a>
            <a href="ControlCliente?action=Tabela" class="nav-link text-light bg-dark d-inline">Clientes</a>
            <a href="ControlContasPagar?action=Tabela" class="nav-link text-light bg-dark d-inline">conta pagar</a>
            <a href="ControlEstoque?action=Tabela" class="nav-link text-light bg-dark d-inline">Estoque</a> 
            <a href="ControlFuncionario?action=Tabela" class="nav-link text-light bg-dark d-inline">Funcionarios</a>
            <a href="ControlContasReceber?action=Tabela" class="nav-link text-light bg-dark d-inline">Conta receber</a>
            <a href="ControlEstabelecimento?action=Tabela" class="nav-link text-light bg-dark d-inline">Filiais</a>
            <a href="ControlVenda?action=Caixa" class="nav-link text-light bg-dark d-inline">Venda</a>
            <a href="ControlLogin?action=Deslogar" class="nav-link text-light bg-dark d-inline">Sair</a></h5>
  </div>

      <!-- Fim Cabeçalho -->
      
   </body>
</html>