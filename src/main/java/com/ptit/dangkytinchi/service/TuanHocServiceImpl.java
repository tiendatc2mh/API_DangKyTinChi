package com.ptit.dangkytinchi.service;

import com.ptit.dangkytinchi.DTO.TuanHocDTO;
import com.ptit.dangkytinchi.model.TuanHoc;
import com.ptit.dangkytinchi.repository.TuanHocRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TuanHocServiceImpl implements TuanHocService {

    private TuanHocRepository tuanHocRepository;

    public TuanHocServiceImpl(TuanHocRepository tuanHocRepository) {
        this.tuanHocRepository = tuanHocRepository;
    }

    @Override
    public ArrayList<TuanHocDTO> timKiemTatCaTuanHoc() {
        ArrayList<TuanHocDTO> ketQua = new ArrayList<TuanHocDTO>();
        ArrayList<TuanHoc> dsTuanHoc = (ArrayList<TuanHoc>) tuanHocRepository.findAll();
        dsTuanHoc.forEach(tuanHoc -> {
            ketQua.add(new TuanHocDTO(tuanHoc.getMaTuanHoc(), tuanHoc.getTenTuanHoc(), tuanHoc.getMoTa()));
        });
        return ketQua;
    }
}
