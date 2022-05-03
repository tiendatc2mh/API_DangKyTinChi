package com.ptit.dangkytinchi.service;

import com.ptit.dangkytinchi.DTO.SinhVienDTO;
import com.ptit.dangkytinchi.model.SinhVien;

public interface SinhVienService {
    SinhVienDTO timKiemTheoTaiKhoanVaMatKhau(SinhVienDTO sinhVienDTO);
    boolean doiMatKhau(SinhVienDTO sinhVienDTO);
}
