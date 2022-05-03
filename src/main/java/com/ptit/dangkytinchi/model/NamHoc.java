package com.ptit.dangkytinchi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "tbl_nam_hoc", schema = "dangkytinchi")
public class NamHoc {
    @Id
    @Column(name = "manamhoc")
    private String maNamHoc;

    @Column(name="tennamhoc")
    private String tenNamHoc;

    @Column(name="mota")
    private String moTa;

    public NamHoc() {
    }

    public NamHoc(String maNamHoc, String tenNamHoc, String moTa) {
        this.maNamHoc = maNamHoc;
        this.tenNamHoc = tenNamHoc;
        this.moTa = moTa;
    }
}
