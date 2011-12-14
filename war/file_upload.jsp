<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.Date"%>
<html>
<head>
<title>Google File Upload</title>
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
</head>
<body>
	<%Date date = new Date();%>
	<form action="/upload" method="post" enctype="multipart/form-data">
		<input type="file" name="upfile" size="50"/></br>
		<input type="submit" name="submit" value="Upload"/>
	</form>
</body>
</html>