package com.ptit.dangkytinchi.controller;

import com.ptit.dangkytinchi.DTO.SinhVienDTO;

import com.ptit.dangkytinchi.response.ResponeAPI;



import com.ptit.dangkytinchi.model.SinhVien;
import com.ptit.dangkytinchi.repository.SinhVienRepository;
import com.ptit.dangkytinchi.service.SinhVienService;
import com.ptit.dangkytinchi.service.SinhVienServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/sinhvien", produces = "application/json")
@CrossOrigin(origins = "*")
public class SinhVienController {
    private SinhVienService sinhVienService;

    public SinhVienController(SinhVienService sinhVienService) {
        this.sinhVienService = sinhVienService;
    }

    //dang nhap
    @PostMapping("/dangnhap")
    public ResponeAPI dangNhap(@RequestBody SinhVienDTO sinhVienDTO) throws Exception {
        ResponeAPI res = new ResponeAPI();
        SinhVienDTO svDTO = sinhVienService.timKiemTheoTaiKhoanVaMatKhau(sinhVienDTO.getTaiKhoan(), sinhVienDTO.getMatKhau());
        if (svDTO == null) {
            res.setError("Sai tài khoản, mật khẩu.Vui lòng nhập lại!");
        } else {
            res.setData(svDTO);
        }
        return res;
    }


    //doi mat khau
//    @PostMapping("/doimatkhau")
//    public ResponeAPI doiMatKhau(@RequestBody SinhVienDTO sinhVienDTO) throws Exception {
//        ResponeAPI res = new ResponeAPI();
//        SinhVien sv = svRepo.findOneByTaiKhoanAndMatKhau(sinhVienDTO.getTaiKhoan(), sinhVienDTO.getMatKhau());
//        if (sv == null) {
//            res.setError("Sai mật khẩu cũ.Vui lòng nhập lại!");
//        } else {
//            SinhVien svMoi = new SinhVien();
//            svMoi.setMaSinhVien(sv.getMaSinhVien());
//            svMoi.setTenSinhVien(sv.getTenSinhVien());
//            svMoi.setTaiKhoan(sv.getTaiKhoan());
//            svMoi.setMatKhau(sinhVienDTO.getMatKhauMoi());
//            svMoi.setLop(sv.getLop());
//            svRepo.save(svMoi);
//            res.setData("ok");
//        }
//        return res;
//    }

}
