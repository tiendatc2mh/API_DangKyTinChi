package com.ptit.dangkytinchi.service;

import com.ptit.dangkytinchi.model.SinhVienKhoa;

public interface SinhVienKhoaService {
    SinhVienKhoa timKiemSinhVienKhoaTheoMaSinhVien(String maSinhVien);
    String layMaKiHocCuaSinhVienKhoa(SinhVienKhoa sinhVienKhoa);

}
