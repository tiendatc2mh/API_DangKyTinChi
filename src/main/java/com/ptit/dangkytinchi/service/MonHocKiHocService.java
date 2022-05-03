package com.ptit.dangkytinchi.service;

import com.ptit.dangkytinchi.model.MonHocKiHoc;

import java.util.ArrayList;

public interface MonHocKiHocService {

    ArrayList<MonHocKiHoc> timKiemMonHocKiHocTheoTenMonHocVaMaKiHocVaMaBoMon(String maKiHoc, String maBoMon, String key);
    ArrayList<MonHocKiHoc> timKiemMonHocKiHocTheoTenMonHocVaMaKiHoc(String maKiHoc, String key);
    MonHocKiHoc timKiemMonHocKiHocTheoMaMonHocVaMaKiHoc(String maKiHoc, String maMonHoc);
}
