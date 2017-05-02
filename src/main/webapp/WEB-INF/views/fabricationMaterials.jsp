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
						<h1><c:out value="${order.name}"></c:out></h1>
					</div>
				</div>
				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							<th>Materiala</th>
							<th>Prezioa</th>
							<th>Kopurua</th>
							<%@ include file="/WEB-INF/views/main/options.jsp"%>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${order.materials}" var="fabricationMaterial">
							<tr>
								<td><c:out value="${fabricationMaterial.material.name}" /></td>
								<td><c:out value="${fabricationMaterial.price}" /></td>
								<td><c:out value="${fabricationMaterial.quantity}" /></td>
								<sec:authorize access="hasAuthority('ADMIN')">
									<jsp:include page="/WEB-INF/views/main/optionButtons.jsp">
										<jsp:param name="id" value="${fabricationMaterial.id}"/>
										<jsp:param name="objectName" value="FabricationMaterial"/>
									</jsp:include>
								</sec:authorize>
							</tr>
						</c:forEach>
						<sec:authorize access="hasAuthority('ADMIN')">
						<tr>
							<td colspan="4"><a class="btn btn-default"
								href="newFabricationMaterial.html?orderId=${order.id}">Fabrikazio material berria</a></td>
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
	<script src="resources/js/fabricationMaterials.js"></script>
</body>

</html>