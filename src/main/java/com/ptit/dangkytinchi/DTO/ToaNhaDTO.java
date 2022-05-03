package com.ptit.dangkytinchi.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ptit.dangkytinchi.model.PhongHoc;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
public class ToaNhaDTO implements Serializable {
    private String maToaNha;
    private String tenToaNha;
    private String moTa;
}
