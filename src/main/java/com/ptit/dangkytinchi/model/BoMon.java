package com.ptit.dangkytinchi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "tbl_bo_mon", schema = "dangkytinchi")
public class BoMon {

    @Id
    @Column(name="mabomon")
    private String maBoMon;

    @Column(name="tenbomon")
    private String tenBoMon;

    @Column(name="mota")
    private String moTa;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "makhoa", nullable = false)
    private Khoa khoa;

    public BoMon() {
    }

    public BoMon(String maBoMon, String tenBoMon, String moTa, Khoa khoa) {
        this.maBoMon = maBoMon;
        this.tenBoMon = tenBoMon;
        this.moTa = moTa;
        this.khoa = khoa;
    }
}
