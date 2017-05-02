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
						<h1>Langileak</h1>
					</div>
				</div>
				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							<th>Izena</th>
							<th>1. abizena</th>
							<th>2. abizena</th>
							<th>koste/orduko</th>
							<th>Emaila</th>
							<th>Rola</th>
							<th>Sekzioa</th>
							<th>Aukerak</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${workers}" var="worker">
							<tr>
								<td><c:out value="${worker.name}" /></td>
								<td><c:out value="${worker.lastName1}" /></td>
								<td><c:out value="${worker.lastName2}" /></td>
								<td><c:out value="${worker.hourCost}" /></td>
								<td><c:out value="${worker.email}" /></td>
								<td><c:out value="${worker.role.name}" /></td>
								<td><c:out value="${worker.section.name}" /></td>
								<td><a type="button" class="btn btn-warning" href="editWorker.html?id=${worker.id}"
										aria-label="Edit">
										<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									</a>
									<a type="button" class="btn btn-danger delete-worker" href="deleteWorker.html?id=${worker.id}"
										aria-label="Delete">
										<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
									</a></td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="8"><a class="btn btn-default"
								href="newWorker.html">Langile berria</a>
								<a target="_blank" class="btn btn-default" href="workersReport.pdf"><span class="glyphicon glyphicon-print" aria-hidden="true"></span>Imprimatu</a>
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
	<script src="resources/js/workers.js"></script>
</body>

</html>