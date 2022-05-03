package com.ptit.dangkytinchi.service;

import com.ptit.dangkytinchi.DTO.LopHocPhanDTO;
import com.ptit.dangkytinchi.model.LichHoc;
import com.ptit.dangkytinchi.model.LopHocPhan;
import com.ptit.dangkytinchi.repository.LopHocPhanRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class LopHocPhanServiceImpl implements LopHocPhanService {
    private LopHocPhanRepository lopHocPhanRepository;

    public LopHocPhanServiceImpl(LopHocPhanRepository lopHocPhanRepository) {
        this.lopHocPhanRepository = lopHocPhanRepository;
    }

    @Override
    public ArrayList<LopHocPhan> timKiemLopHocPhanDuocPhepDangKyCuaMonHoc(String maMonHocKiHoc) {
        return (ArrayList<LopHocPhan>) lopHocPhanRepository.findLopHocPhanByMonHocKiHoc_MaMonHocKiHoc(maMonHocKiHoc);
    }

    @Override
    public LopHocPhan timKiemLopHocPhanTheoMaLopHocPhan(String maLopHocPhan) {
        return lopHocPhanRepository.findLopHocPhanByMaLopHocPhan(maLopHocPhan);
    }
}