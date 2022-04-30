package com.ptit.dangkytinchi.controller;

import com.ptit.dangkytinchi.DTO.MonHocDTO;
import com.ptit.dangkytinchi.DTO.SinhVienDTO;
import com.ptit.dangkytinchi.DangKyTinChiApplication;
import com.ptit.dangkytinchi.exception.ResponeAPI;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
    public void testLuuDangKiThanhCong() throws Exception{
        String input="[\n" +
                "    {\"maLopHocPhan\" : \"D18-051\"},\n" +
                "    {\"maLopHocPhan\" : \"D18-038\"}\n" +
                "]";
        MonHocDTO data1 = new MonHocDTO("INT1408", "Chuyên đề công nghệ phần mềm", 1,
                null,null,null);
        ArrayList<MonHocDTO> outputData = new ArrayList<MonHocDTO>();
        outputData.add(data1);

        SinhVienDTO inputBody = new SinhVienDTO();
        inputBody.setMaSinhVien("B18DCCN147");
        String inputJson = super.mapToJson(inputBody);

        ResponeAPI outputExpect = new ResponeAPI();
        outputExpect.setData(outputData);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/monhoc/timkiem/"+input)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson))
                .andReturn();
        int status = result.getResponse().getStatus();
        Assertions.assertNotNull(result);
        String content =result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        ResponeAPI outputResult = super.mapFromJson(content, ResponeAPI.class);
        ArrayList<MonHocDTO> dataResultObj = new ArrayList<MonHocDTO>();
        List<LinkedHashMap<String, Object>> dataResult =(List<LinkedHashMap<String, Object>>) outputResult.getData();
        dataResult.forEach(stringObjectLinkedHashMap ->{
                    MonHocDTO temp = toMonHocDTO(stringObjectLinkedHashMap);
                    dataResultObj.add(temp);
                }
        );
        ArrayList<MonHocDTO> dataExpect =( ArrayList<MonHocDTO>) outputExpect.getData();
        Assertions.assertEquals(dataResultObj, dataExpect);
    }
    @Test
    public void testLuuDangKiKhongThanhCong() throws Exception{

    }
    @Test
    public void testGetDangKiThanhCong() throws Exception{

    }
    @Test
    public void testXoaDangKiThanhCong() throws Exception{

    }
    @Test
    public void testXoaDangKiKhongThanhCong() throws Exception{

    }
}
