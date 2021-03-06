package com.ptit.dangkytinchi.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ptit.dangkytinchi.model.BoMon;
import com.ptit.dangkytinchi.model.SinhVienKhoa;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
public class KhoaDTO implements Serializable {

    private String maKhoa;
    private String tenKhoa;
    private String moTa;
    Set<BoMon> dsBoMon;
    Set<SinhVienKhoa> dsSinhVienKhoa;
}
