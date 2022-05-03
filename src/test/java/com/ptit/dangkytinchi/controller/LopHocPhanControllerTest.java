package com.ptit.dangkytinchi.controller;

import com.ptit.dangkytinchi.DTO.LopHocPhanDTO;
import com.ptit.dangkytinchi.DTO.MonHocDTO;
import com.ptit.dangkytinchi.DTO.SinhVienDTO;
import com.ptit.dangkytinchi.response.ResponeAPI;
import com.ptit.dangkytinchi.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class LopHocPhanControllerTest extends AbstractTest{

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void LopHocPhanController_GetLopHocPhanCoKQ_D18_CNPM() throws Exception {
        String input="INT1340";


        //LopHocPhanDTO data1 = new LopHocPhanDTO("D18-008", "D18-008", 74,4,null,);
        ArrayList<LopHocPhanDTO> outputData = new ArrayList<LopHocPhanDTO>();
       // outputData.add(data1);




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

        LopHocPhanDTO data1 = new LopHocPhanDTO("D19-0001","D19-0001",1,0, null, monHocKiHoc, dsLichHoc);
        outputData.add(data1);


        SinhVienDTO inputBody = new SinhVienDTO();
        inputBody.setMaSinhVien("B19DCCN001");
        String inputJson = super.mapToJson(inputBody);

        ResponeAPI outputExpect = new ResponeAPI();
        outputExpect.setData(outputData);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/dangkytinchi/lophocphan/"+input)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson))
                .andReturn();
        int status = result.getResponse().getStatus();
        Assertions.assertNotNull(result);
        String content =result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        ResponeAPI outputResult = super.mapFromJson(content, ResponeAPI.class);
        ArrayList<LopHocPhanDTO> dataResultObj = new ArrayList<LopHocPhanDTO>();
        List<LinkedHashMap<String, Object>> dataResult =(List<LinkedHashMap<String, Object>>) outputResult.getData();
        dataResult.forEach(stringObjectLinkedHashMap ->{
                    LopHocPhanDTO temp = toLopHocPhanDTO(stringObjectLinkedHashMap);
                    dataResultObj.add(temp);
                }
        );
        ArrayList<MonHocDTO> dataExpect =( ArrayList<MonHocDTO>) outputExpect.getData();
        Assertions.assertEquals(dataResultObj, dataExpect);

    }
}
