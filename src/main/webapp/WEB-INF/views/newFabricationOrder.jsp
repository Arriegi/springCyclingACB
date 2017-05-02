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
						<h1>Fabrikazio agindu berria</h1>
					</div>
				</div>
				<form:form class="form-horizontal" method="post"
					modelAttribute="fabricationOrder" action="newFabricationOrder.html">
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
					<spring:bind path="client">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Bezeroa</label>
							<div class="col-sm-5">
								<form:select path="client" class="form-control">
									<form:options items="${clients}" itemValue="id"
										itemLabel="name" />
								</form:select>
								<form:errors path="client" class="control-label" />
							</div>
							<div class="col-sm-5"></div>
						</div>
					</spring:bind>
					<spring:bind path="date">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Hasiera data</label>
							<div class="col-sm-10">
								<fmt:formatDate value="${fabricationOrder.date}" type="date"
									pattern="yyyy-MM-dd" var="formatDate" />
								<form:input path="date" type="date" class="form-control "
									id="date" placeholder="Hasiera data" value="${formatDate}" />
								<form:errors path="date" class="control-label" />
							</div>
						</div>
					</spring:bind>
					<spring:bind path="timeLimit">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Muga data</label>
							<div class="col-sm-10">
								<fmt:formatDate value="${fabricationOrder.timeLimit}" type="date"
									pattern="yyyy-MM-dd" var="formatTimeLimit" />
								<form:input path="timeLimit" type="date" class="form-control "
									id="timeLimit" placeholder="Muga data" value="${formatTimeLimit}" />
								<form:errors path="timeLimit" class="control-label" />
							</div>
						</div>
					</spring:bind>
					<spring:bind path="closeDate">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Itxiera data</label>
							<div class="col-sm-10">
								<fmt:formatDate value="${fabricationOrder.closeDate}" type="date"
									pattern="yyyy-MM-dd" var="formatCloseDate" />
								<form:input path="closeDate" type="date" class="form-control "
									id="closeDate" placeholder="Data" value="${formatCloseDate}" />
								<form:errors path="closeDate" class="control-label" />
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