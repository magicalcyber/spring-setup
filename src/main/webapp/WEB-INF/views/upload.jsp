<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>
</head>
<body>
<head>
<title>Upload a file please</title>
</head>
<body>
	<h1>Please upload a file</h1>
	<form method="post" action="upload" enctype="multipart/form-data">
		<input type="file" id="file" name="file" /> <input type="submit" />
	</form>
</body>
</html>
