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
						<h1>Equipos</h1>
					</div>
				</div>
				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							<th>Nombre empresa</th>
							<th>Nombre equipo</th>
							<%@ include file="/WEB-INF/views/main/options.jsp"%>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${teams}" var="team">
							<tr>
								<td><c:out value="${team.basicName}" /></td>
								<td><c:out value="${team.teamName}" /></td>
								<sec:authorize access="hasAuthority('ADMIN')">
									<jsp:include page="/WEB-INF/views/main/optionButtons.jsp">
										<jsp:param name="id" value="${team.id}" />
										<jsp:param name="objectName" value="Team" />
									</jsp:include>
								</sec:authorize>
							</tr>
						</c:forEach>
						<sec:authorize access="hasAuthority('ADMIN')">
							<tr>
								<td colspan="3">
									<a class="btn btn-default" href="newTeam.html">Nuevo equipo</a> 
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