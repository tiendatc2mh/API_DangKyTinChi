package com.ptit.dangkytinchi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "tbl_sinh_vien", schema = "dangkytinchi")
public class SinhVien {

    @Id
    @Column(name = "masinhvien")
    private String maSinhVien;

    @Column(name = "taikhoan")
    private  String taiKhoan;

    @Column(name = "matkhau")
    private  String matKhau;

    @Column(name = "tensinhvien")
    private  String tenSinhVien;

    @Column(name = "lop")
    private  String lop;


    public SinhVien(String maSinhVien, String taiKhoan, String matKhau, String tenSinhVien, String lop) {
        this.maSinhVien = maSinhVien;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.tenSinhVien = tenSinhVien;
        this.lop = lop;
    }
    public  SinhVien(){}

}
