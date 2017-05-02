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
						<h1>Materialak</h1>
					</div>
				</div>
				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							<th>Izena</th>
							<th>Aukerak</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${materials}" var="material">
							<tr>
								<td><c:out value="${material.name}" /></td>
								<td><a type="button" class="btn btn-warning" href="editMaterial.html?id=${material.id}"
										aria-label="Edit">
										<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									</a>
									<a type="button" class="btn btn-danger delete-material" href="deleteMaterial.html?id=${material.id}"
										aria-label="Delete">
										<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
									</a></td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="2"><a class="btn btn-default"
								href="newMaterial.html">Material berria</a>
								<a target="_blank" class="btn btn-default" href="materialsReport.pdf"><span class="glyphicon glyphicon-print" aria-hidden="true"></span>Imprimatu</a>
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
	<script src="resources/js/materials.js"></script>
</body>

</html>