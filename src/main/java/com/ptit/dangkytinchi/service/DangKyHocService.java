package com.ptit.dangkytinchi.service;

import com.ptit.dangkytinchi.DTO.DangKyHocDTO;
import com.ptit.dangkytinchi.model.DangKyHoc;

import java.util.ArrayList;

public interface DangKyHocService {
    int laySiSoThucTeCuaLopHocPhan(String maLopHocPhan);
    ArrayList<DangKyHoc> timKiemDangKyHocCuaSinhVien(String maSinhVienKhoa);
    void luuDangKy(DangKyHoc dangKyHoc);
    void xoaDangKy(DangKyHoc dangKyHoc);
    DangKyHoc timKiemDangKyHocTheoSinhVienKhoaVaMaLopHocPhan(String maSinhVienKhoa, String maLopHocPhan);

}
