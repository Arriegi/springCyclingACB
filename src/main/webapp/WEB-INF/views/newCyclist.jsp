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
						<h1>Nuevo ciclista</h1>
					</div>
				</div>
				<form:form class="form-horizontal" method="post"
					modelAttribute="cyclist" action="newCyclist.html">
					<form:hidden path="id" />
					<spring:bind path="firstName">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Nombre</label>
							<div class="col-sm-10">
								<form:input path="firstName" type="text" class="form-control "
									id="firstName" placeholder="Nombre" />
								<form:errors path="firstName" class="control-label" />
							</div>
						</div>
					</spring:bind>
					<form:errors path="firstName" />
					<spring:bind path="lastName">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Apellido</label>
							<div class="col-sm-10">
								<form:input path="lastName" type="text" class="form-control "
									id="lastName" placeholder="Apellido" />
								<form:errors path="lastName" class="control-label" />
							</div>
						</div>
					</spring:bind>
					<form:errors path="lastName" />
					<spring:bind path="birthdate">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Fecha de nacimiento</label>
							<div class="col-sm-10">
								<fmt:formatDate value="${cyclist.birthdate}" type="date"
									pattern="yyyy-MM-dd" var="formatDate" />
								<form:input path="birthdate" type="date" class="form-control "
									id="birthdate" placeholder="Fecha de nacimiento" value="${formatDate}" />
								<form:errors path="birthdate" class="control-label" />
							</div>
						</div>
					</spring:bind>
					<spring:bind path="country">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">País</label>
							<div class="col-sm-5">
								<form:select path="country" class="form-control">
									<form:options items="${countries}" itemValue="id"
										itemLabel="name" />
								</form:select>
								<form:errors path="country" class="control-label" />
							</div>
							<div class="col-sm-5"></div>
						</div>
					</spring:bind>
					<!-- bind to user.name-->
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