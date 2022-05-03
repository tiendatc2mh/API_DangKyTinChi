package com.ptit.dangkytinchi.service;

import com.ptit.dangkytinchi.model.LichHoc;
import com.ptit.dangkytinchi.repository.LichHocRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LichHocServiceImpl implements LichHocService {
    private LichHocRepository lichHocRepository;

    public LichHocServiceImpl(LichHocRepository lichHocRepository) {
        this.lichHocRepository = lichHocRepository;
    }

    @Override
    public ArrayList<LichHoc> timKiemLichHocCuaLopHocPhan(String maLopHocPhan) {
        return lichHocRepository.findLichHocByLopHocPhan_MaLopHocPhan(maLopHocPhan);
    }
}
