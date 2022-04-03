package com.ptit.dangkytinchi.controller;

import com.ptit.dangkytinchi.DTO.MonHocDTO;
import com.ptit.dangkytinchi.DTO.SinhVienDTO;
import com.ptit.dangkytinchi.exception.ResponeAPI;
import com.ptit.dangkytinchi.model.*;
import com.ptit.dangkytinchi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/sinhvienkhoa", produces = "application/json")
@CrossOrigin(origins = "*")
public class SinhVienKhoaController {
    @Autowired
    private SinhVienKhoaRepository svRepo;
    @Autowired
    private BoMonRepository bomonRepo;
    @Autowired
    private MonHocRepository monhocRepo;

    @Autowired
    private MonHocKiHocRepository MHKHRepo;

    public SinhVienKhoaController(SinhVienKhoaRepository svRepo, BoMonRepository bmRepo, MonHocRepository monhocRepo, MonHocKiHocRepository MHKHRepo) {
        this.svRepo = svRepo;
        this.bomonRepo = bmRepo;
        this.monhocRepo = monhocRepo;
        this.MHKHRepo = MHKHRepo;
    }

    @GetMapping("/danhsach")
    public List<SinhVienKhoa> danhSachSinhVienKhoa() {
        return svRepo.findAll();
    }
    @PostMapping("/monhoc")
    public ResponeAPI layKhoaTheoMaSinhVien(@RequestBody SinhVienDTO sinhVienDTO){
        ResponeAPI res = new ResponeAPI();
        ArrayList<SinhVienKhoa>  list = (ArrayList<SinhVienKhoa>) svRepo.findAll();
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int thang = localDate.getMonthValue();
        int nam = localDate.getYear();

        SinhVienKhoa sinhVienKhoa = svRepo.findSinhVienKhoaBySinhVien_MaSinhVien(sinhVienDTO.getMaSinhVien());
        int nienKhoa = Integer.parseInt(sinhVienKhoa.getNienKhoa().substring(0, 4));
        String maKiHoc = "";
        if (nam - nienKhoa == 0) {
            maKiHoc = "KYHOC01";

        } else if (nam - nienKhoa == 1) {
            //hoc ki 2
            if (thang <= 7 && thang >= 2) {
                maKiHoc = "KYHOC02";
            }
            //hoc ki he
            else if (thang <= 9 && thang >= 7) {

            }
            //hoc ki 1
            else {
                maKiHoc = "KYHOC03";
            }
        } else if (nam - nienKhoa == 2) {
            //hoc ki 2
            if (thang <= 7 && thang >= 2) {
                maKiHoc = "KYHOC04";
            }
            //hoc ki he
            else if (thang <= 9 && thang >= 7) {

            }
            //hoc ki 1
            else {
                maKiHoc = "KYHOC05";
            }
        } else if (nam - nienKhoa == 3) {
            //hoc ki 2
            if (thang <= 7 && thang >= 2) {
                maKiHoc = "KYHOC06";
            }
            //hoc ki he
            else if (thang <= 9 && thang >= 7) {

            }
            //hoc ki 1
            else {
                maKiHoc = "KYHOC07";
            }
        } else if (nam - nienKhoa == 4) {
            //hoc ki 2
            if (thang <= 7 && thang >= 2) {
                maKiHoc = "KYHOC08";
            }
            //hoc ki he
            else if (thang <= 9 && thang >= 7) {
            }
            //hoc ki 1
            else {
            }
        }
        for (SinhVienKhoa svk : list) {
            if (svk.getSinhVien().getMaSinhVien().equals(sinhVienDTO.getMaSinhVien())) {
                ArrayList<BoMon> listBomon = (ArrayList<BoMon>) bomonRepo.danhSachBoMon(svk.getKhoa().getMaKhoa());
                ArrayList<MonHocDTO> listMonHoc = new ArrayList<>();
                if (listBomon.size() > 0){
                    String finalMaKiHoc = maKiHoc;
                    listBomon.forEach(boMon -> {
                        ArrayList<MonHoc> listdem = monhocRepo.timMonHocTheoMaBoMon(boMon.getMaBoMon());
                        listdem.forEach(monHoc -> {
                            List<MonHocKiHoc> listcheck = MHKHRepo.findMonHocKiHocByKiHoc_MaKiHocAndMonHoc_MaMonHoc(finalMaKiHoc, monHoc.getMaMonHoc());
                            System.out.println(listcheck.size());
                            if(listcheck.isEmpty() == false) {
                                MonHocDTO mhDTO = new MonHocDTO(monHoc.getMaMonHoc(), monHoc.getTenMonHoc(), monHoc.getSoTc(), monHoc.getBoMon().getMaBoMon(),null);
                                listMonHoc.add(mhDTO);
                            }
                        });
                    });
                }

                System.out.println(maKiHoc);
                res.setData(listMonHoc);
                return res;
            }
        }
        res.setError("Mã sinh viên sai");
        return res;
    }
}
