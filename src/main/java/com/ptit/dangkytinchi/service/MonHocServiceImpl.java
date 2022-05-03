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
    public ArrayList<MonHocDTO> timKiemMonHocTheoTenMonHoc(String key) {
      ArrayList<MonHocDTO> ketQua = new ArrayList<MonHocDTO>();
      ArrayList<MonHoc> dsMonHoc = (ArrayList<MonHoc>) monHocRepository.findMonHocByTenMonHocContains(key);
        dsMonHoc.forEach(monHoc -> {
            MonHocDTO temp = new MonHocDTO(monHoc.getMaMonHoc(), monHoc.getTenMonHoc(), monHoc.getSoTc());
            ketQua.add(temp);
        });

      return ketQua;
    }

    @Override
    public ArrayList<MonHocDTO> timKiemMonHocTheoTenVaChuongTrinhDaoTaoCuaSinhVien(String maBoMon, String maKiHoc, String key) {
        ArrayList<MonHocKiHoc> dsMonHocKiHoc = new ArrayList<MonHocKiHoc>();
        if (maBoMon.length() > 0) {
            dsMonHocKiHoc = (ArrayList<MonHocKiHoc>) monHocKiHocRepository.
                    findMonHocKiHocByKiHoc_MaKiHocAndMonHoc_BoMon_MaBoMonAndMonHoc_TenMonHocContains(maKiHoc, maBoMon, key);
        } else {
            dsMonHocKiHoc = (ArrayList<MonHocKiHoc>) monHocKiHocRepository.
                    findMonHocKiHocByKiHoc_MaKiHocAndMonHoc_TenMonHocContains(maKiHoc, key);
        }


        ArrayList<MonHocDTO> dsMonHocDTO = new ArrayList<MonHocDTO>();
        dsMonHocKiHoc.forEach(monHoc -> {
            MonHocDTO temp = new MonHocDTO(monHoc.getMonHoc().getMaMonHoc(), monHoc.getMonHoc().getTenMonHoc(), monHoc.getMonHoc().getSoTc());
            dsMonHocDTO.add(temp);
        });

        return dsMonHocDTO;
    }
}
