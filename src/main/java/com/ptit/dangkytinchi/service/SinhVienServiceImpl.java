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
    public SinhVienDTO timKiemTheoTaiKhoanVaMatKhau(SinhVienDTO sinhVienDTO) {
        SinhVien sv = sinhVienRepository.findOneByTaiKhoanAndMatKhau(sinhVienDTO.getTaiKhoan(), sinhVienDTO.getMatKhau());
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

}
