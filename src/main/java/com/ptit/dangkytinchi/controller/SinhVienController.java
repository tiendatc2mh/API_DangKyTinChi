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
        SinhVien sv = sinhVienService.timKiemTheoTaiKhoanVaMatKhau(sinhVienDTO);
        if (sv == null) {
            res.setError("Sai tài khoản, mật khẩu.Vui lòng nhập lại!");
        } else {
            SinhVienDTO svDTO = new SinhVienDTO();
            svDTO.setMaSinhVien(sv.getMaSinhVien());
            svDTO.setTenSinhVien(sv.getTenSinhVien());
            svDTO.setTaiKhoan(sv.getTaiKhoan());
            svDTO.setMatKhau(sv.getMatKhau());
            svDTO.setLop(sv.getLop());
            res.setData(svDTO);
        }
        return res;
    }


    //doi mat khau
    @PostMapping("/doimatkhau")
    public ResponeAPI doiMatKhau(@RequestBody SinhVienDTO sinhVienDTO) throws Exception {
        ResponeAPI res = new ResponeAPI();
        boolean ketQua = sinhVienService.doiMatKhau(sinhVienDTO);
        if (!ketQua) {
            res.setError("Sai mật khẩu cũ.Vui lòng nhập lại!");
        } else {
            res.setData("ok");
        }
        return res;
    }

}
