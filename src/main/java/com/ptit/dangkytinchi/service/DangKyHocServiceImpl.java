package com.ptit.dangkytinchi.service;


import com.ptit.dangkytinchi.model.DangKyHoc;
import com.ptit.dangkytinchi.repository.DangKyHocRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DangKyHocServiceImpl implements DangKyHocService {
    private DangKyHocRepository dangKyHocRepository;

    public DangKyHocServiceImpl(DangKyHocRepository dangKyHocRepository) {
        this.dangKyHocRepository = dangKyHocRepository;
    }

    @Override
    public int laySiSoThucTeCuaLopHocPhan(String maLopHocPhan) {
        return (dangKyHocRepository.findDangKyHocByLopHocPhan_MaLopHocPhan(maLopHocPhan)).size();
    }

    @Override
    public ArrayList<DangKyHoc> timKiemDangKyHocCuaSinhVien(String maSinhVienKhoa) {
        return dangKyHocRepository.findDangKyHocBySinhVienKhoa_MaSinhVienKhoa(maSinhVienKhoa);
    }

    @Override
    public void luuDangKy(DangKyHoc dangKyHoc) {
        dangKyHocRepository.save(dangKyHoc);
    }

    @Override
    public void xoaDangKy(DangKyHoc dangKyHoc) {
        dangKyHocRepository.delete(dangKyHoc);
    }

    @Override
    public DangKyHoc timKiemDangKyHocTheoSinhVienKhoaVaMaLopHocPhan(String maSinhVienKhoa, String maLopHocPhan) {
        return dangKyHocRepository.findDangKyHocBySinhVienKhoa_MaSinhVienKhoaAndLopHocPhan_MaLopHocPhan(maSinhVienKhoa, maLopHocPhan);
    }
}
