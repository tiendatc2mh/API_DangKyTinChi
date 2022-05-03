package com.ptit.dangkytinchi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
//@AllArgsConstructor
@Table(name = "tbl_phong_hoc", schema = "dangkytinchi")
public class PhongHoc {

    @Id
    @Column(name = "maphonghoc")
    private String maPhongHoc;

    @Column(name="tenphonghoc")
    private String tenPhongHoc;

    @Column(name="succhua")
    private int sucChua;

   // @JsonManagedReference(value = "toanha-phonghoc")
   @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "matoanha", nullable = false)
    private ToaNha toaNha;

   // @JsonBackReference(value = "phonghoc-lichhoc")
//    @JsonBackReference
//    @OneToMany(mappedBy = "phongHoc", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    Set<LichHoc> dsLichHoc;

    public PhongHoc() {
    }

    public PhongHoc(String maPhongHoc, String tenPhongHoc, int sucChua, ToaNha toaNha) {
        this.maPhongHoc = maPhongHoc;
        this.tenPhongHoc = tenPhongHoc;
        this.sucChua = sucChua;
        this.toaNha = toaNha;
    }
}
