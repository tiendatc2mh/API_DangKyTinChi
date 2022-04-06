package com.ptit.dangkytinchi.DTO;

import com.ptit.dangkytinchi.model.BoMon;
import com.ptit.dangkytinchi.model.MonHocKiHoc;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class MonHocDTO implements Serializable {
    private String maMonHoc;
    private String tenMonHoc;
    private int soTc;
    private String maBoMon;
    private BoMon boMon;
    ArrayList<MonHocKiHoc> dsMonHocKiHoc;

    public MonHocDTO(String maMonHoc, String tenMonHoc, int soTc, String maBoMon, BoMon boMon) {
        this.maMonHoc = maMonHoc;
        this.tenMonHoc = tenMonHoc;
        this.soTc = soTc;
        this.maBoMon = maBoMon;
        this.boMon = boMon;
    }

    public MonHocDTO(String maMonHoc, String tenMonHoc, int soTc) {
        this.soTc=soTc;
        this.maMonHoc = maMonHoc;
        this.tenMonHoc = tenMonHoc;
    }
}
