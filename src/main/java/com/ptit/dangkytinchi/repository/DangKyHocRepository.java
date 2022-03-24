package com.ptit.dangkytinchi.repository;

import com.ptit.dangkytinchi.model.DangKyHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DangKyHocRepository extends JpaRepository<DangKyHoc, String> {
    List<DangKyHoc> findDangKyHocByLopHocPhan_MaLopHocPhan(String maLopHocPhan);
}
