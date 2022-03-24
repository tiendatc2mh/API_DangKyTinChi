package com.ptit.dangkytinchi.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ptit.dangkytinchi.model.KiHoc;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
public class NamHocDTO implements Serializable {
    private String maNamHoc;
    private String tenNamHoc;
    private String moTa;
    Set<KiHoc> dsKiHoc;
}
