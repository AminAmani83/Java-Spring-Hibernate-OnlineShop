<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="common/header.jspf"%>
<title>Product Page - My Online Shop</title>
</head>
<body class="bg-light">
	<header>
		<%@ include file="common/user-navigation.jspf"%>
	</header>
	<main>
			<div class="container">
			<div class="row">
				<div class="col-12">
		<h2 class="pb-4">Product Page</h2>
		</div></div>
			<div class="row">
				<div class="col-12 col-sm-6 pb-3">
					<img class="img-fluid" src="${product.imageLink}" alt="Product Photo" />
				</div>
				<div class="col-12 col-sm-6 pb-3">
					<dl>
						<dt>ID</dt>
						<dd>${product.id}</dd>

						<dt>Name</dt>
						<dd>${product.name}</dd>

						<dt>Description</dt>
						<dd>${product.description}</dd>

						<dt>Unit Price</dt>
						<dd>${product.unitPrice}</dd>
					</dl>

					<form action="./addcartitem" method="GET">
						<input type="hidden" name="productid" id="productid"
							value="${product.id}" /> <input type="number" name="quantity"
							id="quantity" value="1" min="1" /> <input type="hidden" name="userid"
							id="userid" value="${userId}" /> <input type="submit"
							value="Add to Cart" />
					</form>
				</div>
			</div>
		</div>
	</main>
	<footer>
		<%@ include file="common/footer.jspf"%>
	</footer>
</body>
</html>
<!-- Author: Amin Amani -->