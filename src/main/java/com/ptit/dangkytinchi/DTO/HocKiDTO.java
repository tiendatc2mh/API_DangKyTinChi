package com.ptit.dangkytinchi.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ptit.dangkytinchi.model.KiHoc;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
public class HocKiDTO implements Serializable {
    private String maHocKi;
    private String tenHocKi;
    private String moTa;
    Set<KiHoc> dsKiHoc;
}
