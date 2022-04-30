package com.ptit.dangkytinchi.controller;

import com.ptit.dangkytinchi.DTO.*;
import com.ptit.dangkytinchi.DangKyTinChiApplication;
import com.ptit.dangkytinchi.exception.ResponeAPI;
import com.ptit.dangkytinchi.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DangKyTinChiApplication.class)
@WebAppConfiguration
public abstract class DangKyHocControllerTest extends AbstractTest{
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }


    @Test
    @Transactional
    @Rollback
    public void testLuuDangKiThanhCong() throws Exception{
        String input="[\n" +
                "    {\"maLopHocPhan\" : \"D18-023\"} " +
                "]";
        String maSV = "B18DCCN026";
        String inputJson = super.mapToJson(input);

        ResponeAPI outputExpect = new ResponeAPI();
        outputExpect.setData("Đăng ký thành công!");

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("dangkytinchi/luudangky" +maSV)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson))
                .andReturn();
        int status = result.getResponse().getStatus();
        Assertions.assertNotNull(result);
        String content =result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        ResponeAPI outputResult = super.mapFromJson(content, ResponeAPI.class);

        ArrayList<MonHocDTO> dataExpect =( ArrayList<MonHocDTO>) outputExpect.getData();
        Assertions.assertEquals(outputResult.getData(), outputExpect.getData());
    }
    @Test
    public void testLuuDangKiKhongThanhCong() throws Exception{
        String input="[\n" +
                "    {\"maLopHocPhan\" : \"D18-051\"},\n" +
                "    {\"maLopHocPhan\" : \"D18-038\"}\n" +
                "]";
        String maSV = "B18DCCN026";
        String inputJson = super.mapToJson(input);

        ResponeAPI outputExpect = new ResponeAPI();
        outputExpect.setData("Lỗi trùng lịch học");

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("dangkytinchi/luudangky" +maSV)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson))
                .andReturn();
        int status = result.getResponse().getStatus();
        Assertions.assertNotNull(result);
        String content =result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        ResponeAPI outputResult = super.mapFromJson(content, ResponeAPI.class);

        ArrayList<MonHocDTO> dataExpect =( ArrayList<MonHocDTO>) outputExpect.getData();
        Assertions.assertEquals(outputResult.getData(), outputExpect.getData());
    }
    @Test
    public void testGetDangKiThanhCong() throws Exception{
        HocKi hocKi = new HocKi("HOCKY02", "Học kỳ 2", null);
        NamHoc namHoc  = new NamHoc("NAMHOC2021","NĂM HỌC 2021-2022", "năm học 2021-2022" );
        KiHoc kiHoc = new KiHoc("KYHOC08", true, true, namHoc, hocKi);
        Khoa khoa = new Khoa("CNTT01","Công nghệ thông tin 01" ,  "Khoa Công nghệ thông tin 01");
        BoMon boMon = new BoMon("BOMON10", "Công nghệ phần mềm", null, khoa);
        MonHoc monHoc = new MonHoc( "INT1408","Chuyên đề công nghệ phần mềm",1, boMon);
        MonHocKiHoc monHocKiHoc = new MonHocKiHoc("INT1408", monHoc, kiHoc);
        LopHocPhan lopHocPhan = new LopHocPhan("D18-008","D18-008",74, null, monHocKiHoc);
        SinhVien sinhVien = new SinhVien("B18DCCN026","B18DCCN026","123456","123456","D18CNPM02");
        SinhVienKhoa sinhVienKhoa = new SinhVienKhoa("B18CN026","2018-2023",true, khoa, sinhVien);
        DangKyHoc dangKyHoc = new DangKyHoc("B18DCCN026D18-008",sinhVienKhoa,lopHocPhan );
        ArrayList<DangKyHoc> list = new ArrayList<>();
        list.add(dangKyHoc);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/dangkytinchi/lophocphan/B18DCCN026")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(""))
                .andReturn();
        int status = result.getResponse().getStatus();
        Assertions.assertNotNull(result);
        String content =result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        ResponeAPI outputResult = super.mapFromJson(content, ResponeAPI.class);

        ArrayList<LopHocPhanDTO> dataResultObj = new ArrayList<>();
        List<LinkedHashMap<String, Object>> dataResult =(List<LinkedHashMap<String, Object>>) outputResult.getData();
        dataResult.forEach(stringObjectLinkedHashMap ->{
                    LopHocPhanDTO temp = toLopHocPhanDTO(stringObjectLinkedHashMap);
                    dataResultObj.add(temp);
                }
        );
        ArrayList<DangKyHoc> dataExpect =( ArrayList<DangKyHoc>) list;
        Assertions.assertEquals(dataResultObj, dataExpect);
    }
    @Test
    @Transactional
    @Rollback
    public void testXoaDangKiThanhCong() throws Exception{
        String input="[\n" +
                "    {\"maLopHocPhan\" : \"D18-008\"},\n" +
                "]";
        String maSV = "B18DCCN026";
        String inputJson = super.mapToJson(input);

        ResponeAPI outputExpect = new ResponeAPI();
        outputExpect.setData("Xóa đăng ký thành công!");

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("dangkytinchi/xoadangky" +maSV)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson))
                .andReturn();
        int status = result.getResponse().getStatus();
        Assertions.assertNotNull(result);
        String content =result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        ResponeAPI outputResult = super.mapFromJson(content, ResponeAPI.class);

        ArrayList<MonHocDTO> dataExpect =( ArrayList<MonHocDTO>) outputExpect.getData();
        Assertions.assertEquals(outputResult.getData(), outputExpect.getData());
    }
}
