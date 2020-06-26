<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="common/header.jspf"%>
<title>List of Products - My Online Shop</title>
</head>
<body class="bg-light">
	<header>
		<%@ include file="common/admin-navigation.jspf"%>
	</header>
	<main>
		<div class="container-fluid">
			<div class="row justify-content-center">
				<div class="col-12">
					<h2>List of Products</h2>

					<!--  Add New User Table -->
					<div id="add-item">
						<!-- Hidden By Default -->
						<h4>Add a new product</h4>
						<form:form action="./addproduct" method="post"
							modelAttribute="myAddedProduct">
							<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th>Name</th>
										<th>Description</th>
										<th>Image Link</th>
										<th>Unit Price</th>
										<th>Actions</th>
										<th>&nbsp;</th>
									</tr>
								</thead>
								<tbody>
									<tr id="row0">
										<td><form:input path="name" id="new-name"
												required="required" maxlength="250" /></td>
										<td><form:input path="description" id="new-description" maxlength="10000" /></td>
										<td><form:input path="imageLink" id="new-imageLink"
												required="required" maxlength="250" /></td>
										<td><form:input path="unitPrice" id="new-unitPrice"
												required="required" pattern="^\d+(\.\d{1,2})?$" /></td>
										<td><input type="reset" value="Cancel" id="hide-add-item" /></td>
										<td><input type="submit" value="Save" /></td>
									</tr>
								</tbody>
							</table>
							</div>
						</form:form>
					</div>

					<!-- Main Table -->
					<form:form action="./editproduct" method="post"
						modelAttribute="myEditedProduct">
						<div class="table-responsive">
						<table
							class="table w3-striped w3-bordered w3-hoverable">
							<thead>
								<tr>
									<th>ID</th>
									<th>Name</th>
									<th>Description</th>
									<th>Image Link</th>
									<th>Unit Price</th>
									<th>Actions</th>
									<th><a id="show-add-item" href="#">+</a></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="product" items="${productList}">
									<tr id="row${product.id}">
										<td class="fixed">${product.id}</td>
										<td class="editable">${product.name}</td>
										<td class="editable">${product.description}</td>
										<td class="editable"><a href="${product.imageLink}" target="_blank">${product.imageLink}</a></td>
										<td class="editable">${product.unitPrice}</td>
										<td class="delete"><a
											href="./deleteproduct?id=${product.id}">Delete</a></td>
										<td class="update"><a href="#"
											onclick="rowBackup.update(${product.id})">Update</a></td>
									</tr>
								</c:forEach>
								<tr id="editform">
									<!-- Hidden Form -->
									<td class="fixed"><span></span> <form:hidden path="id" /></td>
									<td class="editable"><form:input path="name" maxlength="250" /></td>
									<td class="editable"><form:input path="description" maxlength="10000" /></td>
									<td class="editable"><form:input path="imageLink" maxlength="250" /></td>
									<td class="editable"><form:input path="unitPrice" pattern="^\d+(\.\d{1,2})?$" /></td>
									<td class="cancel"><a href="#"
										onclick="rowBackup.cancelEdit()">Cancel</a></td>
									<td class="update"><input type="submit" value="Save" /></td>
								</tr>
							</tbody>
						</table>
						</div>
					</form:form>

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