<ul class="nav navbar-right top-nav">
	<li class="dropdown"><a href="#" class="dropdown-toggle"
		data-toggle="dropdown" aria-expanded="false"><i class="fa fa-user"></i>
			<c:out value="${loggedWorker.fullName}"></c:out> <b class="caret"></b></a>
		<ul class="dropdown-menu">
			<li><a href="profile.html"><i class="fa fa-fw fa-user"></i> Profila</a></li>
			<li class="divider"></li>
			<li><c:url var="logoutUrl" value="/j_spring_security_logout" />
				<form action="${logoutUrl}" id="logout" method="post">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form> <a href="#" onclick="document.getElementById('logout').submit();">Irten</a></li>
		</ul></li>
</ul>