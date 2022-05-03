package com.ptit.dangkytinchi.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ptit.dangkytinchi.model.LichHoc;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
public class TuanHocDTO implements Serializable {
    private String maTuanHoc;
    private String tenTuanHoc;
    private String moTa;
    public TuanHocDTO(String maTuanHoc, String tenTuanHoc, String moTa) {
        this.maTuanHoc = maTuanHoc;
        this.tenTuanHoc = tenTuanHoc;
        this.moTa = moTa;
    }
}
