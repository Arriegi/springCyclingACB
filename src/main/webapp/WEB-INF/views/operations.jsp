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
						<h1>Operazioak</h1>
					</div>
				</div>
				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							<th>Izena</th>
							<th>Mota</th>
							<%@ include file="/WEB-INF/views/main/options.jsp"%>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${operations}" var="operation">
							<tr>
								<td><c:out value="${operation.name}" /></td>
								<td><c:out value="${operation.operationType.name}" /></td>
								<sec:authorize access="hasAuthority('ADMIN')">
									<jsp:include page="/WEB-INF/views/main/optionButtons.jsp">
										<jsp:param name="id" value="${operation.id}"/>
										<jsp:param name="objectName" value="Operation"/>
									</jsp:include>
								</sec:authorize>
							</tr>
						</c:forEach>
						<sec:authorize access="hasAuthority('ADMIN')">
						<tr>
							<td colspan="3"><a class="btn btn-default"
								href="newOperation.html">Operazio berria</a>
								<a target="_blank" class="btn btn-default" href="operationsReport.pdf"><span class="glyphicon glyphicon-print" aria-hidden="true"></span>Imprimatu</a>
								</td>
						</tr>
						</sec:authorize>
					</tbody>
				</table>
			</div>
			<!-- /.container-fluid -->

		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->
	<%@ include file="/WEB-INF/views/confirm_modal.jsp"%>
	<script src="resources/js/operations.js"></script>
</body>

</html>