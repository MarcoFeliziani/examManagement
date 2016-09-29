<!DOCTYPE html>
<html>
<head>
	<title>Login</title>
	<link rel="stylesheet" href="/styles/normalize.css">
	<link rel="stylesheet" href="/styles/style.css">
</head>
<body>
	<section class="loginform cf">
		<form name="login" action="LoginServlet" method="post" accept-charset="utf-8">
			<ul>
				<li>
					<label for="user">Username</label>
					<input type="text" name="user" id="user" placeholder="username" required>
				</li>
				<li>
					<label for="password">Password</label>
					<input type="password" id="pwd" name="pwd" placeholder="password" required>
				</li>
				<li>
					<input type="submit" value="Login">
				</li>
			</ul>
		</form>
	</section>
</body>
</html>
