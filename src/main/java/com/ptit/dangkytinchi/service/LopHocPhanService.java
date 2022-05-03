package com.ptit.dangkytinchi.service;

import com.ptit.dangkytinchi.DTO.LopHocPhanDTO;
import com.ptit.dangkytinchi.model.LopHocPhan;

import java.util.ArrayList;

public interface LopHocPhanService {
    ArrayList<LopHocPhan> timKiemLopHocPhanDuocPhepDangKyCuaMonHoc(String maMonHocKiHoc);

}
