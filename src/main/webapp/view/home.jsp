<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 4/2/2024
  Time: 12:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

</head>
<body>
<div class="container">
    <h2 class="text-center">Thêm sinh viên</h2>
    <form action="find-sinhVien" method="get">
        <div>
            <label>Họ tên</label>
            <input type="text" name="name" class="form-control">
        </div>
        <button type="submit" class="btn btn-success">Find</button>
    </form>
    <form action="store-sinhVien" method="post">
        <div>
            <label>Họ tên</label>
            <input type="text" name="hoTen" value="${hoTen}" class="form-control">
            <div class="text-danger">${validHoTen}</div>
        </div>
        <div>
            <label>Lớp</label>
            <select class="form-control" name="idLop">
                <option value="" hidden>--Chọn lớp</option>
                <c:forEach items="${listLop}" var="lop">
                    <option value="${lop.id}"
                        <c:if test="${idLop == lop.id}"> selected </c:if>
                    >${lop.tenLop}</option>
                </c:forEach>
            </select>
            <div class="text-danger">${validLop}</div>
        </div>
        <div>
            <label>Địa chỉ</label>
            <input type="text" name="diaChi" value="${diaChi}" class="form-control">
            <div class="text-danger">${validDiaChi}</div>
        </div>
        <div>
            <label>Trạng thái</label>
            <input type="radio" name="trangThai" value="Active" id="Active"
            <c:if test="${trangThai == 'Active'}"> checked </c:if>> <label for="Active">Active</label>
            <input type="radio" name="trangThai" value="InActive" id="InActive"
            <c:if test="${trangThai == 'InActive'}"> checked </c:if>> <label for="InActive">InActive</label>
            <div class="text-danger">${validTrangThai}</div>
        </div>
        <button class="btn btn-primary" type="submit">Add</button>
    </form>
    <div class="text-danger">${check}</div>
    <table class="table">
        <tr>
            <th>STT</th>
            <th>Họ tên</th>
            <th>Lớp</th>
            <th>Địa chỉ</th>
            <th>Trạng thái</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${listSV}" var="sv" varStatus="i">
            <tr>
                <td>${i.index + 1}</td>
                <td>${sv.hoTen}</td>
                <td>${sv.lopHocByIdLop.tenLop}</td>
                <td>${sv.diaChi}</td>
                <td>${sv.trangThai}</td>
                <td>
                    <a href="edit-sinhVien?id=${sv.id}" class="btn btn-warning">Edit</a>
                    <a href="delete-sinhVien?id=${sv.id}" class="btn btn-danger">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
