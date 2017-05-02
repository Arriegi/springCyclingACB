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
						<h1>Fabrikazio aginduak</h1>
					</div>
				</div>
				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							<th>Izena</th>
							<th>Bezeroa</th>
							<th>Sarrera data</th>
							<th>Muga data</th>
							<th>Itxiera data</th>
							<th>Materialak</th>
							<%@ include file="/WEB-INF/views/main/options.jsp"%>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${fabricationOrders}" var="fabricationOrder">
							<tr>
								<td><c:out value="${fabricationOrder.name}" /></td>
								<td><c:out value="${fabricationOrder.client.name}" /></td>
								<td><fmt:formatDate pattern="dd-MM-yyyy" value="${fabricationOrder.date}" /></td>
								<td><fmt:formatDate pattern="dd-MM-yyyy" value="${fabricationOrder.timeLimit}" /></td>
								<td><fmt:formatDate pattern="dd-MM-yyyy" value="${fabricationOrder.closeDate}" /></td>
								<td><a class="btn btn-default" href="seeMaterials.html?id=${fabricationOrder.id}">Materialak</a></td>
								<sec:authorize access="hasAuthority('ADMIN')">
									<jsp:include page="/WEB-INF/views/main/optionButtons.jsp">
										<jsp:param name="id" value="${fabricationOrder.id}"/>
										<jsp:param name="objectName" value="FabricationOrder"/>
									</jsp:include>
								</sec:authorize>
							</tr>
						</c:forEach>
						<sec:authorize access="hasAuthority('ADMIN')">
						<tr>
							<td colspan="7"><a class="btn btn-default"
								href="newFabricationOrder.html">Fabrikazio agindu berria</a>
								<a target="_blank" class="btn btn-default" href="ordersReport.pdf"><span class="glyphicon glyphicon-print" aria-hidden="true"></span>Imprimatu</a>
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
	<script src="resources/js/fabricationOrders.js"></script>
</body>

</html>