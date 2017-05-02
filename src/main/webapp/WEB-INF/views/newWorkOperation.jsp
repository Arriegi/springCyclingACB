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
						<h1>Lan berria</h1>
					</div>
				</div>
				<form:form class="form-horizontal" method="post"
					modelAttribute="workOperation"
					action="newWorkOperation.html">
					<form:hidden path="id" />
					<spring:bind path="startTime">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Hasiera</label>
							<div class="col-sm-10">
								<fmt:formatDate value="${operation.date}" type="startTime"
									pattern="HH:mm" var="formatStartTime" />
								<form:input path="startTime" type="time" class="form-control "
									id="startTime" placeholder="Hasiera" value="${formatStartTime}" />
								<form:errors path="startTime" class="control-label" />
							</div>
						</div>
					</spring:bind>
					<spring:bind path="endTime">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Bukaera</label>
							<div class="col-sm-10">
								<fmt:formatDate value="${operation.date}" type="endTime"
									pattern="HH:mm" var="formatEndTime" />
								<form:input path="endTime" type="time" class="form-control "
									id="endTime" placeholder="Bukaera" value="${formatEndTime}" />
								<form:errors path="endTime" class="control-label" />
							</div>
						</div>
					</spring:bind>
					<spring:bind path="operation">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Eragiketa</label>
							<div class="col-sm-5">
								<form:select path="operation" class="form-control">
									<form:options items="${operations}" itemValue="id"
										itemLabel="name" />
								</form:select>
								<form:errors path="operation" class="control-label" />
							</div>
							<div class="col-sm-5"></div>
						</div>
					</spring:bind>
					<spring:bind path="order">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Ordenak</label>
							<div class="col-sm-5">
								<form:select path="order" class="form-control">
									<form:options items="${orders}" itemValue="id"
										itemLabel="name" />
								</form:select>
								<form:errors path="order" class="control-label" />
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