package com.ptit.dangkytinchi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@Table(name = "tbl_tuan_hoc", schema = "dangkytinchi")
public class TuanHoc {

    @Id
    @Column(name="matuanhoc")
    private String maTuanHoc;

    @Column(name = "tentuanhoc")
    private String tenTuanHoc;

    @Column(name = "mota")
    private String moTa;

   // @JsonBackReference(value = "tuanhoc-lichhoc")
    @JsonBackReference
    @OneToMany(mappedBy = "tuanHoc", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<LichHoc> dsLichHoc;

    public TuanHoc() {
    }
}
