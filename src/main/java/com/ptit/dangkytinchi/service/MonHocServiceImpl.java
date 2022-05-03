package com.ptit.dangkytinchi.service;

import com.ptit.dangkytinchi.DTO.MonHocDTO;
import com.ptit.dangkytinchi.model.MonHoc;
import com.ptit.dangkytinchi.repository.MonHocRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MonHocServiceImpl implements MonHocService {
    private MonHocRepository monHocRepository;

    public MonHocServiceImpl(MonHocRepository monHocRepository) {
        this.monHocRepository = monHocRepository;
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
}
