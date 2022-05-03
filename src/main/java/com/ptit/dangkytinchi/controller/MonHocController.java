package com.ptit.dangkytinchi.controller;

import com.ptit.dangkytinchi.DTO.LichHocDTO;
import com.ptit.dangkytinchi.DTO.LopHocPhanDTO;
import com.ptit.dangkytinchi.DTO.MonHocDTO;
import com.ptit.dangkytinchi.response.ResponeAPI;
import com.ptit.dangkytinchi.model.*;
import com.ptit.dangkytinchi.repository.MonHocKiHocRepository;
import com.ptit.dangkytinchi.repository.MonHocRepository;
import com.ptit.dangkytinchi.repository.SinhVienKhoaRepository;
import com.ptit.dangkytinchi.repository.SinhVienRepository;
import com.ptit.dangkytinchi.service.MonHocService;
import com.ptit.dangkytinchi.service.SinhVienKhoaService;
import com.ptit.dangkytinchi.service.SinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
        ArrayList<MonHocKiHoc> monHocKiHoc;
//        Date date = new Date();
//        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        int thang = localDate.getMonthValue();
//        int nam = localDate.getYear();

        SinhVienKhoa sinhVienKhoa = sinhVienKhoaService.timKiemSinhVienKhoaTheoMaSinhVien(maSinhVien);
        SinhVien sinhVien = sinhVienService.timKiemTheoMaSinhVien(maSinhVien);
        if (sinhVienKhoa.isDangHoc()) {
            String maKiHoc = sinhVienKhoaService.layMaKiHocCuaSinhVienKhoa(sinhVienKhoa);
            String maBoMon=sinhVienService.layMaBoMonCuaSinhVien(sinhVien);

//            int nienKhoa = Integer.parseInt(sinhVienKhoa.getNienKhoa().substring(0, 4));
//            String maKiHoc = "";
//            if (nam - nienKhoa == 0) {
//                maKiHoc = "KYHOC01";
//
//            } else if (nam - nienKhoa == 1) {
//                //hoc ki 2
//                if (thang <= 7 && thang >= 2) {
//                    maKiHoc = "KYHOC02";
//                }
//                //hoc ki he
//                else if (thang <= 9 && thang >= 7) {
//
//                }
//                //hoc ki 1
//                else {
//                    maKiHoc = "KYHOC03";
//                }
//
//            } else if (nam - nienKhoa == 2) {
//                //hoc ki 2
//                if (thang <= 7 && thang >= 2) {
//                    maKiHoc = "KYHOC04";
//                }
//                //hoc ki he
//                else if (thang <= 9 && thang >= 7) {
//
//                }
//                //hoc ki 1
//                else {
//                    maKiHoc = "KYHOC05";
//                }
//
//            } else if (nam - nienKhoa == 3) {
//                //hoc ki 2
//                if (thang <= 7 && thang >= 2) {
//                    maKiHoc = "KYHOC06";
//                }
//                //hoc ki he
//                else if (thang <= 9 && thang >= 7) {
//
//                }
//                //hoc ki 1
//                else {
//                    maKiHoc = "KYHOC07";
//                }
//
//            } else if (nam - nienKhoa == 4) {
//                //hoc ki 2
//                if (thang <= 7 && thang >= 2) {
//                    maKiHoc = "KYHOC08";
//                }
//
//            }
//            if (maBoMon.length() > 0) {
//                monHocKiHoc = (ArrayList<MonHocKiHoc>) monHocKiHocRepository.
//                        findMonHocKiHocByKiHoc_MaKiHocAndMonHoc_BoMon_MaBoMonAndMonHoc_TenMonHocContains(maKiHoc, maBoMon, key);
//            } else {
//                monHocKiHoc = (ArrayList<MonHocKiHoc>) monHocKiHocRepository.
//                        findMonHocKiHocByKiHoc_MaKiHocAndMonHoc_TenMonHocContains(maKiHoc, key);
//            }
//
//
//            ArrayList<MonHocDTO> dsMonHocDTO = new ArrayList<MonHocDTO>();
//            monHocKiHoc.forEach(monHoc -> {
//                MonHocDTO temp = new MonHocDTO(monHoc.getMonHoc().getMaMonHoc(), monHoc.getMonHoc().getTenMonHoc(), monHoc.getMonHoc().getSoTc());
//                dsMonHocDTO.add(temp);
//            });

            res.setData(monHocService.timKiemMonHocCuaSinhVienTheoChuongTrinhDaoTaoVaTheoTen(maBoMon,maKiHoc,key));
        } else {
            res.setData(null);
        }
        return res;
    }


    //chuc nang tim kiem tat ca cac mon hoc duoc giang day
    @PostMapping("/timkiembutton/{key}")
    public ResponeAPI getMonHocByTenMHbutton(@PathVariable String key) {
        ResponeAPI res = new ResponeAPI();
        res.setData(monHocService.timKiemMonHocTheoTenMonHoc(key));
        return res;
    }

}
