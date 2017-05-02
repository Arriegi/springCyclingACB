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
						<h1><sec:authorize  access="hasAuthority('ADMIN')"><c:out value="${part.worker.fullName}"></c:out> / </sec:authorize><fmt:formatDate pattern="dd-MM-yyyy" value="${part.date}" /></h1>
					</div>
				</div>
				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							<th>Ordena</th>
							<th>Hasiera</th>
							<th>Bukaera</th>
							<th>Eragiketa</th>
							<%@ include file="/WEB-INF/views/main/options.jsp"%>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${part.operations}" var="operation">
							<tr>
								<td><c:out value="${operation.value.order.name}" /></td>
								<td><fmt:formatDate pattern="HH:mm" value="${operation.value.startTime}" /></td>
								<td><fmt:formatDate pattern="HH:mm" value="${operation.value.endTime}" /></td>
								<td><c:out value="${operation.value.operation.name}" /></td>
								<sec:authorize access="hasAuthority('ADMIN')">
									<jsp:include page="/WEB-INF/views/main/optionButtons.jsp">
										<jsp:param name="id" value="${operation.value.id}"/>
										<jsp:param name="objectName" value="WorkOperation"/>
									</jsp:include>
								</sec:authorize>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="5"><a class="btn btn-default"
								href="newWorkOperation.html?partId=${part.id}">Eragiketa berria</a>
								<a target="_blank" class="btn btn-default" href="workOperationsReport.html"><span class="glyphicon glyphicon-print" aria-hidden="true"></span>Imprimatu</a>
								</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- /.container-fluid -->

		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->
	<%@ include file="/WEB-INF/views/confirm_modal.jsp"%>
	<script src="resources/js/workOperations.js"></script>
</body>

</html>