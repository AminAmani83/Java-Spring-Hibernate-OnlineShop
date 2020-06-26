<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="common/header.jspf"%>
<title>List of Users - My Online Shop</title>
</head>
<body class="bg-light">
	<header>
		<%@ include file="common/admin-navigation.jspf"%>
	</header>
	<main>
		<div class="container-fluid">
			<div class="row justify-content-center">
				<div class="col-12">
					<h2>List of Users</h2>

					<!--  Add New User Table -->
					<div id="add-item">
						<!-- Hidden By Default -->
						<h4>Add a new user</h4>
						<form:form action="./adduser" method="post"
							modelAttribute="myAddedUser">
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr>
											<th>Username</th>
											<th>Password</th>
											<th>Email</th>
											<th>First Name</th>
											<th>Middle Name</th>
											<th>Last Name</th>
											<th>Gender</th>
											<th>Phone</th>
											<th>Addresses</th>
											<th>Actions</th>
											<th>&nbsp;</th>
										</tr>
									</thead>
									<tbody>
										<tr id="row0">
											<td><form:input path="username" id="new-username"
													required="required" maxlength="100" /></td>
											<td><form:input path="password" id="new-password"
													required="required" maxlength="100" /></td>
											<td><form:input path="email" id="new-email"
													required="required" pattern="[^@]+@[^\.]+\..+" maxlength="100" /></td>
											<td><form:input path="firstName" id="new-firstName"
													required="required" maxlength="100" /></td>
											<td><form:input path="middleName" id="new-middleName" maxlength="100" /></td>
											<td><form:input path="lastName" id="new-lastName"
													required="required" maxlength="100" /></td>
											<td><select name="gender" id="new-gender">
													<c:forEach var="gender" items="${genderList}">
														<option value="${gender}">${gender}</option>
													</c:forEach>
											</select></td>
											<td><form:input path="phone" id="new-phone" maxlength="11" /></td>
											<td>&nbsp;</td>
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
					<form:form action="./edituser" method="post"
						modelAttribute="myEditedUser">
						<div class="table-responsive">
							<table class="table w3-striped w3-bordered w3-hoverable">
								<thead>
									<tr>
										<th>ID</th>
										<th>Username</th>
										<th>Password</th>
										<th>Email</th>
										<th>First Name</th>
										<th>Middle Name</th>
										<th>Last Name</th>
										<th>Gender</th>
										<th>Phone</th>
										<th>Addresses</th>
										<th>Actions</th>
										<th><a id="show-add-item" href="#">+</a></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="user" items="${userList}">
										<tr id="row${user.id}">
											<td class="fixed">${user.id}</td>
											<td class="editable">${user.username}</td>
											<td class="editable">${user.password}</td>
											<td class="editable">${user.email}</td>
											<td class="editable">${user.firstName}</td>
											<td class="editable">${user.middleName}</td>
											<td class="editable">${user.lastName}</td>
											<td class="selectbox">${user.gender}</td>
											<td class="editable">${user.phone}</td>
											<td class="fixed"><a
												href="./useraddresses?userid=${user.id}">View</a></td>
											<td class="delete"><a href="./deleteuser?id=${user.id}">Delete</a></td>
											<td class="update"><a href="#"
												onclick="rowBackup.update(${user.id})">Update</a></td>
										</tr>
									</c:forEach>
									<tr id="editform">
										<!-- Hidden Form -->
										<td class="fixed"><span></span> <form:hidden path="id" /></td>
										<td class="editable"><form:input path="username"
												required="required" maxlength="100" /></td>
										<td class="editable"><form:input path="password"
												required="required" maxlength="100" /></td>
										<td class="editable"><form:input path="email"
												required="required" maxlength="100" pattern="[^@]+@[^\.]+\..+" /></td>
										<td class="editable"><form:input path="firstName"
												required="required" maxlength="100" /></td>
										<td class="editable"><form:input path="middleName" maxlength="100" /></td>
										<td class="editable"><form:input path="lastName"
												required="required" maxlength="100" /></td>
										<td class="selectbox"><select name="gender">
												<c:forEach var="gender" items="${genderList}">
													<option value="${gender}">${gender}</option>
												</c:forEach>
										</select></td>
										<td class="editable"><form:input path="phone" maxlength="11" /></td>
										<td class="fixed"><span></span></td>
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