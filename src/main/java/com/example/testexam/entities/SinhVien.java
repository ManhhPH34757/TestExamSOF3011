package com.example.testexam.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sinh_vien", schema = "dbo", catalog = "DB1")
public class SinhVien {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "ho_ten")
    private String hoTen;
    @Basic
    @Column(name = "id_lop")
    private Integer idLop;
    @Basic
    @Column(name = "dia_chi")
    private String diaChi;
    @Basic
    @Column(name = "trang_thai")
    private String trangThai;
    @ManyToOne
    @JoinColumn(name = "id_lop", referencedColumnName = "id", insertable=false, updatable=false)
    private LopHoc lopHocByIdLop;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Integer getIdLop() {
        return idLop;
    }

    public void setIdLop(Integer idLop) {
        this.idLop = idLop;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SinhVien sinhVien = (SinhVien) o;

        if (id != sinhVien.id) return false;
        if (hoTen != null ? !hoTen.equals(sinhVien.hoTen) : sinhVien.hoTen != null) return false;
        if (idLop != null ? !idLop.equals(sinhVien.idLop) : sinhVien.idLop != null) return false;
        if (diaChi != null ? !diaChi.equals(sinhVien.diaChi) : sinhVien.diaChi != null) return false;
        if (trangThai != null ? !trangThai.equals(sinhVien.trangThai) : sinhVien.trangThai != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (hoTen != null ? hoTen.hashCode() : 0);
        result = 31 * result + (idLop != null ? idLop.hashCode() : 0);
        result = 31 * result + (diaChi != null ? diaChi.hashCode() : 0);
        result = 31 * result + (trangThai != null ? trangThai.hashCode() : 0);
        return result;
    }

    public LopHoc getLopHocByIdLop() {
        return lopHocByIdLop;
    }

    public void setLopHocByIdLop(LopHoc lopHocByIdLop) {
        this.lopHocByIdLop = lopHocByIdLop;
    }

    public SinhVien(String hoTen, Integer idLop, String diaChi, String trangThai, LopHoc lopHocByIdLop) {
        this.hoTen = hoTen;
        this.idLop = idLop;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
        this.lopHocByIdLop = lopHocByIdLop;
    }
}
