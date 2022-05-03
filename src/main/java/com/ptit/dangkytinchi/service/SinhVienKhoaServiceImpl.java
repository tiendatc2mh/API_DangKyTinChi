package com.ptit.dangkytinchi.service;

import com.ptit.dangkytinchi.model.SinhVienKhoa;
import com.ptit.dangkytinchi.repository.SinhVienKhoaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class SinhVienKhoaServiceImpl implements SinhVienKhoaService {
    private SinhVienKhoaRepository sinhVienKhoaRepository;

    public SinhVienKhoaServiceImpl(SinhVienKhoaRepository sinhVienKhoaRepository) {
        this.sinhVienKhoaRepository = sinhVienKhoaRepository;
    }

    @Override
    public SinhVienKhoa timKiemSinhVienKhoaTheoMaSinhVien(String maSinhVien) {
       return sinhVienKhoaRepository.findSinhVienKhoaBySinhVien_MaSinhVien(maSinhVien);
    }

    @Override
    public String layMaKiHocCuaSinhVienKhoa(SinhVienKhoa sinhVienKhoa) {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int thang = localDate.getMonthValue();
        int nam = localDate.getYear();
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

        }
        return maKiHoc;
    }
}
