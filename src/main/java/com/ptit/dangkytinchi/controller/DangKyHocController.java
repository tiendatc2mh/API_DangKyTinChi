package com.ptit.dangkytinchi.controller;

import com.ptit.dangkytinchi.DTO.DangKyHocDTO;
import com.ptit.dangkytinchi.DTO.LichHocDTO;
import com.ptit.dangkytinchi.DTO.LopHocPhanDTO;
import com.ptit.dangkytinchi.exception.ResponeAPI;
import com.ptit.dangkytinchi.model.DangKyHoc;
import com.ptit.dangkytinchi.model.LichHoc;
import com.ptit.dangkytinchi.model.LopHocPhan;
import com.ptit.dangkytinchi.model.SinhVienKhoa;
import com.ptit.dangkytinchi.repository.DangKyHocRepository;
import com.ptit.dangkytinchi.repository.LichHocRepository;
import com.ptit.dangkytinchi.repository.LopHocPhanRepository;
import com.ptit.dangkytinchi.repository.SinhVienKhoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/dangkytinchi", produces = "application/json")
@CrossOrigin(origins = "*")
public class DangKyHocController {
    @Autowired
    private DangKyHocRepository dangKyHocRepository;
    @Autowired
    private SinhVienKhoaRepository sinhVienKhoaRepository;
    @Autowired
    private LopHocPhanRepository lopHocPhanRepository;
    @Autowired
    private LichHocRepository lichHocRepository;

    public DangKyHocController(DangKyHocRepository dangKyHocRepository, SinhVienKhoaRepository sinhVienKhoaRepository,
                               LopHocPhanRepository lopHocPhanRepository, LichHocRepository lichHocRepository) {
        this.dangKyHocRepository = dangKyHocRepository;
        this.sinhVienKhoaRepository = sinhVienKhoaRepository;
        this.lopHocPhanRepository = lopHocPhanRepository;
        this.lichHocRepository = lichHocRepository;
    }
    @PostMapping("/luudangky/{maSinhVien}")
    private ResponeAPI luuDangKy(@RequestBody List<LinkedHashMap> object, @PathVariable String maSinhVien) {

        ResponeAPI res = new ResponeAPI();
        ArrayList<String> dsmaLopHocPhanDTO = new ArrayList<String>();
        object.forEach(obj -> {
            dsmaLopHocPhanDTO.add(obj.get("maLopHocPhan").toString().trim());
        });
        ArrayList<LopHocPhan> dsLopHocPhan = new ArrayList<LopHocPhan>();


        dsmaLopHocPhanDTO.forEach(list -> {
            dsLopHocPhan.add(lopHocPhanRepository.findLopHocPhanByMaLopHocPhan(list));
        });
        for (int i = 0; i < dsLopHocPhan.size(); i++) {
            LopHocPhan temp = dsLopHocPhan.get(i);
            ArrayList<DangKyHoc> dsDangKy = (ArrayList<DangKyHoc>) dangKyHocRepository.findDangKyHocByLopHocPhan_MaLopHocPhan(temp.getMaLopHocPhan());
            int siSoThucTe = dsDangKy.size();
            if (siSoThucTe + 1 > temp.getSiSoToiDa()) {

                res.setError("Môn học " + temp.getMonHocKiHoc().getMonHoc().getTenMonHoc() + "đã hết số lượng đăng ký!!!");
                return res;
            }
        }
//         check trung lich hoc
        ArrayList<LopHocPhanDTO> listFullDataLopHopPhan = new ArrayList<>();
        for (LopHocPhan lopHocPhan : dsLopHocPhan) {
            ArrayList<DangKyHoc> dsDangKy = (ArrayList<DangKyHoc>) dangKyHocRepository.findDangKyHocByLopHocPhan_MaLopHocPhan(lopHocPhan.getMaLopHocPhan());
            int siSoThucTe = dsDangKy.size();
            ArrayList<LichHoc> listLichHoc = lichHocRepository.findLichHocByLopHocPhan_MaLopHocPhan(lopHocPhan.getMaLopHocPhan());
            ArrayList<LichHocDTO> listLichHocDTO = new ArrayList<>();
//            listLichHoc.forEach(ls -> {
//                ls.setLopHocPhan(null);
//            });
            LopHocPhanDTO data = new LopHocPhanDTO(lopHocPhan.getMaLopHocPhan(), lopHocPhan.getTenLopHocPhan(),
                    lopHocPhan.getSiSoToiDa(), siSoThucTe, lopHocPhan.getMoTa(), null, listLichHoc);
            listFullDataLopHopPhan.add(data);
        }
        for(int i = 0; i < listFullDataLopHopPhan.size(); i++){
            for ( int j = i + 1; j < listFullDataLopHopPhan.size(); j++){
                if(listFullDataLopHopPhan.get(i).checkTrungLich(listFullDataLopHopPhan.get(j)) == false){
                    res.setData("Lỗi trùng lịch học");
                    return res;
                }
            }
        }

        SinhVienKhoa sinhVienKhoa = sinhVienKhoaRepository.findSinhVienKhoaBySinhVien_MaSinhVien(maSinhVien);
        for (int i = 0; i < dsLopHocPhan.size(); i++) {
            LopHocPhan temp = dsLopHocPhan.get(i);
            String maDangKyHoc = maSinhVien + temp.getMaLopHocPhan();

            DangKyHoc tempDKH = new DangKyHoc();
            tempDKH.setMaDangKyHoc(maDangKyHoc);
            tempDKH.setSinhVienKhoa(sinhVienKhoa);
            tempDKH.setLopHocPhan(temp);
            dangKyHocRepository.save(tempDKH);
        }
        res.setData("Đăng ký thành công!");
        return res;

    }
    @GetMapping("/luudangky/{maSinhVien}")
    public ResponeAPI getDSDangKy (@PathVariable String maSinhVien){
        ResponeAPI res = new ResponeAPI();
        SinhVienKhoa sinhVienKhoa = sinhVienKhoaRepository.findSinhVienKhoaBySinhVien_MaSinhVien(maSinhVien);
        ArrayList<DangKyHocDTO> dsDangKyHocDTO = new ArrayList<DangKyHocDTO>();
        ArrayList<DangKyHoc> dsDangKyHoc = new ArrayList<DangKyHoc>();
        dsDangKyHoc = ( ArrayList<DangKyHoc>) dangKyHocRepository.findDangKyHocBySinhVienKhoa_MaSinhVienKhoa(sinhVienKhoa.getMaSinhVienKhoa());
        dsDangKyHoc.forEach(dangKyHoc -> {
            DangKyHocDTO temp = new DangKyHocDTO(dangKyHoc.getMaDangKyHoc(), dangKyHoc.getSinhVienKhoa(), dangKyHoc.getLopHocPhan());
            dsDangKyHocDTO.add(temp);
        });
        res.setData(dsDangKyHocDTO);
        return res;
    }
    @PostMapping("xoadangky/{maSinhVien}")
    public ResponeAPI xoaDangKyHoc(@RequestBody List<LinkedHashMap> object, @PathVariable String maSinhVien){
        ResponeAPI res = new ResponeAPI();
        ArrayList<String> dsmaLopHocPhan = new ArrayList<String>();
        object.forEach(obj -> {
            dsmaLopHocPhan.add(obj.get("maLopHocPhan").toString().trim());
        });
        SinhVienKhoa sinhVienKhoa = sinhVienKhoaRepository.findSinhVienKhoaBySinhVien_MaSinhVien(maSinhVien);
        ArrayList<DangKyHocDTO> dsDangKyHocDTO = new ArrayList<DangKyHocDTO>();
        ArrayList<DangKyHoc> dsDangKyHoc = new ArrayList<DangKyHoc>();
        dsmaLopHocPhan.forEach(maLopHocPhan ->{
            DangKyHoc temp =  dangKyHocRepository.findDangKyHocBySinhVienKhoa_MaSinhVienKhoaAndLopHocPhan_MaLopHocPhan(sinhVienKhoa.getMaSinhVienKhoa(), maLopHocPhan);
                dangKyHocRepository.delete(temp);
        } );
        res.setData("Xóa đăng ký thành công!");
        return res;
    }

    @PostMapping("xemthoikhoabieu/{maSinhVien}")
    public ResponeAPI xemThoiKhoaBieu(@RequestBody LinkedHashMap object, @PathVariable String maSinhVien){
        ResponeAPI res = new ResponeAPI();
        String maTuanHoc = object.get("maTuanHoc").toString().trim();
        SinhVienKhoa sinhVienKhoa = sinhVienKhoaRepository.findSinhVienKhoaBySinhVien_MaSinhVien(maSinhVien);

        ArrayList<DangKyHoc> dsDangKyHoc = new ArrayList<DangKyHoc>();
        dsDangKyHoc= (ArrayList<DangKyHoc>) dangKyHocRepository.findDangKyHocBySinhVienKhoa_MaSinhVienKhoa(sinhVienKhoa.getMaSinhVienKhoa());
        ArrayList<LichHocDTO> data = new ArrayList<LichHocDTO>();
        dsDangKyHoc.forEach(dangKyHoc -> {
            ArrayList<LichHoc> dsLichHoc = ( ArrayList<LichHoc>)lichHocRepository.findLichHocByLopHocPhan_MaLopHocPhanAndTuanHoc_MaTuanHoc(dangKyHoc.getLopHocPhan().getMaLopHocPhan(), maTuanHoc);
            if(dsLichHoc.size()>0){
                LichHoc lichHoc = dsLichHoc.get(0);
                LichHocDTO temp = new LichHocDTO(lichHoc.getMaLichHoc(), lichHoc.getTenLichHoc(), lichHoc.getGiangvien(),
                        lichHoc.getLopHocPhan(), lichHoc.getPhongHoc(), lichHoc.getTuanHoc(), lichHoc.getNgayHoc(), lichHoc.getKipHoc());
                data.add(temp);
            }
        });
        res.setData(data);
        return res;
    }

}
