package com.ptit.dangkytinchi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "tbl_lop_hoc_phan", schema = "dangkytinchi")
public class LopHocPhan {

    @Id
    @Column(name="malophocphan")
    private String maLopHocPhan;

    @Column(name="tenlophocphan")
    private String tenLopHocPhan;

    @Column(name="sisotoida")
    private int siSoToiDa;

    @Column(name="mota")
    private String moTa;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "mamonhockihoc", nullable = false)
    private MonHocKiHoc monHocKiHoc;

    public LopHocPhan() {
    }

    public LopHocPhan(String maLopHocPhan, String tenLopHocPhan, int siSoToiDa, String moTa, MonHocKiHoc monHocKiHoc) {
        this.maLopHocPhan = maLopHocPhan;
        this.tenLopHocPhan = tenLopHocPhan;
        this.siSoToiDa = siSoToiDa;
        this.moTa = moTa;
        this.monHocKiHoc = monHocKiHoc;
    }
}
