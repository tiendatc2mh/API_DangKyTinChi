package com.ptit.dangkytinchi.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ptit.dangkytinchi.model.KiHoc;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
public class NamHocDTO implements Serializable {
    private String maNamHoc;
    private String tenNamHoc;
    private String moTa;
    Set<KiHoc> dsKiHoc;

    public NamHocDTO(String maNamHoc, String tenNamHoc, String moTa) {
        this.maNamHoc = maNamHoc;
        this.tenNamHoc = tenNamHoc;
        this.moTa = moTa;
    }
}

