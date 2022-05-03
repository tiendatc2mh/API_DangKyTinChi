package com.ptit.dangkytinchi.service;

import com.ptit.dangkytinchi.DTO.SinhVienDTO;
import com.ptit.dangkytinchi.model.SinhVien;

public interface SinhVienService {
    SinhVien timKiemTheoTaiKhoanVaMatKhau(SinhVienDTO sinhVienDTO);
    boolean doiMatKhau(SinhVienDTO sinhVienDTO);

    SinhVien timKiemTheoMaSinhVien(String maSinhVien);

    String layMaBoMonCuaSinhVien(SinhVien sinhVien);
}
