package com.ptit.dangkytinchi.service;

import com.ptit.dangkytinchi.DTO.SinhVienDTO;
import com.ptit.dangkytinchi.model.SinhVien;
import com.ptit.dangkytinchi.repository.SinhVienRepository;
import org.springframework.stereotype.Service;

@Service
public class SinhVienServiceImpl implements SinhVienService {
    SinhVienRepository sinhVienRepository;

    public SinhVienServiceImpl(SinhVienRepository sinhVienRepository) {
        this.sinhVienRepository = sinhVienRepository;
    }

    @Override
    public SinhVien timKiemTheoTaiKhoanVaMatKhau(SinhVienDTO sinhVienDTO) {
        return sinhVienRepository.findOneByTaiKhoanAndMatKhau(sinhVienDTO.getTaiKhoan(), sinhVienDTO.getMatKhau());
    }

    //true: doi mat khau thanh cong
    //false: sai mat khau cu
    @Override
    public boolean doiMatKhau(SinhVienDTO sinhVienDTO) {
        SinhVien sv = sinhVienRepository.findOneByTaiKhoanAndMatKhau(sinhVienDTO.getTaiKhoan(), sinhVienDTO.getMatKhau());
        if (sv == null) {
            return false;
        } else {
            sv.setMatKhau(sinhVienDTO.getMatKhauMoi());
            sinhVienRepository.save(sv);
            return true;
        }
    }

    @Override
    public SinhVien timKiemTheoMaSinhVien(String maSinhVien) {
        return  sinhVienRepository.getById(maSinhVien);
    }

    @Override
    public String layMaBoMonCuaSinhVien(SinhVien sinhVien) {
        String maBoMon="";
        if (sinhVien.getLop().contains("CNPM")) {
            maBoMon= "BOMON10";
        } else if (sinhVien.getLop().contains("HTTT")) {
            maBoMon= "BOMON09";
        }
        return maBoMon;
    }

}