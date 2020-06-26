<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="common/header.jspf"%>
<title>Shopping Cart - My Online Shop</title>
</head>
<body class="bg-light">
	<header>
		<%@ include file="common/user-navigation.jspf"%>
	</header>
	<main>
		<div class="container-fluid">
			<div class="row justify-content-center">
				<div class="col-12">
					<h2>Your Shopping Cart</h2>

					<!-- Main Table -->
					
					<form action="./editcartitem?cartitemid=1&newquantity=1"
						method="GET">
						<div class="table-responsive">
							<table class="table w3-striped w3-bordered w3-hoverable">
								<thead>
									<tr>
										<th>ID</th>
										<th>Name</th>
										<th>Description</th>
										<th>Image Link</th>
										<th>Unit Price</th>
										<th>Quantity</th>
										<th>Actions</th>
										<th><a href="./shop?userid=${userId}">+</a></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="cartItem" items="${cartItemList}">
										<tr id="row${cartItem.id}">
											<td class="fixed">${cartItem.id}</td>
											<td class="fixed">${cartItem.product.name}</td>
											<td class="fixed">${cartItem.product.description}</td>
											<td class="fixed"><a
												href="${cartItem.product.imageLink}" target="_blank">Link</a></td>
											<td class="fixed">${cartItem.product.unitPrice}</td>
											<td class="editable">${cartItem.quantity}</td>
											<td class="delete"><a
												href="./deletecartitem?cartitemid=${cartItem.id}">Delete</a></td>
											<td class="update"><a href="#"
												onclick="rowBackup.update(${cartItem.id})">Update</a></td>
										</tr>
									</c:forEach>
									<tr id="editform">
										<!-- Hidden Form -->
										<td class="fixed"><span></span><input type="hidden"
											name="cartitemid" /></td>
										<td class="fixed"><span></span></td>
										<td class="fixed"><span></span></td>
										<td class="fixed"><span></span></td>
										<td class="fixed"><span></span></td>
										<td class="editable"><input type="number"
											name="newquantity" required="required" min="1" /></td>
										<td class="cancel"><a href="#"
											onclick="rowBackup.cancelEdit()">Cancel</a></td>
										<td class="update"><input type="submit" value="Save" /></td>
									</tr>
								</tbody>
							</table>
						</div>
					</form>

					${message}
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