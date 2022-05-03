package com.ptit.dangkytinchi.service;

import com.ptit.dangkytinchi.DTO.MonHocDTO;
import com.ptit.dangkytinchi.model.MonHoc;
import com.ptit.dangkytinchi.model.MonHocKiHoc;
import com.ptit.dangkytinchi.repository.MonHocKiHocRepository;
import com.ptit.dangkytinchi.repository.MonHocRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MonHocServiceImpl implements MonHocService {
    private MonHocRepository monHocRepository;
    private MonHocKiHocRepository monHocKiHocRepository;


    public MonHocServiceImpl(MonHocRepository monHocRepository, MonHocKiHocRepository monHocKiHocRepository) {
        this.monHocRepository = monHocRepository;
        this.monHocKiHocRepository = monHocKiHocRepository;
    }

    @Override
    public ArrayList<MonHoc> timKiemMonHocTheoTenMonHoc(String key) {
      return (ArrayList<MonHoc>) monHocRepository.findMonHocByTenMonHocContains(key);
    }

    @Override
    public ArrayList<MonHoc> timKiemMonHocTheoTenVaChuongTrinhDaoTaoCuaSinhVien(String maBoMon, String maKiHoc, String key) {
        ArrayList<MonHocKiHoc> dsMonHocKiHoc = new ArrayList<MonHocKiHoc>();
        if (maBoMon.length() > 0) {
            dsMonHocKiHoc = (ArrayList<MonHocKiHoc>) monHocKiHocRepository.
                    findMonHocKiHocByKiHoc_MaKiHocAndMonHoc_BoMon_MaBoMonAndMonHoc_TenMonHocContains(maKiHoc, maBoMon, key);
        } else {
            dsMonHocKiHoc =(ArrayList<MonHocKiHoc>) monHocKiHocRepository.
                    findMonHocKiHocByKiHoc_MaKiHocAndMonHoc_TenMonHocContains(maKiHoc, key);
        }
        ArrayList<MonHoc> ketQua = new ArrayList<MonHoc>();
        dsMonHocKiHoc.forEach(monHocKiHoc -> {
            ketQua.add(monHocKiHoc.getMonHoc());
        });

        return ketQua;
    }
}
