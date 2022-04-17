package com.ptit.dangkytinchi.controller;

import com.ptit.dangkytinchi.DTO.SinhVienDTO;
import com.ptit.dangkytinchi.exception.ResponeAPI;
import com.ptit.dangkytinchi.repository.SinhVienRepository;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

public class SinhVienControllerTest extends AbstractTest {

    @Autowired
    private SinhVienRepository sinhVienRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }



    @Test
    public void sinhVienControllerDangNhapDungTest() throws Exception {
        String inputTaiKhoan = "B18DCCN147";
        String inputMatKhau = "123456";
        SinhVienDTO outputData = new SinhVienDTO("B18DCCN147", "Trịnh Tiến Đạt", "B18DCCN147", "123456", null, "D18CNPM02");

        ResponeAPI outputExpext = new ResponeAPI();
        outputExpext.setData(outputData);
        SinhVienDTO svDTO = new SinhVienDTO(inputTaiKhoan, inputMatKhau);
        String inputJson = super.mapToJson(svDTO);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/sinhvien/dangnhap")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        int status = result.getResponse().getStatus();
        Assertions.assertNotNull(result);
        String content =result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        ResponeAPI outputResult = super.mapFromJson(content, ResponeAPI.class);
        LinkedHashMap<String, String> dataResult =(LinkedHashMap<String, String>) outputResult.getData();
        SinhVienDTO dataResultObj = super.toSinhVienDTO(dataResult);
        SinhVienDTO dataExpect =(SinhVienDTO) outputExpext.getData();
        Assertions.assertEquals(dataResultObj, dataExpect);

    }
    @Test
    public void sinhVienControllerDangNhapSaiTest() throws Exception {
        String inputTaiKhoan = "B18DCCN147";
        String inputMatKhau = "1234566";

        ResponeAPI outputExpext = new ResponeAPI();
        outputExpext.setError("Sai tài khoản, mật khẩu.Vui lòng nhập lại!");
        SinhVienDTO svDTO = new SinhVienDTO(inputTaiKhoan, inputMatKhau);
        String inputJson = super.mapToJson(svDTO);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/sinhvien/dangnhap")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        int status = result.getResponse().getStatus();
        Assertions.assertNotNull(result);
        String content =result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        ResponeAPI outputResult = super.mapFromJson(content, ResponeAPI.class);
        String dataResult =(String) outputResult.getError();
        String dataExpect =(String) outputExpext.getError();
        Assertions.assertEquals(dataResult, dataExpect);

    }

    @Test
    @Transactional
    @Rollback
    public void sinhVienControllerDoiMatKhauDungTest() throws Exception {
        String inputTaiKhoan = "B18DCCN147";
        String inputMatKhau = "123456";
        String inputMatKhauMoi = "1234566";
        SinhVienDTO outputData = new SinhVienDTO("B18DCCN147", "Trịnh Tiến Đạt", "B18DCCN147", "1234566", null, "D18CNPM02");

        ResponeAPI outputExpext = new ResponeAPI();
        outputExpext.setData("ok");
        SinhVienDTO svDTO = new SinhVienDTO(inputTaiKhoan,inputMatKhau, inputMatKhauMoi);
        String inputJson = super.mapToJson(svDTO);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/sinhvien/doimatkhau")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        int status = result.getResponse().getStatus();
        Assertions.assertNotNull(result);
        String content =result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        ResponeAPI outputResult = super.mapFromJson(content, ResponeAPI.class);

        String dataResult =(String) outputResult.getData();
        String dataExpect =(String) outputExpext.getData();
        Assertions.assertEquals(dataResult, dataExpect);
        Assertions.assertNotNull(sinhVienRepository.findOneByTaiKhoanAndMatKhau(inputTaiKhoan, inputMatKhauMoi));

    }

    @Test
    public void sinhVienControllerDoiMatKhauSaiTest() throws Exception {
        String inputTaiKhoan = "B18DCCN147";
        String inputMatKhau = "1234566";
        String inputMatKhauMoi = "12345666";

        ResponeAPI outputExpext = new ResponeAPI();
        outputExpext.setError("Sai mật khẩu cũ.Vui lòng nhập lại!");
        SinhVienDTO svDTO = new SinhVienDTO(inputTaiKhoan, inputMatKhau, inputMatKhauMoi);
        String inputJson = super.mapToJson(svDTO);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/sinhvien/doimatkhau")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        int status = result.getResponse().getStatus();
        Assertions.assertNotNull(result);
        String content =result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        ResponeAPI outputResult = super.mapFromJson(content, ResponeAPI.class);
        String dataResult =(String) outputResult.getError();
        String dataExpect =(String) outputExpext.getError();
        Assertions.assertEquals(dataResult, dataExpect);

    }



}
