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
//@AllArgsConstructor
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

   // @JsonManagedReference(value = "monhockihoc-lophocphan")
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "mamonhockihoc", nullable = false)
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private MonHocKiHoc monHocKiHoc;


//    @JsonBackReference(value = "lophocphan-lichhoc")
//    @JsonBackReference
//    @OneToMany(mappedBy = "lopHocPhan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<LichHoc> dsLichHoc;


//    @JsonBackReference(value = "lophocphan-dangkyhoc")
//    @JsonBackReference
//    @OneToMany(mappedBy = "lopHocPhan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<DangKyHoc> dsDangKyHoc;

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
