<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Cadastro Compra</title> 
      <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
      <script src="//cdnjs.cloudflare.com/ajax/libs/jquery.maskedinput/1.4.1/jquery.maskedinput.min.js"></script>
   </head>
   <body>
      <jsp:include page="index.xhtml" flush="false">
         <jsp:param name="cabecalho" value="cabecalho"/>
      </jsp:include>
  <f:view>  
         <form action="ControlCompra" method="POST" name="cadastroCompra">
            <p>
            	<h2>PEDIDO COMPRA</h2>
            </p> 
            <br />
            <p>
            	<input value="ENVIAR" type="submit" id="btn"> <a href="ControlCompra?action=Tabela">CANCELAR</a> 
            </p>
            <br />
            <fieldset id="informacoes"> 
               <p>
                  <label>
                     REFERÊCIA: <input name="referencia"  maxlength="50" value="<c:out value="${compra.idCompra}"/>" />
                  </label>
                  <label>
                     SITUAÇÃO: <c:out value="${compra.status}"/>
                  </label>
               </p> 
               <p>
               	   <label>
                     DATA COMPRA: <input name="dataCompra" type="date" disabled="disabled" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${compra.dataCriada}" />" style="width: 140px; "/>
                  </label>
                  <label>
                     DATA FINALIZADA: <input name="dataFinalizada" disabled="disabled" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${compra.dataFinalizada}" />" style="width: 140px; "/>
                  </label>
               </p>
                
            	 <br />
            		<table border="1">
            			<thead>
            				<tr> 
            					<th>PRODUTO</th>
            					<th style="width: 152px; ">FORNECEDOR</th>
            					<th style="width: 125px; ">QUANTIDADE</th>
            					<th style="width: 168px; ">VALOR UNITÁRIO</th>
            					<th style="width: 118px; ">SUBTOTAL</th>
            					<th colspan="2">AÇÃO</th>
            				</tr>
            			</thead>
            			<tbody>
            				<c:forEach items=" " var="itens">
            					<tr>
            						<td>
            							<c:out value=" " />
           							</td> 
           							<td>
            							<c:out value=" " />
           							</td><td>
            							<c:out value="" />
           							</td><td>
            							<c:out value=" " />
           							</td><td>
            							<c:out value=" " />
           							</td>
            						<td><a href='ControlEstabelecimento?action=Edit&idCompra=<c:out value=" "/>&cod=<c:out value=" "/>'><img src="img/refresh-icon.png" style="width: 21px; height: 21px; " title="ATUALIZAR"></a></td>
                 					<td><a href='ControlEstabelecimento?action=Delete&idCompra=<c:out value=" "/>&cod=<c:out value=" "/>'><img src="img/delete.png" style="width: 21px; height: 21px; " title="EXCLUIR"></a></td>
                 
            					</tr>
            				</c:forEach>
            				 
            					<td>
            						<a href="#">INSERIR ITENS</a>
            					</td>
            			 
            			</tbody>
            		</table>
            </fieldset>
         </form>
   </f:view>
   </body> 
   </body> 
</html>	