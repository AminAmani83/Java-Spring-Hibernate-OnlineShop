<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="common/header.jspf"%>
<title>Shop - My Online Shop</title>
</head>
<body class="bg-light">
	<header>
		<div id="logo">My Online Shop</div>
		<nav>
			<ul>

			</ul>
		</nav>
	</header>
	<main>
		<div class="container-fluid">
			<div class="row justify-content-center">
				<div class="col-12">
					<h2>Please Choose your identity</h2>
					<div>
						<h3>Admin</h3>
						<a href="./admin/users">Administrator</a> <br>
						<br>
						<h3>User</h3>
						<c:forEach var="user" items="${userList}">
							<a href="./shop?userid=${user.id}">ID: ${user.id} - UserName:
								${user.username}</a>
							<br>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</main>
	<footer>
		<%@ include file="common/footer.jspf"%>
	</footer>
</body>
</html>