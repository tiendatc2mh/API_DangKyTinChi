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
    public SinhVienDTO timKiemTheoTaiKhoanVaMatKhau(String taiKhoan, String matKhau) {
        SinhVien sv = sinhVienRepository.findOneByTaiKhoanAndMatKhau(taiKhoan, matKhau);
        SinhVienDTO svDTO = null;
        if (sv != null) {
            svDTO = new SinhVienDTO();
            svDTO.setMaSinhVien(sv.getMaSinhVien());
            svDTO.setTenSinhVien(sv.getTenSinhVien());
            svDTO.setTaiKhoan(sv.getTaiKhoan());
            svDTO.setMatKhau(sv.getMatKhau());
            svDTO.setLop(sv.getLop());
        }
        return svDTO;
    }

    @Override
    public void doiMatKhau(SinhVien sinhVien) {
        sinhVienRepository.save(sinhVien);
    }
}
