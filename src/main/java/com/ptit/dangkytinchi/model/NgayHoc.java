package com.ptit.dangkytinchi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
//@AllArgsConstructor
@Table(name = "tbl_ngay_hoc", schema = "dangkytinchi")
public class NgayHoc {

    @Id
    @Column(name = "mangayhoc")
    private String maNgayHoc;

    @Column(name="tenngayhoc")
    private String tenNgayHoc;

    @Column(name="mota")
    private String moTa;


    public NgayHoc() {
    }

    public NgayHoc(String maNgayHoc, String tenNgayHoc, String moTa) {
        this.maNgayHoc = maNgayHoc;
        this.tenNgayHoc = tenNgayHoc;
        this.moTa = moTa;
    }
}
