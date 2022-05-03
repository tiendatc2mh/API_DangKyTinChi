package com.ptit.dangkytinchi.service;

import com.ptit.dangkytinchi.DTO.LichHocDTO;
import com.ptit.dangkytinchi.model.DangKyHoc;
import com.ptit.dangkytinchi.model.LichHoc;
import com.ptit.dangkytinchi.repository.LichHocRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LichHocServiceImpl implements LichHocService {
    private LichHocRepository lichHocRepository;
    private DangKyHocService dangKyHocService;

    public LichHocServiceImpl(LichHocRepository lichHocRepository, DangKyHocService dangKyHocService) {
        this.lichHocRepository = lichHocRepository;
        this.dangKyHocService = dangKyHocService;
    }

    @Override
    public ArrayList<LichHoc> timKiemLichHocCuaLopHocPhan(String maLopHocPhan) {
        return lichHocRepository.findLichHocByLopHocPhan_MaLopHocPhan(maLopHocPhan);
    }

    @Override
    public ArrayList<LichHoc> timKiemLichHocCuaSinhVien(String maSinhVienKhoa, String maTuanHoc) {
        ArrayList<DangKyHoc> dsDangKyHoc = dangKyHocService.timKiemDangKyHocCuaSinhVien(maSinhVienKhoa);
        ArrayList<LichHoc> ketQua = new ArrayList<LichHoc>();
        dsDangKyHoc.forEach(dangKyHoc -> {
            ArrayList<LichHoc> dsLichHoc = lichHocRepository.findLichHocByLopHocPhan_MaLopHocPhanAndTuanHoc_MaTuanHoc(dangKyHoc.getLopHocPhan().getMaLopHocPhan(), maTuanHoc);
            if (dsLichHoc.size() > 0) {
                LichHoc lichHoc = dsLichHoc.get(0);
                ketQua.add(lichHoc);
            }
        });
        return ketQua;
    }
}
