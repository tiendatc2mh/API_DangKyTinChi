package com.ptit.dangkytinchi.service;

import com.ptit.dangkytinchi.model.MonHocKiHoc;
import com.ptit.dangkytinchi.repository.MonHocKiHocRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MonHocKiHocServiceImpl implements MonHocKiHocService {
    private MonHocKiHocRepository monHocKiHocRepository;

    public MonHocKiHocServiceImpl(MonHocKiHocRepository monHocKiHocRepository) {
        this.monHocKiHocRepository = monHocKiHocRepository;
    }

    @Override
    public ArrayList<MonHocKiHoc> timKiemMonHocKiHocTheoTenMonHocVaMaKiHocVaMaBoMon(String maKiHoc, String maBoMon, String key) {
        return (ArrayList<MonHocKiHoc>) monHocKiHocRepository.
                findMonHocKiHocByKiHoc_MaKiHocAndMonHoc_BoMon_MaBoMonAndMonHoc_TenMonHocContains(maKiHoc, maBoMon, key);
    }

    @Override
    public ArrayList<MonHocKiHoc> timKiemMonHocKiHocTheoTenMonHocVaMaKiHoc(String maKiHoc, String key) {
        return (ArrayList<MonHocKiHoc>) monHocKiHocRepository.
                findMonHocKiHocByKiHoc_MaKiHocAndMonHoc_TenMonHocContains(maKiHoc, key);
    }

    @Override
    public MonHocKiHoc timKiemMonHocKiHocTheoMaMonHocVaMaKiHoc(String maKiHoc, String maMonHoc) {
       return  monHocKiHocRepository.
               findMonHocKiHocByKiHoc_MaKiHocAndMonHoc_MaMonHoc(maKiHoc, maMonHoc);
    }
}
