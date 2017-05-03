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
					modelAttribute="teamName" action="newTeamName.html">
					<spring:bind path="year">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Año</label>
							<div class="col-sm-10">
								<form:input path="year" type="number" class="form-control "
									id="year" placeholder="Nombre" />
								<form:errors path="year" class="control-label" />
							</div>
						</div>
					</spring:bind>
					<form:errors path="year" />
					<spring:bind path="name">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Nombre</label>
							<div class="col-sm-10">
								<form:input path="name" type="text" class="form-control "
									id="name" placeholder="Nombre" />
								<form:errors path="name" class="control-label" />
							</div>
						</div>
					</spring:bind>
					<form:errors path="name" />
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