package com.ptit.dangkytinchi.service;

import com.ptit.dangkytinchi.DTO.MonHocDTO;

import java.util.ArrayList;

public interface MonHocService {
    ArrayList<MonHocDTO> timKiemMonHocTheoTenMonHoc(String key);
    ArrayList<MonHocDTO> timKiemMonHocCuaSinhVienTheoChuongTrinhDaoTaoVaTheoTen(String maBoMon, String maKiHoc, String key);
}
