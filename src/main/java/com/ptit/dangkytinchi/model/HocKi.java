package com.ptit.dangkytinchi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
//@AllArgsConstructor
@Table(name = "tbl_hoc_ki", schema = "dangkytinchi")
public class HocKi {
    @Id
    @Column(name="mahocki")
    private String maHocKi;

    @Column(name="tenhocki")
    private String tenHocKi;

    @Column(name="mota")
    private String moTa;

//    @JsonBackReference(value = "hocki-kihoc")
//    @JsonBackReference
//    @OneToMany(mappedBy = "hocKi", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    Set<KiHoc> dsKiHoc;

    public HocKi() {
    }

    public HocKi(String maHocKi, String tenHocKi, String moTa) {
        this.maHocKi = maHocKi;
        this.tenHocKi = tenHocKi;
        this.moTa = moTa;
    }
}
