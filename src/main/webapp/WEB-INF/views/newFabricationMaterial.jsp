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
						<h1>Fabrikazio material berria</h1>
					</div>
				</div>
				<form:form class="form-horizontal" method="post"
					modelAttribute="fabricationMaterial"
					action="newFabricationMaterial.html">
					<form:hidden path="id" />
					<spring:bind path="material">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Materiala</label>
							<div class="col-sm-5">
								<form:select path="material" class="form-control">
									<form:options items="${materials}" itemValue="id"
										itemLabel="name" />
								</form:select>
								<form:errors path="material" class="control-label" />
							</div>
							<div class="col-sm-5"></div>
						</div>
					</spring:bind>
					<spring:bind path="price">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Prezioa</label>
							<div class="col-sm-10">
								<form:input path="price" type="number" class="form-control "
									id="price" placeholder="Prezioa" />
								<form:errors path="price" class="control-label" />
							</div>
						</div>
					</spring:bind>
					<spring:bind path="quantity">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label class="col-sm-2 control-label">Kopurua</label>
							<div class="col-sm-10">
								<form:input path="quantity" type="number" class="form-control "
									id="quantity" placeholder="Kopurua" />
								<form:errors path="quantity" class="control-label" />
							</div>
						</div>
					</spring:bind>
					<spring:bind path="order.id">
						<div class="form-group ${status.error ? 'has-error' : ''}" style="display: none;">
							<label class="col-sm-2 control-label">Ordena</label>
							<div class="col-sm-10">
								<form:input path="order.id" type="number" class="form-control "
									id="order" placeholder="Ordena" />
								<form:errors path="order.id" class="control-label" />
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