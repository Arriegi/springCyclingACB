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
						<h1><c:out value="${profile.fullName}"/></h1>
					</div>
				</div>
				<form:form class="form-horizontal" method="post"
					modelAttribute="profile" action="editProfile.html">
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