package com.ptit.dangkytinchi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "tbl_mon_hoc", schema = "dangkytinchi")
public class MonHoc {
    @Id
    @Column(name="mamonhoc")
    private String maMonHoc;

    @Column(name = "tenmonhoc")
    private String tenMonHoc;

    @Column(name="sotc")
    private int soTc;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="mabomon", nullable = false)
    private BoMon boMon;

    public MonHoc() {
    }

    public MonHoc(String maMonHoc, String tenMonHoc, int soTc, BoMon boMon) {
        this.maMonHoc = maMonHoc;
        this.tenMonHoc = tenMonHoc;
        this.soTc = soTc;
        this.boMon = boMon;
    }
}
