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
						<h1>Langile berria</h1>
					</div>
				</div>
				<form:form class="form-horizontal" method="post"
					modelAttribute="worker" action="newWorker.html">
					<form:hidden path="id" />
					<spring:bind path="name">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Izena</label>
							<div class="col-sm-10">
								<form:input path="name" type="text" class="form-control "
									id="name" placeholder="Izena" />
								<form:errors path="name" class="control-label" />
							</div>
						</div>
					</spring:bind>
					<spring:bind path="lastName1">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">1. abizena</label>
							<div class="col-sm-10">
								<form:input path="lastName1" type="text" class="form-control "
									id="lastName1" placeholder="1. abizena" />
								<form:errors path="lastName1" class="control-label" />
							</div>
						</div>
					</spring:bind>
					<spring:bind path="lastName2">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">2. abizena</label>
							<div class="col-sm-10">
								<form:input path="lastName2" type="text" class="form-control "
									id="lastName2" placeholder="2. abizena" />
								<form:errors path="lastName2" class="control-label" />
							</div>
						</div>
					</spring:bind>
					<spring:bind path="hourCost">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Koste/Ordu</label>
							<div class="col-sm-10">
								<form:input path="hourCost" type="number" class="form-control "
									id="hourCost" placeholder="Kost/Ordu" />
								<form:errors path="hourCost" class="control-label" />
							</div>
						</div>
					</spring:bind>
					<spring:bind path="email">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Emaila</label>
							<div class="col-sm-10">
								<form:input path="email" type="email" class="form-control "
									id="email" placeholder="Emaila" />
								<form:errors path="email" class="control-label" />
							</div>
						</div>
					</spring:bind>
					<spring:bind path="password">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Pasahitza</label>
							<div class="col-sm-10">
								<form:input path="password" type="password" class="form-control "
									id="password" placeholder="Emaila" />
								<form:errors path="password" class="control-label" />
							</div>
						</div>
					</spring:bind>
					<spring:bind path="role">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Rola</label>
							<div class="col-sm-5">
								<form:select path="role" class="form-control">
									<form:options items="${roles}" itemValue="id"
										itemLabel="name" />
								</form:select>
								<form:errors path="role" class="control-label" />
							</div>
							<div class="col-sm-5"></div>
						</div>
					</spring:bind>
					<spring:bind path="section">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Sekzioa</label>
							<div class="col-sm-5">
								<form:select path="section" class="form-control">
									<form:options items="${sections}" itemValue="id"
										itemLabel="name" />
								</form:select>
								<form:errors path="section" class="control-label" />
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