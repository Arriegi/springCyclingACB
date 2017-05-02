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
						<h1>Parteak</h1>
					</div>
				</div>
				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							<sec:authorize  access="hasAuthority('ADMIN')">
							<th>Langilea</th>
							</sec:authorize>
							<th>Data</th>
							<th>Lanak</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${parts}" var="part">
							<tr>
								<sec:authorize  access="hasAuthority('ADMIN')">
								<td><c:out value="${part.worker.name}" /></td>
								</sec:authorize>
								<td><fmt:formatDate pattern="yyyy-MM-dd" value="${part.date}" /></td>
								<td><a class="btn btn-default" href="seeOperations.html?id=${part.id}">Lanak</a></td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="3"><a class="btn btn-default"
								href="newPart.html">Parte berria</a></td>
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
	<script src="resources/js/parts.js"></script>
</body>

</html>