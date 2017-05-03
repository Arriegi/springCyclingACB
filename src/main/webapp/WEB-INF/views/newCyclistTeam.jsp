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
						<h1><c:out value="${cyclist.fullName}"></c:out></h1>
					</div>
				</div>
				<form:form class="form-horizontal" method="post"
					modelAttribute="cyclistTeam" action="newCyclistTeam.html">
					<spring:bind path="year">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Año</label>
							<div class="col-sm-10">
								<form:input path="year" type="number" class="form-control "
									id="year" placeholder="Año" />
								<form:errors path="year" class="control-label" />
							</div>
						</div>
					</spring:bind>
					<form:errors path="year" />
					<spring:bind path="team">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Equipos</label>
							<div class="col-sm-5">
								<form:select path="team" class="form-control">
									<form:options items="${teams}" itemValue="id"
										itemLabel="teamName" />
								</form:select>
								<form:errors path="team" class="control-label" />
							</div>
							<div class="col-sm-5"></div>
						</div>
					</spring:bind>
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