<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>
</head>
<body>
	<form:form commandName="uploadForm" method="post" action="upload"
		enctype="multipart/form-data">
		<div>
			<label>File Location:</label>
			<form:input name="file" path="file" type="file"
				style="width: 350px;display: inline-block;" autocomplete="off" />
			<span><form:errors path="file" cssClass="error" /></span>
		</div>
		<div>
			<label></label> <input id="uploadBtnId" name="uploadBtnId"
				type="submit" value="Upload" />
		</div>
	</form:form>
</body>
</html>
