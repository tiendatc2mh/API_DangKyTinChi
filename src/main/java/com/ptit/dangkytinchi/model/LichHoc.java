package com.ptit.dangkytinchi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tbl_lich_hoc", schema = "dangkytinchi")
public class LichHoc {

    @Id
    @Column(name="malichhoc")
    private String maLichHoc;

    @Column(name="tenlichhoc")
    private String tenLichHoc;

    @Column(name="giangvien")
    private String giangvien;

//    @JsonManagedReference(value = "lophocphan-lichhoc")
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "malophocphan", nullable = false)
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private LopHocPhan lopHocPhan;

//    @JsonManagedReference(value = "phonghoc-lichhoc")
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "maphonghoc", nullable = false)
    private PhongHoc phongHoc;

//    @JsonManagedReference(value = "tuanhoc-lichhoc")
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "matuanhoc", nullable = false)
    private TuanHoc tuanHoc;

//    @JsonManagedReference(value = "ngayhoc-lichhoc")
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "mangayhoc", nullable = false)
    private NgayHoc ngayHoc;

//    @JsonManagedReference(value = "kiphoc-lichhoc")
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "makiphoc", nullable = false)
    private KipHoc kipHoc;

}
