package com.ptit.dangkytinchi.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.ptit.dangkytinchi.DTO.*;
import com.ptit.dangkytinchi.DangKyTinChiApplication;
import com.ptit.dangkytinchi.model.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DangKyTinChiApplication.class)
@WebAppConfiguration
public abstract class AbstractTest {
    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    public SinhVienDTO toSinhVienDTO(LinkedHashMap<String, String> data){
        SinhVienDTO dataout = new SinhVienDTO();
        dataout.setMaSinhVien(data.get("maSinhVien"));
        dataout.setTenSinhVien(data.get("tenSinhVien"));
        dataout.setTaiKhoan(data.get("taiKhoan"));
        dataout.setMatKhau(data.get("matKhau"));
        dataout.setMatKhauMoi(data.get("matKhauMoi"));
        dataout.setLop(data.get("lop"));
        return dataout;
    }

    public SinhVien toSinhVien(LinkedHashMap<String, String> data){
        SinhVien dataout = new SinhVien();
        dataout.setMaSinhVien(data.get("maSinhVien"));
        dataout.setTenSinhVien(data.get("tenSinhVien"));
        dataout.setTaiKhoan(data.get("taiKhoan"));
        dataout.setMatKhau(data.get("matKhau"));
        dataout.setLop(data.get("lop"));
        return dataout;
    }
    public SinhVienKhoa toSinhVienKhoa(LinkedHashMap<String, Object> data){
        SinhVienKhoa dataout = new SinhVienKhoa();
        dataout.setMaSinhVienKhoa((String) data.get("maSinhVienKhoa"));
        dataout.setNienKhoa((String) data.get("nienKhoa"));
        dataout.setDangHoc((Boolean) data.get("dangHoc"));
        dataout.setKhoa(toKhoa((LinkedHashMap<String, String>)  data.get("khoa")));
        dataout.setSinhVien(toSinhVien((LinkedHashMap<String, String>) data.get("sinhVien")));
        return dataout;
    }
    public MonHocDTO toMonHocDTO(LinkedHashMap<String, Object> data){
        MonHocDTO dataout = new MonHocDTO();
        dataout.setMaMonHoc((String)(data.get("maMonHoc")));
        dataout.setTenMonHoc((String)(data.get("tenMonHoc")));
        dataout.setSoTc((Integer)(data.get("soTc")));
        dataout.setMaBoMon((String)(data.get("maBoMon")));
//        dataout.setBoMon(data.get("BoMon"));
//        dataout.setDsMonHocKiHoc(data.get("dsMonHocKiHoc"));
        return dataout;
    }

    public KipHoc toKipHoc(LinkedHashMap<String, String> data){
        KipHoc dataout = new KipHoc();
        dataout.setMaKipHoc(data.get("maKipHoc"));
        dataout.setTenKipHoc(data.get("tenKipHoc"));
        dataout.setMoTa(data.get("moTa"));
        return dataout;
    }

    public NgayHoc toNgayHoc(LinkedHashMap<String, String> data){
        NgayHoc dataout = new NgayHoc();
        dataout.setMaNgayHoc(data.get("maNgayHoc"));
        dataout.setTenNgayHoc(data.get("tenNgayHoc"));
        dataout.setMoTa(data.get("moTa"));
        return dataout;
    }

    public TuanHoc toTuanHoc(LinkedHashMap<String, String> data){
        TuanHoc dataout = new TuanHoc();
        dataout.setMaTuanHoc(data.get("maTuanHoc"));
        dataout.setTenTuanHoc(data.get("tenTuanHoc"));
        dataout.setMoTa(data.get("moTa"));
        return dataout;
    }

    public PhongHoc toPhongHoc(LinkedHashMap<String, Object> data){
        PhongHoc dataout = new PhongHoc();
        dataout.setMaPhongHoc((String) data.get("maPhongHoc"));
        dataout.setTenPhongHoc((String)data.get("tenPhongHoc"));
        dataout.setSucChua((Integer) data.get("sucChua"));
        ToaNha toaNha= new ToaNha();
        toaNha = toToaNha((LinkedHashMap<String, String>) data.get("toaNha"));
        dataout.setToaNha(toaNha);
        return dataout;
    }

    public ToaNha toToaNha(LinkedHashMap<String, String> data){
        ToaNha dataout = new ToaNha();
        dataout.setMaToaNha(data.get("maToaNha"));
        dataout.setTenToaNha(data.get("tenToaNha"));
        dataout.setMoTa(data.get("moTa"));
        return dataout;
    }
    public MonHoc toMonHoc(LinkedHashMap<String, Object> data){
        MonHoc dataout = new MonHoc();
        dataout.setMaMonHoc((String)(data.get("maMonHoc")));
        dataout.setTenMonHoc((String)(data.get("tenMonHoc")));
        dataout.setSoTc((Integer)(data.get("soTc")));
        dataout.setBoMon(toBoMon((LinkedHashMap<String, Object>) (data.get("boMon"))));
        return dataout;
    }
    public BoMon toBoMon(LinkedHashMap<String, Object> data){
        BoMon dataout = new BoMon();
        dataout.setMaBoMon((String) data.get("maBoMon"));
        dataout.setTenBoMon((String) data.get("tenBoMon"));
        dataout.setMoTa((String) data.get("moTa"));
        dataout.setKhoa(toKhoa((LinkedHashMap<String, String>) data.get("khoa")));
        return dataout;
    }
    public Khoa toKhoa(LinkedHashMap<String, String> data){
        Khoa dataout = new Khoa();
        dataout.setMaKhoa(data.get("maKhoa"));
        dataout.setTenKhoa(data.get("tenKhoa"));
        dataout.setMoTa(data.get("moTa"));
        return dataout;
    }

    public HocKi toHocKi(LinkedHashMap<String, String> data){
        HocKi dataout = new HocKi();
        dataout.setMaHocKi(data.get("maHocKi"));
        dataout.setTenHocKi(data.get("tenHocKi"));
        dataout.setMoTa(data.get("moTa"));
        return dataout;
    }

    public NamHoc toNamHoc(LinkedHashMap<String, String> data){
        NamHoc dataout = new NamHoc();
        dataout.setMaNamHoc(data.get("maNamHoc"));
        dataout.setTenNamHoc(data.get("tenNamHoc"));
        dataout.setMoTa(data.get("moTa"));
        return dataout;
    }

    public KiHoc toKiHoc(LinkedHashMap<String, Object> data){
        KiHoc dataout = new KiHoc();
        dataout.setMaKiHoc((String) data.get("maKiHoc"));
        dataout.setDangHoc((Boolean) data.get("dangHoc"));
        dataout.setDangDangKy((Boolean) data.get("dangDangKy"));
        dataout.setHocKi(toHocKi((LinkedHashMap<String, String>) data.get("hocKi")));
        dataout.setNamHoc(toNamHoc((LinkedHashMap<String, String>) data.get("namHoc")));
        return dataout;
    }

    public MonHocKiHoc toMonHocKiHoc(LinkedHashMap<String, Object> data){
        MonHocKiHoc dataout = new MonHocKiHoc();
        dataout.setMaMonHocKiHoc((String) data.get("maMonHocKiHoc"));
        dataout.setMonHoc(toMonHoc((LinkedHashMap<String, Object>) data.get("monHoc")));
        dataout.setKiHoc(toKiHoc((LinkedHashMap<String, Object>) data.get("kiHoc")));
        return dataout;
    }


    public LopHocPhan toLopHocPhan(LinkedHashMap<String, Object> data){
        LopHocPhan dataout = new LopHocPhan();
        dataout.setMaLopHocPhan((String) data.get("maLopHocPhan"));
        dataout.setTenLopHocPhan((String) data.get("tenLopHocPhan"));
        dataout.setSiSoToiDa((Integer) data.get("siSoToiDa"));
        dataout.setMoTa((String) data.get("moTa"));
        dataout.setMonHocKiHoc(toMonHocKiHoc((LinkedHashMap<String, Object>) data.get("monHocKiHoc")));
        return dataout;
    }

    public LichHoc toLichHoc(LinkedHashMap<String, Object> data){
        LichHoc dataout = new LichHoc();
        dataout.setMaLichHoc((String) data.get("maLichHoc"));
        dataout.setTenLichHoc((String) data.get("tenLichHoc"));
        dataout.setGiangvien((String) data.get("giangvien"));
        dataout.setLopHocPhan(toLopHocPhan((LinkedHashMap<String, Object>) data.get("lopHocPhan")));
        dataout.setPhongHoc(toPhongHoc((LinkedHashMap<String, Object>) data.get("phongHoc")));
        dataout.setTuanHoc(toTuanHoc((LinkedHashMap<String, String>) data.get("tuanHoc")));
        dataout.setNgayHoc(toNgayHoc((LinkedHashMap<String, String>) data.get("ngayHoc")));
        dataout.setKipHoc(toKipHoc((LinkedHashMap<String, String>) data.get("kipHoc")));
        return dataout;
    }
    public LopHocPhanDTO toLopHocPhanDTO(LinkedHashMap<String, Object> data){
        LopHocPhanDTO dataout = new LopHocPhanDTO();
        dataout.setMaLopHocPhan((String) data.get("maLopHocPhan"));
        dataout.setTenLopHocPhan((String) data.get("tenLopHocPhan"));
        dataout.setSiSoThucTe((Integer) data.get("siSoThucTe"));
        dataout.setMoTa((String) data.get("moTa"));
        dataout.setMonHocKiHoc(toMonHocKiHoc((LinkedHashMap<String, Object>) data.get("monHocKiHoc")));
        ArrayList<LichHoc> dsLichHoc = new ArrayList<LichHoc>();
        ArrayList<LinkedHashMap<String, Object>> temp = new ArrayList<LinkedHashMap<String, Object>>();
        temp = (ArrayList<LinkedHashMap<String, Object>>) data.get("dsLichHoc");
        temp.forEach(stringObjectLinkedHashMap ->{
            dsLichHoc.add(toLichHoc(stringObjectLinkedHashMap));
        } );
        dataout.setDsLichHoc(dsLichHoc);
        return dataout;
    }

    public LichHocDTO toLichHocDTO(LinkedHashMap<String, Object> data){
        LichHocDTO dataout = new LichHocDTO();
        dataout.setMaLichHoc((String) data.get("maLichHoc"));
        dataout.setTenLichHoc((String) data.get("tenLichHoc"));
        dataout.setGiangvien((String) data.get("giangvien"));
        dataout.setLopHocPhan(toLopHocPhan((LinkedHashMap<String, Object>) data.get("lopHocPhan")));
        dataout.setPhongHoc(toPhongHoc((LinkedHashMap<String, Object>) data.get("phongHoc")));
        dataout.setTuanHoc(toTuanHoc((LinkedHashMap<String, String>) data.get("tuanHoc")));
        dataout.setNgayHoc(toNgayHoc((LinkedHashMap<String, String>) data.get("ngayHoc")));
        dataout.setKipHoc(toKipHoc((LinkedHashMap<String, String>) data.get("kipHoc")));
        return dataout;
    }

    public DangKyHocDTO toDangKyHocDTO(LinkedHashMap<String, Object> data){
        DangKyHocDTO dataout = new DangKyHocDTO();
        dataout.setMaDangKyHoc((String) data.get("maDangKyHoc"));
        dataout.setLopHocPhan(toLopHocPhan((LinkedHashMap<String, Object>) data.get("lopHocPhan")));
        dataout.setSinhVienKhoa(toSinhVienKhoa((LinkedHashMap<String, Object>) data.get("sinhVienKhoa")));
        return  dataout;
    }




//    public MonHoc toMonHoc(LinkedHashMap<String, Object> data){
//        MonHoc dataout = new MonHoc();
//        dataout.setMaMonHoc((String) data.get("maMonHoc"));
//        dataout.setTenMonHoc((String) data.get("tenMonHoc"));
//        dataout.setSoTc((Integer) data.get("soTc"));
//        dataout.setBoMon(toBoMon((LinkedHashMap<String, Object>) data.get("boMon")));
//        return dataout;
//    }


}

