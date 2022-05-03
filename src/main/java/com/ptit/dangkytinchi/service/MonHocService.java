package com.ptit.dangkytinchi.service;

import com.ptit.dangkytinchi.DTO.MonHocDTO;
import com.ptit.dangkytinchi.model.MonHoc;

import java.util.ArrayList;

public interface MonHocService {
    ArrayList<MonHoc> timKiemMonHocTheoTenMonHoc(String key);
    ArrayList<MonHoc> timKiemMonHocTheoTenVaChuongTrinhDaoTaoCuaSinhVien(String maBoMon, String maKiHoc, String key);
}
