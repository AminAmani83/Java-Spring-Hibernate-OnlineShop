<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="common/header.jspf"%>
<title>Shop - My Online Shop</title>
</head>
<body class="bg-light">
	<header>
		<%@ include file="common/user-navigation.jspf"%>
	</header>
	<main>
		<div class="container">
			<div class="row">
				<div class="col-12">
					<h2>Shop</h2>
				</div>
			</div>
			<div class="row">
				<c:forEach var="product" items="${productList}">
					<div class="col-12 col-md-6 col-lg-4 pb-4 text-center">
						<a href="./product?productid=${product.id}&userid=${userId}"><img class="img-fluid pb-3" src="${product.imageLink}" alt="Product Photo" /><br>ID:
							${product.id} - Name: ${product.name}</a>
					</div>
				</c:forEach>

			</div>
		</div>
	</main>
	<footer>
		<%@ include file="common/footer.jspf"%>
	</footer>
</body>
</html>
<!-- Author: Amin Amani -->