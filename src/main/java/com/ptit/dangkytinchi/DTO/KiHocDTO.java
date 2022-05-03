package com.ptit.dangkytinchi.DTO;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ptit.dangkytinchi.model.HocKi;
import com.ptit.dangkytinchi.model.MonHocKiHoc;
import com.ptit.dangkytinchi.model.NamHoc;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
public class KiHocDTO implements Serializable {
    private String maKiHoc;
    private boolean dangHoc;
    private boolean dangDangKy;
    private NamHoc namHoc;
    private HocKi hocKi;

    public KiHocDTO(String maKiHoc, boolean dangHoc, boolean dangDangKy, NamHoc namHoc, HocKi hocKi) {
        this.maKiHoc = maKiHoc;
        this.dangHoc = dangHoc;
        this.dangDangKy = dangDangKy;
        this.namHoc = namHoc;
        this.hocKi = hocKi;
    }
}
