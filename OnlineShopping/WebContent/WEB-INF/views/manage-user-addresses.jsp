<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="common/header.jspf"%>
<title>List of User Addresses - My Online Shop</title>
</head>
<body class="bg-light">
	<header>
		<%@ include file="common/admin-navigation.jspf"%>
	</header>
	<main>
		<div class="container-fluid">
			<div class="row justify-content-center">
				<div class="col-12">
					<h2>List of Addresses for User Id: ${userId}</h2>

					<!--  Add New Address Table -->
					<div id="add-item">
						<!-- Hidden By Default -->
						<h4>Add a new address</h4>
						<form:form action="./adduseraddress?userid=${userId}"
							method="post" modelAttribute="myAddedAddress">
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr>
											<th>Address Type</th>
											<th>Address Line 1</th>
											<th>Address Line 2</th>
											<th>City</th>
											<th>Province</th>
											<th>Country</th>
											<th>Postal Code</th>
											<th>Actions</th>
											<th>&nbsp;</th>
										</tr>
									</thead>
									<tbody>
										<tr id="row0">
											<td><select name="addressType" id="new-addressType">
													<c:forEach var="addressType" items="${addressTypeList}">
														<option value="${addressType}">${addressType}</option>
													</c:forEach>
											</select></td>
											<td><form:input path="addressLine1"
													id="new-addressLine1" required="required" maxlength="250" /></td>
											<td><form:input path="addressLine2"
													id="new-addressLine2" maxlength="250" /></td>
											<td><form:input path="city" id="new-city"
													required="required" maxlength="100" /></td>
											<td><form:input path="province" id="new-province"
													required="required" maxlength="100" /></td>
											<td><form:input path="country" id="new-country"
													required="required" maxlength="100" /></td>
											<td><form:input path="postalCode" id="new-postalCode"
													required="required" maxlength="20" /></td>
											<td><input type="reset" value="Cancel"
												id="hide-add-item" /></td>
											<td><input type="submit" value="Save" /></td>
										</tr>
									</tbody>
								</table>
							</div>
						</form:form>
					</div>

					<!-- Main Table -->

					<form:form action="./edituseraddress?userid=${userId}"
						method="post" modelAttribute="myEditedAddress">
						<div class="table-responsive">
							<table class="table w3-striped w3-bordered w3-hoverable">
								<thead>
									<tr>
										<th>ID</th>
										<th>Address Type</th>
										<th>Address Line 1</th>
										<th>Address Line 2</th>
										<th>City</th>
										<th>Province</th>
										<th>Country</th>
										<th>Postal Code</th>
										<th>Actions</th>
										<th><a id="show-add-item" href="#">+</a></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="address" items="${addressList}">
										<tr id="row${address.id}">
											<td class="fixed">${address.id}</td>
											<td class="selectbox">${address.addressType}</td>
											<td class="editable">${address.addressLine1}</td>
											<td class="editable">${address.addressLine2}</td>
											<td class="editable">${address.city}</td>
											<td class="editable">${address.province}</td>
											<td class="editable">${address.country}</td>
											<td class="editable">${address.postalCode}</td>
											<td class="delete"><a
												href="./deleteuseraddress?userid=${userId}&addressid=${address.id}">Delete</a></td>
											<td class="update"><a href="#"
												onclick="rowBackup.update(${address.id})">Update</a></td>
										</tr>
									</c:forEach>
									<tr id="editform">
										<!-- Hidden Form -->
										<td class="fixed"><span></span> <form:hidden path="id" /></td>
										<td class="selectbox"><select name="addressType">
												<c:forEach var="addressType" items="${addressTypeList}">
													<option value="${addressType}">${addressType}</option>
												</c:forEach>
										</select></td>
										<td class="editable"><form:input path="addressLine1"
												required="required" maxlength="250" /></td>
										<td class="editable"><form:input path="addressLine2" maxlength="250" /></td>
										<td class="editable"><form:input path="city"
												required="required" maxlength="100" /></td>
										<td class="editable"><form:input path="province"
												required="required" maxlength="100" /></td>
										<td class="editable"><form:input path="country"
												required="required" maxlength="100" /></td>
										<td class="editable"><form:input path="postalCode"
												required="required" maxlength="20" /></td>
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
