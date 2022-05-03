package com.ptit.dangkytinchi.DTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ptit.dangkytinchi.model.Khoa;
import com.ptit.dangkytinchi.model.MonHoc;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
public class BoMonDTO implements Serializable {
    private String maBoMon;
    private String tenBoMon;
    private String moTa;
    private Khoa khoa;
}
