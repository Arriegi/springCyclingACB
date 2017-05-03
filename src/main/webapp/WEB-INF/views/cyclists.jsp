<%@ include file="/WEB-INF/views/menu/include.jsp"%>
<!DOCTYPE html>
<html lang="es">
<%@ include file="/WEB-INF/views/menu/header.jsp"%>
<body>

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<%@ include file="/WEB-INF/views/menu/navbar-header.jsp"%>
			<!-- Top Menu Items -->
			<%@ include file="/WEB-INF/views/menu/user.jsp"%>
			<%@ include file="/WEB-INF/views/menu/left_menu.jsp"%>
			<!-- /.navbar-collapse -->
		</nav>

		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12 col-md-12">
						<h1>Ciclistas</h1>
					</div>
				</div>
				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							<th>Nombre</th>
							<th>Apellido</th>
							<th>Fecha de nacimiento</th>
							<th>País</th>
							<th>Equipo</th>
							<%@ include file="/WEB-INF/views/main/options.jsp"%>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${cyclists}" var="cyclist">
							<tr>
								<td><c:out value="${cyclist.firstName}" /></td>
								<td><c:out value="${cyclist.lastName}" /></td>
								<td><fmt:formatDate pattern="yyyy-MM-dd" value="${cyclist.birthdate}" /></td>
								<td><c:out value="${cyclist.country.name}" /></td>
								<td><c:out value="${cyclist.teamName}" /></td>
								<sec:authorize access="hasAuthority('ADMIN')">
									<jsp:include page="/WEB-INF/views/main/optionButtons.jsp">
										<jsp:param name="id" value="${cyclist.id}" />
										<jsp:param name="objectName" value="Cyclist" />
									</jsp:include>
								</sec:authorize>
							</tr>
						</c:forEach>
						<sec:authorize access="hasAuthority('ADMIN')">
							<tr>
								<td colspan="6">
									<a class="btn btn-default" href="newCyclist.html">Nuevo ciclista</a> 
								</td>
							</tr>
						</sec:authorize>
					</tbody>
				</table>
			</div>
			<!-- /.container-fluid -->

		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->
	<%@ include file="/WEB-INF/views/confirm_modal.jsp"%>
	<!-- Bootstrap Core JavaScript -->
	<script src="resources/js/clients.js"></script>
</body>

</html>