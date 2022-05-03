package com.ptit.dangkytinchi.service;

import com.ptit.dangkytinchi.model.LichHoc;

import java.util.ArrayList;

public interface LichHocService {
    ArrayList<LichHoc> timKiemLichHocCuaLopHocPhan(String maLopHocPhan);
}
