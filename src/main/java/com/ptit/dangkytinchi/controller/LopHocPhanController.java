package com.ptit.dangkytinchi.controller;


import com.ptit.dangkytinchi.DTO.*;
import com.ptit.dangkytinchi.model.*;
import com.ptit.dangkytinchi.response.ResponeAPI;
import com.ptit.dangkytinchi.repository.*;
import com.ptit.dangkytinchi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@RestController
@RequestMapping(path = "/dangkytinchi", produces = "application/json")
@CrossOrigin(origins = "*")
public class LopHocPhanController {

    private SinhVienKhoaService sinhVienKhoaService;
    private MonHocKiHocService monHocKiHocService;
    private LichHocService lichHocService;
    private LopHocPhanService lopHocPhanService;
    private DangKyHocService dangKyHocService;

    public LopHocPhanController(SinhVienKhoaService sinhVienKhoaService, MonHocKiHocService monHocKiHocService,
                                LichHocService lichHocService, LopHocPhanService lopHocPhanService,
                                DangKyHocService dangKyHocService) {
        this.sinhVienKhoaService = sinhVienKhoaService;
        this.monHocKiHocService = monHocKiHocService;
        this.lichHocService = lichHocService;
        this.lopHocPhanService = lopHocPhanService;
        this.dangKyHocService = dangKyHocService;
    }

    //lay tat ca cac lop hoc phan duoc mo cua mon hoc theo maMonHoc
    @PostMapping("/lophocphan/{maMonHoc}")
    private ResponeAPI getLopHocPhanByMonHoc(@RequestBody LinkedHashMap object, @PathVariable("maMonHoc") String maMonHoc) {
        ResponeAPI res = new ResponeAPI();
        String maSinhVien = object.get("maSinhVien").toString().trim();
        ArrayList<LopHocPhanDTO> dsLopHocPhanDTO = new ArrayList<LopHocPhanDTO>();
        SinhVienKhoa sinhVienKhoa = sinhVienKhoaService.timKiemSinhVienKhoaTheoMaSinhVien(maSinhVien);
        String maKiHoc = sinhVienKhoaService.layMaKiHocCuaSinhVienKhoa(sinhVienKhoa);
        MonHocKiHoc monHocKiHoc = monHocKiHocService.timKiemMonHocKiHocTheoMaMonHocVaMaKiHoc(maKiHoc, maMonHoc);
        ArrayList<LopHocPhan> dsLopHocPhan = lopHocPhanService.
                timKiemLopHocPhanDuocPhepDangKyCuaMonHoc(monHocKiHoc.getMaMonHocKiHoc());
        //lay tat ca lich hoc cua lop hoc phan theo maLopHocPhan
        dsLopHocPhan.forEach(lopHocPhan -> {
            ArrayList<LichHoc> dsLichHoc = new ArrayList<LichHoc>();
            dsLichHoc = lichHocService.timKiemLichHocCuaLopHocPhan(lopHocPhan.getMaLopHocPhan());
            int siSoThucTe = dangKyHocService.laySiSoThucTeCuaLopHocPhan(lopHocPhan.getMaLopHocPhan());
            LopHocPhanDTO tempLHP_DTO = new LopHocPhanDTO(lopHocPhan.getMaLopHocPhan(), lopHocPhan.getTenLopHocPhan(),
                    lopHocPhan.getSiSoToiDa(), siSoThucTe, lopHocPhan.getMoTa(), lopHocPhan.getMonHocKiHoc(), dsLichHoc);
            dsLopHocPhanDTO.add(tempLHP_DTO);
        });
        res.setData(dsLopHocPhanDTO);
        return res;
    }
}
