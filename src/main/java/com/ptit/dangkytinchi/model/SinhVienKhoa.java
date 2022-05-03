package com.ptit.dangkytinchi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

import static org.hibernate.annotations.FetchMode.SELECT;
@Data
@Entity
@Table(name = "tbl_sinh_vien_khoa", schema = "dangkytinchi")
public class SinhVienKhoa {

    @Id
    @Column(name = "masinhvienkhoa")
    private String maSinhVienKhoa;

    @Column(name = "nienkhoa")
    private  String nienKhoa;

    @Column(name = "danghoc")
    private boolean dangHoc;

//    @JsonManagedReference(value = "khoa-sinhvienkhoa")
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="makhoa", nullable = false)
    private Khoa khoa;



//    @JsonManagedReference(value = "sinhvien-sinhvienkhoa")
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "masinhvien", nullable = false)
    private SinhVien sinhVien;
    //    @JsonBackReference(value = "sinhvienkhoa-dangkyhoc")
    @JsonBackReference
    @OneToMany(mappedBy = "sinhVienKhoa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<DangKyHoc> dsDangKyHoc;

    public SinhVienKhoa() {

    }

    public SinhVienKhoa(String maSinhVienKhoa, String nienKhoa, boolean dangHoc, Khoa khoa, SinhVien sinhVien) {
        this.maSinhVienKhoa = maSinhVienKhoa;
        this.nienKhoa = nienKhoa;
        this.dangHoc = dangHoc;
        this.khoa = khoa;
        this.sinhVien = sinhVien;
    }
}
