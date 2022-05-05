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

public class DangKyHocControllerTest extends AbstractTest{
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
                "    {\"maLopHocPhan\" : \"D18-008\"} " +
                "]";
        String maSV = "B18DCCN026";

        ResponeAPI outputExpect = new ResponeAPI();
        outputExpect.setData("Đăng ký thành công!");

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/dangkytinchi/luudangky/" +maSV)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(input))
                .andReturn();
        int status = result.getResponse().getStatus();
        Assertions.assertNotNull(result);
        String content =result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        ResponeAPI outputResult = super.mapFromJson(content, ResponeAPI.class);

        String dataExpect =(String) outputExpect.getData();
        Assertions.assertEquals(outputResult.getData(), outputExpect.getData());
    }
    @Test
    public void testLuuDangKiKhongThanhCong_TrungLichHoc() throws Exception{
        String input="[\n" +
                "    {\"maLopHocPhan\" : \"D18-051\"},\n" +
                "    {\"maLopHocPhan\" : \"D18-038\"}\n" +
                "]";
        String maSV = "B18DCCN026";

        ResponeAPI outputExpect = new ResponeAPI();
        outputExpect.setData("Lỗi trùng lịch học");

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/dangkytinchi/luudangky/" +maSV)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(input))
                .andReturn();
        int status = result.getResponse().getStatus();
        Assertions.assertNotNull(result);
        String content =result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        ResponeAPI outputResult = super.mapFromJson(content, ResponeAPI.class);

        String dataExpect =(String) outputExpect.getData();
        Assertions.assertEquals(outputResult.getData(), outputExpect.getData());
    }

    @Test
    public void testLuuDangKiKhongThanhCong_HetSoLuong() throws Exception{
        String input="[\n" +
                "    {\"maLopHocPhan\": \"D19-0001\"}\n" +
                "]";
        String maSV = "B19DCCN001";

        ResponeAPI outputExpect = new ResponeAPI();
        outputExpect.setError("Môn học Nhập môn CNPM đã hết số lượng đăng ký!!!");

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/dangkytinchi/luudangky/" +maSV)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(input))
                .andReturn();
        int status = result.getResponse().getStatus();
        Assertions.assertNotNull(result);
        String content =result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        ResponeAPI outputResult = super.mapFromJson(content, ResponeAPI.class);

        String dataExpect =(String) outputExpect.getData();
        Assertions.assertEquals(outputResult.getError(), outputExpect.getError());
    }

    @Test
    public void testGetDangKiThanhCong() throws Exception{
        HocKi hocKi = new HocKi("HOCKY02", "Học kỳ 2", null);
        NamHoc namHoc  = new NamHoc("NAMHOC2021","NĂM HỌC 2021-2022", "năm học 2021-2022" );
        KiHoc kiHoc = new KiHoc("KYHOC08", true, true, namHoc, hocKi);
        Khoa khoa = new Khoa("CNTT01","Công nghệ thông tin 01" ,  "Khoa Công nghệ thông tin 01");
        BoMon boMon = new BoMon("BOMON10", "Công nghệ phần mềm", null, khoa);
        MonHoc monHoc = new MonHoc( "INT1408","Chuyên đề công nghệ phần mềm",1, boMon);
        MonHocKiHoc monHocKiHoc = new MonHocKiHoc("MHKH06", monHoc, kiHoc, null);
        LopHocPhan lopHocPhan = new LopHocPhan("D18-008","D18-008",74, null, monHocKiHoc);
        SinhVien sinhVien = new SinhVien("B18DCCN026","B18DCCN026","123456","Nguyễn Thế Anh","D18CNPM02");
        SinhVienKhoa sinhVienKhoa = new SinhVienKhoa("B18CN026","2018-2023",true, khoa, sinhVien);
        DangKyHocDTO dangKyHocDTO = new DangKyHocDTO("B18DCCN026D18-008",sinhVienKhoa,lopHocPhan );
        ArrayList<DangKyHocDTO> list = new ArrayList<>();
        list.add(dangKyHocDTO);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/dangkytinchi/luudangky/B18DCCN026")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = result.getResponse().getStatus();
        Assertions.assertNotNull(result);
        String content =result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        ResponeAPI outputResult = super.mapFromJson(content, ResponeAPI.class);

        ArrayList<DangKyHocDTO> dataResultObj = new ArrayList<>();
        List<LinkedHashMap<String, Object>> dataResult =(List<LinkedHashMap<String, Object>>) outputResult.getData();
        dataResult.forEach(stringObjectLinkedHashMap ->{
            DangKyHocDTO temp = toDangKyHocDTO(stringObjectLinkedHashMap);
                    dataResultObj.add(temp);
                }
        );
        Assertions.assertEquals(dataResultObj, list);
    }
    @Test
    @Transactional
    @Rollback
    public void testXoaDangKiThanhCong() throws Exception{
        this.testLuuDangKiThanhCong();
        String input="[\n" +
                "    {\"maLopHocPhan\" : \"D18-008\"} " +
                "]";
        String maSV = "B18DCCN026";
        ResponeAPI outputExpect = new ResponeAPI();
        outputExpect.setData("Xóa đăng ký thành công!");

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/dangkytinchi/xoadangky/" +maSV)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(input))
                .andReturn();
        int status = result.getResponse().getStatus();
        Assertions.assertNotNull(result);
        String content =result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        ResponeAPI outputResult = super.mapFromJson(content, ResponeAPI.class);
        String dataExpect =(String) outputExpect.getData();
        Assertions.assertEquals(outputResult.getData(), outputExpect.getData());
    }

    @Test
    public void testXemThoiKhoaBieuThanhCong() throws Exception{
        String input="{\n" +
                "    \"maTuanHoc\": \"TUANHOC27\"\n" +
                "}";
        String maSinhVien ="B19DCCN001";
        String inputJson = super.mapToJson(input);

        KipHoc kipHoc = new KipHoc("KIPHOC01", "KIP 01", "7h-9h",null);
        NgayHoc ngayHoc = new NgayHoc("NGAYHOC013", "2022-02-19", "Bảy",null);
        TuanHoc tuanHoc = new TuanHoc("TUANHOC27", "Tuần 27","14/02-20/02/2022", null );
        ToaNha toaNha = new ToaNha("TOANHA06", "Trans", "hoc online", null);
        PhongHoc phongHoc = new PhongHoc("70193", "70193", 90, toaNha, null);
        HocKi hocKi = new HocKi("HOCKY02", "Học kỳ 2", null, null);
        NamHoc namHoc = new NamHoc("NAMHOC2021","NĂM HỌC 2021-2022","năm học 2021-2022", null);
        KiHoc kiHoc = new KiHoc("KYHOC06", true, true, namHoc,hocKi, null);
        Khoa khoa = new Khoa("CNTT01", "Công nghệ thông tin 01","Khoa Công nghệ thông tin 01",null,null );
        BoMon boMon = new BoMon("BOMON10", "Công nghệ phần mềm", null,  khoa,null);
        MonHoc monHoc = new MonHoc("INT1340", "Nhập môn CNPM", 14,boMon,null);
        MonHocKiHoc monHocKiHoc = new MonHocKiHoc("MHKH16", monHoc,kiHoc,null);
        LopHocPhan lopHocPhan= new LopHocPhan("D19-0001","D19-0001",1,null,monHocKiHoc,null,null);
        LichHoc lichHoc = new LichHoc("LICHHOC0001", "Lịch học 734", "N.M.Hùng",lopHocPhan,phongHoc,tuanHoc,ngayHoc,kipHoc);
        ArrayList<LichHoc> dsLichHoc = new ArrayList<LichHoc>();
        dsLichHoc.add(lichHoc);
        ResponeAPI outputExpect = new ResponeAPI();
        outputExpect.setData(dsLichHoc);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/dangkytinchi/xemthoikhoabieu/" + maSinhVien)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(input))
                .andReturn();
        int status = result.getResponse().getStatus();
        Assertions.assertNotNull(result);
        String content =result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        ResponeAPI outputResult = super.mapFromJson(content, ResponeAPI.class);
        ArrayList<LichHoc> dataResultObj = new ArrayList<LichHoc>();
        List<LinkedHashMap<String, Object>> dataResult =(List<LinkedHashMap<String, Object>>) outputResult.getData();
        dataResult.forEach(stringObjectLinkedHashMap ->{
            LichHoc temp = toLichHoc(stringObjectLinkedHashMap);
                    dataResultObj.add(temp);
                }
        );
        ArrayList<LichHoc> dataExpect = (ArrayList<LichHoc>) outputExpect.getData();
        Assertions.assertEquals(dataResultObj, dsLichHoc);
    }
}
