
package com.ptit.dangkytinchi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@Table(name = "tbl_toa_nha", schema = "dangkytinchi")
public class ToaNha {

    @Id
    @Column(name = "matoanha")
    private String maToaNha;

    @Column(name = "tentoanha")
    private String tenToaNha;

    @Column(name = "mota")
    private String moTa;

  //  @JsonBackReference(value = "toanha-phonghoc")
    @JsonBackReference
    @OneToMany(mappedBy = "toaNha", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<PhongHoc> dsPhongHoc;

    public ToaNha() {
    }

    public ToaNha(String maToaNha, String tenToaNha, String moTa) {
        this.maToaNha = maToaNha;
        this.tenToaNha = tenToaNha;
        this.moTa = moTa;
    }
}

