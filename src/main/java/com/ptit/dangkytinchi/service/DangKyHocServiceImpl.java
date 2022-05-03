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
}
