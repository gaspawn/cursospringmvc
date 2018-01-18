<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>Livros de java, Android, Iphone, PHP, Ruby e muito mais -
	Casa do código</title>
<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath }/bootstrap.min.css" />
<link rel="stylesheet" href="${cssPath }/bootstrap-theme.min.css" />

</head>
<body>
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<li><a href="${s:mvcUrl('PC#listar').build() }" rel="nofollow">Listagem
						de Produtos</a></li>
				<li><a href="${s:mvcUrl('PC#form').build() }" rel="nofollow">Cadastro
						de Produtos</a></li>
			</security:authorize>
		</ul>
	</div>
	<ul class="nav navbar-nav navbar-right">
		<li><a href="#"> <security:authentication
					property="principal" var="usuario" /> Usuário: ${usuario.username}
		</a></li>
	</ul>

	<div>${sucesso }</div>
	<table>
		<tr>
			<td>Título</td>
			<td>Descrição</td>
			<td>Páginas</td>
		</tr>

		<c:forEach items="${produtos }" var="produto">
			<tr>
				<td><a
					href="${s:mvcUrl('PC#detalhe').arg(0,produto.id).build()}">${produto.titulo}
				</a></td>
				<td>${produto.descricao }</td>
				<td>${produto.paginas }</td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>