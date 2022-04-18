package com.ptit.dangkytinchi.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ptit.dangkytinchi.model.KiHoc;
import com.ptit.dangkytinchi.model.LopHocPhan;
import com.ptit.dangkytinchi.model.MonHoc;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
public class MonHocKiHocDTO implements Serializable {

    private String maMocHocKiHoc;
    private MonHoc monHoc;
    private KiHoc kiHoc;
    Set<LopHocPhan> dsLopHocPhan;
}

