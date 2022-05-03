package com.ptit.dangkytinchi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
//@AllArgsConstructor
@Table(name = "tbl_khoa", schema = "dangkytinchi")
public class Khoa {

    @Id
    @Column(name="makhoa")
    private String maKhoa;

    @Column(name="tenkhoa")
    private String tenKhoa;

    @Column(name="mota")
    private String moTa;

//    @JsonBackReference(value = "khoa-bomon")
//    @JsonBackReference
//    @OneToMany(mappedBy = "khoa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    Set<BoMon> dsBoMon;

//    @JsonBackReference(value = "khoa-sinhvienkhoa")
//    @JsonBackReference
//    @OneToMany(mappedBy = "khoa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    Set<SinhVienKhoa> dsSinhVienKhoa;

    public Khoa() {
    }

    public Khoa(String maKhoa, String tenKhoa, String moTa) {
        this.maKhoa = maKhoa;
        this.tenKhoa = tenKhoa;
        this.moTa = moTa;
    }
}
