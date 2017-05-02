<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
<div class="collapse navbar-collapse navbar-ex1-collapse left-menu">
	<ul class="nav navbar-nav side-nav">
		<li><a href="parts.html"><i class="fa fa-fw fa-dashboard"></i>Parteak</a></li>
		<li><a href="sections.html"><i
				class="fa fa-fw fa-bar-chart-o"></i> Sekzioak</a></li>
		<li><a href="fabricationOrders.html"><i
				class="fa fa-fw fa-bar-chart-o"></i> Fabrikazio aginduak</a></li>
		<li><a href="operations.html"><i
				class="fa fa-fw fa-bar-chart-o"></i> Operazioak</a></li>
		<li><a href="materials.html"><i
				class="fa fa-fw fa-bar-chart-o"></i> Materialak</a></li>
		<sec:authorize access="hasAuthority('ADMIN')">
		<li><a href="workers.html"><i class="fa fa-fw fa-bar-chart-o"></i>
				Langileak</a></li>
		</sec:authorize>
		<li><a href="operationTypes.html"><i
				class="fa fa-fw fa-bar-chart-o"></i> Operazio motak</a></li>
		<li><a href="clients.html"><i class="fa fa-fw fa-dashboard"></i>Bezeroak</a></li>
	</ul>
</div>