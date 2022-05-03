package com.ptit.dangkytinchi.controller;

import com.ptit.dangkytinchi.DTO.MonHocDTO;
import com.ptit.dangkytinchi.response.ResponeAPI;
import com.ptit.dangkytinchi.model.*;
import com.ptit.dangkytinchi.service.MonHocService;
import com.ptit.dangkytinchi.service.SinhVienKhoaService;
import com.ptit.dangkytinchi.service.SinhVienService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@RestController
@RequestMapping(path = "/monhoc", produces = "application/json")
@CrossOrigin(origins = "*")
public class MonHocController {

    private MonHocService monHocService;
    private SinhVienService sinhVienService;
    private SinhVienKhoaService sinhVienKhoaService;

    public MonHocController(MonHocService monHocService, SinhVienService sinhVienService, SinhVienKhoaService sinhVienKhoaService) {
        this.monHocService = monHocService;
        this.sinhVienService = sinhVienService;
        this.sinhVienKhoaService = sinhVienKhoaService;
    }

    //tim kiem mon hoc theo chuong trinh dao tao
    @PostMapping("/timkiem/{key}")
    public ResponeAPI getMonHocByTenMH(@RequestBody LinkedHashMap object, @PathVariable String key) {
        ResponeAPI res = new ResponeAPI();
        String maSinhVien = object.get("maSinhVien").toString().trim();
        SinhVienKhoa sinhVienKhoa = sinhVienKhoaService.timKiemSinhVienKhoaTheoMaSinhVien(maSinhVien);
        SinhVien sinhVien = sinhVienService.timKiemTheoMaSinhVien(maSinhVien);
        if (sinhVienKhoa.isDangHoc()) {
            String maKiHoc = sinhVienKhoaService.layMaKiHocCuaSinhVienKhoa(sinhVienKhoa);
            String maBoMon = sinhVienService.layMaBoMonCuaSinhVien(sinhVien);
            ArrayList<MonHoc> dsMonHoc = monHocService.timKiemMonHocTheoTenVaChuongTrinhDaoTaoCuaSinhVien(maBoMon, maKiHoc, key);
            ArrayList<MonHocDTO> ketQua = new ArrayList<MonHocDTO>();
            dsMonHoc.forEach(monHoc -> {
                MonHocDTO temp = new MonHocDTO(monHoc.getMaMonHoc(), monHoc.getTenMonHoc(), monHoc.getSoTc());
                ketQua.add(temp);
            });
            res.setData(ketQua);
        } else {
            res.setData(null);
        }
        return res;
    }

    //chuc nang tim kiem tat ca cac mon hoc duoc giang day
    @PostMapping("/timkiembutton/{key}")
    public ResponeAPI getMonHocByTenMHbutton(@PathVariable String key) {
        ResponeAPI res = new ResponeAPI();
        ArrayList<MonHoc> dsMonHoc = monHocService.timKiemMonHocTheoTenMonHoc(key);
        ArrayList<MonHocDTO> ketQua = new ArrayList<MonHocDTO>();
        dsMonHoc.forEach(monHoc -> {
            MonHocDTO temp = new MonHocDTO(monHoc.getMaMonHoc(), monHoc.getTenMonHoc(), monHoc.getSoTc());
            ketQua.add(temp);
        });
        res.setData(ketQua);
        return res;
    }

}
