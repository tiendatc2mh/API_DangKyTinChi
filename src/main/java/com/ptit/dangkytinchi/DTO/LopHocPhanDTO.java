package com.ptit.dangkytinchi.DTO;

import com.ptit.dangkytinchi.model.LichHoc;
import com.ptit.dangkytinchi.model.MonHocKiHoc;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class LopHocPhanDTO implements Serializable {
    private String maLopHocPhan;
    private String tenLopHocPhan;
    private int siSoToiDa;
    private int siSoThucTe;
    private String moTa;
    private MonHocKiHoc monHocKiHoc;
    ArrayList<LichHoc> dsLichHoc;

    public LopHocPhanDTO() {
    }

    public LopHocPhanDTO(String maLopHocPhan, String tenLopHocPhan, int siSoToiDa, int siSoThucTe,
                         String moTa, MonHocKiHoc monHocKiHoc, ArrayList<LichHoc> dsLichHoc) {
        this.maLopHocPhan = maLopHocPhan;
        this.tenLopHocPhan = tenLopHocPhan;
        this.siSoToiDa = siSoToiDa;
        this.siSoThucTe = siSoThucTe;
        this.moTa = moTa;
        this.monHocKiHoc = monHocKiHoc;
        this.dsLichHoc = dsLichHoc;
    }
    private boolean checkTrungLich(LichHoc check1, LichHoc check2){
        if (check1.getTuanHoc().getMaTuanHoc() == check2.getTuanHoc().getMaTuanHoc()) {
            if(check1.getNgayHoc().getMaNgayHoc() == check2.getNgayHoc().getMaNgayHoc()){
                if(check1.getKipHoc().getMaKipHoc() == check2.getKipHoc().getMaKipHoc()){
                    return  false;
                }
            }
        }
        return  true;
    }
    public boolean checkTrungLich(LopHocPhanDTO dataCheck){
        for (LichHoc lichHoc : this.dsLichHoc) {
            for (LichHoc hoc : dataCheck.getDsLichHoc()) {
                if(checkTrungLich(lichHoc, hoc) == false) {
                    return false;
                }
            }
        }
        return  true;
    }

}
