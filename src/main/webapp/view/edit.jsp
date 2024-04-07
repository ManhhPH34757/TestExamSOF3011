<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 4/2/2024
  Time: 12:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<html>
<head>
    <title>Edit</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

</head>
<body>
<form action="update-sinhVien?id=${sv.id}" method="post">
  <div>
    <label>Họ tên</label>
    <input type="text" name="hoTen" value="${sv.hoTen}" class="form-control">
  </div>
  <div>
    <label>Lớp</label>
    <select class="form-control" name="idLop">
      <option value="" hidden>--Chọn lớp</option>
      <c:forEach items="${listLop}" var="lop">
        <option value="${lop.id}"
        <c:if test="${sv.lopHocByIdLop.id == lop.id}"> selected </c:if>
        >${lop.tenLop}</option>
      </c:forEach>
    </select>
  </div>
  <div>
    <label>Địa chỉ</label>
    <input type="text" name="diaChi" value="${sv.diaChi}" class="form-control">
  </div>
  <div>
    <label>Trạng thái</label>
    <input type="radio" name="trangThai" value="Active" id="Active"
    <c:if test="${sv.trangThai == 'Active'}"> checked </c:if>
    > <label for="Active">Active</label>
    <input type="radio" name="trangThai" value="InActive" id="InActive"
    <c:if test="${sv.trangThai == 'InActive'}"> checked </c:if>
    > <label for="InActive">InActive</label>
  </div>
  <button class="btn btn-primary" type="submit">Update</button>
</form>
</body>
</html>
