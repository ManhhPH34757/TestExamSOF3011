package com.example.testexam.controllers;

import com.example.testexam.entities.LopHoc;
import com.example.testexam.entities.SinhVien;
import com.example.testexam.repositories.LopHocRepo;
import com.example.testexam.repositories.SinhVienRepo;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "SinhVienServlet", value = {
        "/home-sinhVien",
        "/edit-sinhVien",
        "/delete-sinhVien",
        "/store-sinhVien",
        "/update-sinhVien",
        "/find-sinhVien"
})
public class SinhVienServlet extends HttpServlet {

    SinhVienRepo sinhVienRepo = new SinhVienRepo();
    LopHocRepo lopHocRepo = new LopHocRepo();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("home-sinhVien")){
            home(request, response);
        } else if (uri.contains("edit-sinhVien")) {
            edit(request, response);
        } else if (uri.contains("delete-sinhVien")) {
            delete(request, response);
        } else if (uri.contains("find-sinhVien")) {
            find(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("store-sinhVien")){
            store(request, response);
        } else if (uri.contains("update-sinhVien")) {
            update(request, response);
        }
    }

    protected void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listSV", sinhVienRepo.getList());
        request.setAttribute("listLop", lopHocRepo.getList());
        if (sinhVienRepo.getList().size() == 0){
            request.setAttribute("check", "Danh sách trống");
        }
        request.getRequestDispatcher("/view/home.jsp").forward(request, response);
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listSV", sinhVienRepo.getList());
        request.setAttribute("listLop", lopHocRepo.getList());
        if (valid(request, response)){
            String hoTen = request.getParameter("hoTen");
            int idLop = Integer.parseInt(request.getParameter("idLop"));
            LopHoc lopHoc = lopHocRepo.findById(idLop);
            String diaChi = request.getParameter("diaChi");
            String trangThai = request.getParameter("trangThai");
            sinhVienRepo.insert(new SinhVien(hoTen, idLop, diaChi, trangThai, lopHoc));
            response.sendRedirect("/home-sinhVien");
        }else {
            home(request, response);
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        sinhVienRepo.delete(id);
        if (sinhVienRepo.getList().size() == 0){
            request.setAttribute("check", "Danh sách trống");
        }
        response.sendRedirect("/home-sinhVien");
    }

    protected void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        request.setAttribute("listSV", sinhVienRepo.find(name));
        request.setAttribute("listLop", lopHocRepo.getList());
        request.getRequestDispatcher("/view/home.jsp").forward(request, response);
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listLop", lopHocRepo.getList());
        int id = Integer.parseInt(request.getParameter("id"));
        SinhVien sinhVien = sinhVienRepo.findById(id);
        request.setAttribute("sv", sinhVien);
        request.getRequestDispatcher("/view/edit.jsp").forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        SinhVien sinhVien = sinhVienRepo.findById(id);
        String hoTen = request.getParameter("hoTen");
        int idLop = Integer.parseInt(request.getParameter("idLop"));
        LopHoc lopHoc = lopHocRepo.findById(idLop);
        String diaChi = request.getParameter("diaChi");
        String trangThai = request.getParameter("trangThai");
        sinhVien.setHoTen(hoTen);
        sinhVien.setDiaChi(diaChi);
        sinhVien.setIdLop(idLop);
        sinhVien.setLopHocByIdLop(lopHoc);
        sinhVien.setTrangThai(trangThai);
        sinhVienRepo.update(sinhVien);
        response.sendRedirect("/home-sinhVien");
    }

    protected boolean valid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean check = true;
        String hoTen = request.getParameter("hoTen");
        if (hoTen == null || hoTen.trim().isEmpty()){
            request.setAttribute("validHoTen", "Vui lòng nhập họ tên");
            check = false;
        }else {
            request.setAttribute("validHoTen", "");
            request.setAttribute("hoTen", hoTen);
        }
        try {
            int idLop = Integer.parseInt(request.getParameter("idLop"));
            request.setAttribute("validLop", "");
            request.setAttribute("idLop", idLop);
        } catch (Exception e){
            request.setAttribute("validLop", "Vui lòng chọn lớp");
            check = false;
        }

        String diaChi = request.getParameter("diaChi");
        if (diaChi == null || diaChi.trim().isEmpty()){
            request.setAttribute("validDiaChi", "Vui lòng nhập địa chỉ");
            check = false;
        }else {
            request.setAttribute("validDiaChi", "");
            request.setAttribute("diaChi", diaChi);
        }
        String trangThai = request.getParameter("trangThai");
        if (trangThai == null || trangThai.trim().isEmpty()){
            request.setAttribute("validTrangThai", "Vui lòng chọn trạng thái");
            check = false;
        }else {
            request.setAttribute("validTrangThai", "");
            request.setAttribute("trangThai", trangThai);
        }

        return check;
    }

    }
