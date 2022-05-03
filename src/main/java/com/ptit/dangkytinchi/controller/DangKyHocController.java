package com.ptit.dangkytinchi.controller;

import com.ptit.dangkytinchi.DTO.DangKyHocDTO;
import com.ptit.dangkytinchi.DTO.LichHocDTO;
import com.ptit.dangkytinchi.model.*;
import com.ptit.dangkytinchi.response.ResponeAPI;
import com.ptit.dangkytinchi.service.DangKyHocService;
import com.ptit.dangkytinchi.service.LichHocService;
import com.ptit.dangkytinchi.service.LopHocPhanService;
import com.ptit.dangkytinchi.service.SinhVienKhoaService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/dangkytinchi", produces = "application/json")
@CrossOrigin(origins = "*")
public class DangKyHocController {

    private SinhVienKhoaService sinhVienKhoaService;
    private LichHocService lichHocService;
    private DangKyHocService dangKyHocService;
    private LopHocPhanService lopHocPhanService;

    public DangKyHocController(SinhVienKhoaService sinhVienKhoaService, LichHocService lichHocService, DangKyHocService dangKyHocService, LopHocPhanService lopHocPhanService) {
        this.sinhVienKhoaService = sinhVienKhoaService;
        this.lichHocService = lichHocService;
        this.dangKyHocService = dangKyHocService;
        this.lopHocPhanService = lopHocPhanService;
    }

    //luu dang ky
    @PostMapping("/luudangky/{maSinhVien}")
    private ResponeAPI luuDangKy(@RequestBody List<LinkedHashMap> object, @PathVariable String maSinhVien) {

        ResponeAPI res = new ResponeAPI();
        ArrayList<String> dsMaLopHocPhan = new ArrayList<String>();
        object.forEach(obj -> {
            dsMaLopHocPhan.add(obj.get("maLopHocPhan").toString().trim());
        });
        ArrayList<LopHocPhan> dsLopHocPhanChuaDangKy = new ArrayList<LopHocPhan>();
        SinhVienKhoa sinhVienKhoa = sinhVienKhoaService.timKiemSinhVienKhoaTheoMaSinhVien(maSinhVien);
        dsMaLopHocPhan.forEach(list -> {
            dsLopHocPhanChuaDangKy.add(lopHocPhanService.timKiemLopHocPhanTheoMaLopHocPhan(list));
        });

        //check si so con lai cua lop hoc phan
        for (int i = 0; i < dsLopHocPhanChuaDangKy.size(); i++) {
            LopHocPhan temp = dsLopHocPhanChuaDangKy.get(i);
            int siSoThucTe = dangKyHocService.laySiSoThucTeCuaLopHocPhan(temp.getMaLopHocPhan());
            if (siSoThucTe + 1 > temp.getSiSoToiDa()) {
                res.setError("Môn học " + temp.getMonHocKiHoc().getMonHoc().getTenMonHoc() + " đã hết số lượng đăng ký!!!");
                return res;
            }
        }
        //check trung thoi khoa bieu
        ArrayList<LichHocSoSanh> dsLichHocSoSanhDaDangKy = new ArrayList<>();
        ArrayList<DangKyHoc> dsDangKyHocDaDangKy = dangKyHocService.timKiemDangKyHocCuaSinhVien(sinhVienKhoa.getMaSinhVienKhoa());
        dsDangKyHocDaDangKy.forEach(dangKyHoc -> {
            ArrayList<LichHoc> dsLichHocDaDangKy = lichHocService.timKiemLichHocCuaLopHocPhan(dangKyHoc.getLopHocPhan().getMaLopHocPhan());
            dsLichHocDaDangKy.forEach(lichHoc -> {
                dsLichHocSoSanhDaDangKy.add(new LichHocSoSanh(lichHoc.getTuanHoc().getMaTuanHoc(),
                        lichHoc.getNgayHoc().getMaNgayHoc(),
                        lichHoc.getKipHoc().getMaKipHoc()));
            });
        });
        if (dsLichHocSoSanhDaDangKy.size() > 0) {
            ArrayList<LichHocSoSanh> dsLichHocSoSanhChuaDangKy = new ArrayList<>();
            dsMaLopHocPhan.forEach(maLopHocPhan -> {
                ArrayList<LichHoc> dsLichHocChuaDangKy = lichHocService.timKiemLichHocCuaLopHocPhan(maLopHocPhan);
                dsLichHocChuaDangKy.forEach(lichHoc -> {
                    dsLichHocSoSanhChuaDangKy.add(new LichHocSoSanh(lichHoc.getTuanHoc().getMaTuanHoc(),
                            lichHoc.getNgayHoc().getMaNgayHoc(),
                            lichHoc.getKipHoc().getMaKipHoc()));
                });
            });
            dsLichHocSoSanhChuaDangKy.retainAll(dsLichHocSoSanhDaDangKy);
            if (dsLichHocSoSanhChuaDangKy.size() > 0) {
                res.setData("Lỗi trùng lịch học");
                return res;
            }
        }

        dsLopHocPhanChuaDangKy.forEach(lopHocPhan -> {
            String maDangKyHoc = maSinhVien + lopHocPhan.getMaLopHocPhan();
            DangKyHoc tempDKH = new DangKyHoc();
            tempDKH.setMaDangKyHoc(maDangKyHoc);
            tempDKH.setSinhVienKhoa(sinhVienKhoa);
            tempDKH.setLopHocPhan(lopHocPhan);
            dangKyHocService.luuDangKy(tempDKH);
        });
        res.setData("Đăng ký thành công!");
        return res;

    }

    //lay tat ca lop hoc phan da dang ky cua sinh vien
    @GetMapping("/luudangky/{maSinhVien}")
    public ResponeAPI getDSDangKy(@PathVariable String maSinhVien) {
        ResponeAPI res = new ResponeAPI();
        SinhVienKhoa sinhVienKhoa = sinhVienKhoaService.timKiemSinhVienKhoaTheoMaSinhVien(maSinhVien);
        ArrayList<DangKyHocDTO> dsDangKyHocDTO = new ArrayList<DangKyHocDTO>();
        ArrayList<DangKyHoc> dsDangKyHoc = new ArrayList<DangKyHoc>();
        dsDangKyHoc = dangKyHocService.timKiemDangKyHocCuaSinhVien(sinhVienKhoa.getMaSinhVienKhoa());
        dsDangKyHoc.forEach(dangKyHoc -> {
            DangKyHocDTO temp = new DangKyHocDTO(dangKyHoc.getMaDangKyHoc(), dangKyHoc.getSinhVienKhoa(), dangKyHoc.getLopHocPhan());
            dsDangKyHocDTO.add(temp);
        });
        res.setData(dsDangKyHocDTO);
        return res;
    }

    //    //xoa dang ky
    @PostMapping("xoadangky/{maSinhVien}")
    public ResponeAPI xoaDangKyHoc(@RequestBody List<LinkedHashMap> object, @PathVariable String maSinhVien) {
        ResponeAPI res = new ResponeAPI();
        ArrayList<String> dsMaLopHocPhan = new ArrayList<String>();
        object.forEach(obj -> {
            dsMaLopHocPhan.add(obj.get("maLopHocPhan").toString().trim());
        });
        SinhVienKhoa sinhVienKhoa = sinhVienKhoaService.timKiemSinhVienKhoaTheoMaSinhVien(maSinhVien);
        dsMaLopHocPhan.forEach(maLopHocPhan -> {
            DangKyHoc temp = dangKyHocService.timKiemDangKyHocTheoSinhVienKhoaVaMaLopHocPhan(sinhVienKhoa.getMaSinhVienKhoa(), maLopHocPhan);
            dangKyHocService.xoaDangKy(temp);
        });
        res.setData("Xóa đăng ký thành công!");
        return res;
    }

    //xem thoi khoa bieu theo tuan cua sinh vien
    @PostMapping("xemthoikhoabieu/{maSinhVien}")
    public ResponeAPI xemThoiKhoaBieu(@RequestBody LinkedHashMap object, @PathVariable String maSinhVien) {
        ResponeAPI res = new ResponeAPI();
        String maTuanHoc = object.get("maTuanHoc").toString().trim();
        SinhVienKhoa sinhVienKhoa = sinhVienKhoaService.timKiemSinhVienKhoaTheoMaSinhVien(maSinhVien);
        ArrayList<LichHoc> dsLichHoc = lichHocService.timKiemLichHocCuaSinhVienTheoTuan(sinhVienKhoa.getMaSinhVienKhoa(), maTuanHoc);
        ArrayList<LichHocDTO> ketQua = new ArrayList<>();
        dsLichHoc.forEach(lichHoc -> {
            LichHocDTO temp = new LichHocDTO(lichHoc.getMaLichHoc(), lichHoc.getTenLichHoc(), lichHoc.getGiangvien(), lichHoc.getLopHocPhan(), lichHoc.getPhongHoc(), lichHoc.getTuanHoc(), lichHoc.getNgayHoc(), lichHoc.getKipHoc());
            ketQua.add(temp);
        });


        res.setData(ketQua);
        return res;
    }

    @Data
    public class LichHocSoSanh {
        private String maTuanHoc;
        private String maNgayHoc;
        private String maKipHoc;

        public LichHocSoSanh() {
        }

        public LichHocSoSanh(String maTuanHoc, String maNgayHoc, String maKipHoc) {
            this.maTuanHoc = maTuanHoc;
            this.maNgayHoc = maNgayHoc;
            this.maKipHoc = maKipHoc;
        }

        @Override
        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
            LichHocSoSanh that = (LichHocSoSanh) o;
            return maTuanHoc.equals(that.maTuanHoc) && maNgayHoc.equals(that.maNgayHoc) && maKipHoc.equals(that.maKipHoc);
        }

    }
}
