package com.ptit.dangkytinchi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tbl_dang_ky_hoc", schema = "dangkytinchi")
public class DangKyHoc {
    @Id
    @Column(name="madangkyhoc")
    private String maDangKyHoc;

//    @JsonManagedReference(value = "sinhvienkhoa-dangkyhoc")
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "masinhvienkhoa", nullable = false)
    private SinhVienKhoa sinhVienKhoa;

//    @JsonManagedReference(value = "lophocphan-dangkyhoc")
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "malophocphan", nullable = false)
    private LopHocPhan lopHocPhan;
    public  DangKyHoc(){}

    public DangKyHoc(String maDangKyHoc, SinhVienKhoa sinhVienKhoa, LopHocPhan lopHocPhan) {
        this.maDangKyHoc = maDangKyHoc;
        this.sinhVienKhoa = sinhVienKhoa;
        this.lopHocPhan = lopHocPhan;
    }
}
