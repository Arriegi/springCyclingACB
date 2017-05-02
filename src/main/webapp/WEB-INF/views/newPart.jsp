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
						<h1>Parte berria </h1>
					</div>
				</div>
				<form:form class="form-horizontal" method="post"
					modelAttribute="part" action="newPart.html">
					<form:hidden path="id" />
					<spring:bind path="date">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Data</label>
							<div class="col-sm-10">
								<fmt:formatDate value="${part.date}" type="date"
									pattern="yyyy-MM-dd" var="formatDate" />
								<form:input path="date" type="date" class="form-control "
									id="date" placeholder="Data" value="${formatDate}" />
								<form:errors path="date" class="control-label" />
							</div>
						</div>
					</spring:bind>
					<spring:bind path="worker">
						<div class="<c:out value="${ part.worker.role.id != 1 ? 'hide' : 'show'}" /> form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Langilea</label>
							<div class="col-sm-5">
								<form:select path="worker" class="form-control">
									<form:options items="${workers}" itemValue="id"
										itemLabel="fullName" />
								</form:select>
								<form:errors path="worker" class="control-label" />
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