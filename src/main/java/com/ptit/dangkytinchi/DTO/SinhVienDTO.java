package com.ptit.dangkytinchi.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class SinhVienDTO implements Serializable {
    private String maSinhVien;
    private String tenSinhVien;
    private String taiKhoan;
    private String matKhau;
    private String matKhauMoi;
    private String lop;

    public SinhVienDTO() {
    }

    public SinhVienDTO(String taiKhoan, String matKhau) {
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
    }

    public SinhVienDTO(String maSinhVien, String tenSinhVien, String taiKhoan, String matKhau, String matKhauMoi, String lop) {
        this.maSinhVien = maSinhVien;
        this.tenSinhVien = tenSinhVien;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.matKhauMoi = matKhauMoi;
        this.lop = lop;
    }

    public SinhVienDTO(String taiKhoan, String matKhau, String matKhauMoi) {
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.matKhauMoi = matKhauMoi;
    }

    @Override
    public String toString() {
        return "SinhVienDTO{" +
                "maSinhVien='" + maSinhVien + '\'' +
                ", tenSinhVien='" + tenSinhVien + '\'' +
                ", taiKhoan='" + taiKhoan + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", matKhauMoi='" + matKhauMoi + '\'' +
                ", lop='" + lop + '\'' +
                '}';
    }
}
