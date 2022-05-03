package com.ptit.dangkytinchi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
//@AllArgsConstructor
@Table(name = "tbl_ki_hoc", schema = "dangkytinchi")
public class KiHoc {
    @Id
    @Column(name="makihoc")
    private String maKiHoc;
    @Column(name="danghoc")
    private boolean dangHoc;
    @Column(name="dangdangky")
    private boolean dangDangKy;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "manamhoc", nullable = false)
    private NamHoc namHoc;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "mahocki", nullable = false)
    private HocKi hocKi;

    public KiHoc() {
    }

    public KiHoc(String maKiHoc, boolean dangHoc, boolean dangDangKy, NamHoc namHoc, HocKi hocKi) {
        this.maKiHoc = maKiHoc;
        this.dangHoc = dangHoc;
        this.dangDangKy = dangDangKy;
        this.namHoc = namHoc;
        this.hocKi = hocKi;
    }
}
