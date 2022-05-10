<<<<<<< HEAD

=======
>>>>>>> fbe679128a754f2a1eb532176cfc33d00627ba4f
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script>
const img = opener.document.getElementById("pic");
img.src = "<%=request.getContextPath()%>/imgupload/${filename}";
opener.document.f.picture.value = "${filename}";
self.close();
</script>
</body>
</html>