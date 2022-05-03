package com.ptit.dangkytinchi.service;

import com.ptit.dangkytinchi.DTO.LichHocDTO;
import com.ptit.dangkytinchi.model.DangKyHoc;
import com.ptit.dangkytinchi.model.LichHoc;
import com.ptit.dangkytinchi.repository.DangKyHocRepository;
import com.ptit.dangkytinchi.repository.LichHocRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LichHocServiceImpl implements LichHocService {
    private LichHocRepository lichHocRepository;
    private DangKyHocRepository dangKyHocRepository;


    public LichHocServiceImpl(LichHocRepository lichHocRepository, DangKyHocRepository dangKyHocRepository) {
        this.lichHocRepository = lichHocRepository;
        this.dangKyHocRepository = dangKyHocRepository;
    }

    @Override
    public ArrayList<LichHoc> timKiemLichHocCuaLopHocPhan(String maLopHocPhan) {
        return lichHocRepository.findLichHocByLopHocPhan_MaLopHocPhan(maLopHocPhan);
    }

    @Override
    public ArrayList<LichHoc> timKiemLichHocCuaSinhVienTheoTuan(String maSinhVienKhoa, String maTuanHoc) {
        ArrayList<DangKyHoc> dsDangKyHoc = dangKyHocRepository.findDangKyHocBySinhVienKhoa_MaSinhVienKhoa(maSinhVienKhoa);
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
