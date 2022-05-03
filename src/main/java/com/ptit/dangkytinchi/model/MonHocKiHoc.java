package com.ptit.dangkytinchi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
//@AllArgsConstructor
@Table(name = "tbl_mon_hoc_ki_hoc", schema = "dangkytinchi")
public class MonHocKiHoc {
    @Id
    @Column(name = "mamonhockihoc")
    private String maMonHocKiHoc;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "mamonhoc", nullable = false)
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private MonHoc monHoc;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "makihoc", nullable = false)
    private KiHoc kiHoc;

    public MonHocKiHoc() {
    }

    public MonHocKiHoc(String maMonHocKiHoc, MonHoc monHoc, KiHoc kiHoc) {
        this.maMonHocKiHoc = maMonHocKiHoc;
        this.monHoc = monHoc;
        this.kiHoc = kiHoc;
    }
}
