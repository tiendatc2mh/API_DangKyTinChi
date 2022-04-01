package com.ptit.dangkytinchi.controller;

import com.ptit.dangkytinchi.DTO.LichHocDTO;
import com.ptit.dangkytinchi.DTO.LopHocPhanDTO;
import com.ptit.dangkytinchi.DTO.MonHocDTO;
import com.ptit.dangkytinchi.exception.ResponeAPI;
import com.ptit.dangkytinchi.model.*;
import com.ptit.dangkytinchi.repository.MonHocKiHocRepository;
import com.ptit.dangkytinchi.repository.MonHocRepository;
import com.ptit.dangkytinchi.repository.SinhVienKhoaRepository;
import com.ptit.dangkytinchi.repository.SinhVienRepository;
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
    @Autowired
    private MonHocRepository monHocRepository;
    @Autowired
    private SinhVienKhoaRepository sinhVienKhoaRepository;
    @Autowired
    private MonHocKiHocRepository monHocKiHocRepository;
    @Autowired
    private SinhVienRepository sinhVienRepository;


    @PostMapping("/timkiem/{key}")
    public ResponeAPI getMonHocByTenMH(@RequestBody LinkedHashMap object, @PathVariable String key){
        ResponeAPI res = new ResponeAPI();
        String maSinhVien = object.get("maSinhVien").toString().trim();
        ArrayList<LopHocPhanDTO> dsLHPDTO = new ArrayList<LopHocPhanDTO>();
        ArrayList<LichHocDTO> dsLHDTO = new ArrayList<LichHocDTO>();
        ArrayList<MonHocKiHoc>  monHocKiHoc;
        ArrayList<LopHocPhan> dsLopHocPhan = new ArrayList<LopHocPhan>();
        String maBoMon ="";
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int thang = localDate.getMonthValue();
        int nam = localDate.getYear();

        SinhVienKhoa sinhVienKhoa = sinhVienKhoaRepository.findSinhVienKhoaBySinhVien_MaSinhVien(maSinhVien);
        SinhVien sinhVien = sinhVienRepository.getById(maSinhVien);
        if(sinhVien.getLop().contains("CNPM")){
            maBoMon ="BOMON10";
        }else if(sinhVien.getLop().contains("HTTT")){
            maBoMon ="BOMON09";
        }
        System.out.println(sinhVienKhoa.getNienKhoa() + "");
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
        monHocKiHoc =(ArrayList<MonHocKiHoc>) monHocKiHocRepository.findMonHocKiHocByKiHoc_MaKiHocAndMonHoc_BoMon_MaBoMonAndMonHoc_TenMonHocContains(maKiHoc,maBoMon,key);


        ArrayList<MonHocDTO> dsMonHocDTO = new ArrayList<MonHocDTO>();
        monHocKiHoc.forEach(monHoc->{
            MonHocDTO temp = new MonHocDTO(monHoc.getMonHoc().getMaMonHoc(), monHoc.getMonHoc().getTenMonHoc());
            dsMonHocDTO.add(temp);
        });

        res.setData(dsMonHocDTO);
        return res;
    }
}
