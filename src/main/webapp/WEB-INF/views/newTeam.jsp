<%@ include file="/WEB-INF/views/menu/include.jsp"%>
<!DOCTYPE html>
<html lang="es">
<%@ include file="/WEB-INF/views/menu/header.jsp"%>
<body>

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<%@ include file="/WEB-INF/views/menu/navbar-header.jsp"%>
			<%@ include file="/WEB-INF/views/menu/user.jsp"%>
			<%@ include file="/WEB-INF/views/menu/left_menu.jsp"%>
			<!-- /.navbar-collapse -->
		</nav>

		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12 col-md-12">
						<h1>Nuevo equipo</h1>
					</div>
				</div>
				<form:form class="form-horizontal" method="post"
					modelAttribute="team" action="newTeam.html">
					<form:hidden path="id" />
					<spring:bind path="basicName">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Nombre</label>
							<div class="col-sm-10">
								<form:input path="basicName" type="text" class="form-control "
									id="basicName" placeholder="Nombre" />
								<form:errors path="basicName" class="control-label" />
							</div>
						</div>
					</spring:bind>
					<form:errors path="basicName" />
					<table class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>Año</th>
								<th>Nombre</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${team.names}" var="obj"
								varStatus="status">
								<tr>
									<td>${obj.key}</td>
									<td>${obj.value}</td>
								</tr>
							</c:forEach>
							<sec:authorize access="hasAuthority('ADMIN')">
							<tr>
								<td colspan="2">
									<a class="btn btn-default" href="newTeamName.html">Nuevo nombre</a> 
								</td>
							</tr>
						</sec:authorize>
						</tbody>
					</table>
					<button class="btn btn-default col-md-4 col-md-offset-3">Gorde</button>
				</form:form>
			</div>
			<!-- /.container-fluid -->

		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

</body>

</html>