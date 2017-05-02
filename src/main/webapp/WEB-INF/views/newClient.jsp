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
						<h1>Bezero berria</h1>
					</div>
				</div>
				<form:form class="form-horizontal" method="post"
					modelAttribute="client" action="newClient.html">
					<form:hidden path="id" />
					<spring:bind path="name">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Izena</label>
							<div class="col-sm-10">
								<form:input path="name" type="text" class="form-control "
									id="name" placeholder="Name" />
								<form:errors path="name" class="control-label" />
							</div>
						</div>
					</spring:bind>
					<spring:bind path="contactName">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Kontaktuaren izena</label>
							<div class="col-sm-10">
								<form:input path="contactName" type="text" class="form-control "
									id="contactName" placeholder="Kontaktuaren izena" />
								<form:errors path="contactName" class="control-label" />
							</div>
						</div>
					</spring:bind>
					<spring:bind path="email">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Email</label>
							<div class="col-sm-10">
								<form:input path="email" class="form-control" id="email"
									placeholder="Email" />
								<form:errors path="email" class="control-label" />
							</div>
						</div>
					</spring:bind>
					<spring:bind path="email">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Telefonoa</label>
							<div class="col-sm-10">
								<form:input path="phone" class="form-control" id="phone"
									placeholder="Telefonoa" />
								<form:errors path="phone" class="control-label" />
							</div>
						</div>
					</spring:bind>
					<!-- bind to user.name-->
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