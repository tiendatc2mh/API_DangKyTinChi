package com.ptit.dangkytinchi.DTO;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ptit.dangkytinchi.model.LopHocPhan;
import com.ptit.dangkytinchi.model.SinhVienKhoa;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
public class DangKyHocDTO implements Serializable {

    private String maDangKyHoc;
    private SinhVienKhoa sinhVienKhoa;
    private LopHocPhan lopHocPhan;

    public DangKyHocDTO() {
    }

    public DangKyHocDTO(String maDangKyHoc, SinhVienKhoa sinhVienKhoa, LopHocPhan lopHocPhan) {
        this.maDangKyHoc = maDangKyHoc;
        this.sinhVienKhoa = sinhVienKhoa;
        this.lopHocPhan = lopHocPhan;
    }
}
