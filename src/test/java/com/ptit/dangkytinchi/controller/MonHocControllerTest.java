package com.ptit.dangkytinchi.controller;

import com.ptit.dangkytinchi.DTO.BoMonDTO;
import com.ptit.dangkytinchi.DTO.MonHocDTO;
import com.ptit.dangkytinchi.DTO.SinhVienDTO;
import com.ptit.dangkytinchi.exception.ResponeAPI;
import com.ptit.dangkytinchi.repository.SinhVienRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MonHocControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void MonHocControllerTimKiemButtonCoKQ() throws Exception {
        String input="Toán";
        MonHocDTO data1 = new MonHocDTO("INT1358", "Toán rời rạc 1", 3,
                null,null,null);
        MonHocDTO data2 = new MonHocDTO("INT1359", "Toán rời rạc 2", 3,
                null,null,null);
        ArrayList<MonHocDTO> outputData = new ArrayList<MonHocDTO>();
        outputData.add(data1);
        outputData.add(data2);

        ResponeAPI outputExpect = new ResponeAPI();
        outputExpect.setData(outputData);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/monhoc/timkiembutton/"+input)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
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
    public void MonHocControllerTimKiemButtonKhongKQ() throws Exception {
        String input="bnn";
        ArrayList<MonHocDTO> outputData = new ArrayList<MonHocDTO>();

        ResponeAPI outputExpect = new ResponeAPI();
        outputExpect.setData(outputData);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/monhoc/timkiembutton/"+input)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
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
    public void MonHocControllerTimKiemKhongKQ() throws Exception {
        String input="bnn";
        SinhVienDTO inputBody = new SinhVienDTO();
        inputBody.setMaSinhVien("B18DCCN147");
        String inputJson = super.mapToJson(inputBody);

        ArrayList<MonHocDTO> outputData = new ArrayList<MonHocDTO>();

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
        Assertions.assertEquals(outputExpect.getError(), outputResult.getError());

    }

    @Test
    public void MonHocControllerTimKiemCoKQ_D18_CNPM() throws Exception {
        String input="chuyên đề";
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
    public void MonHocControllerTimKiemCoKQ_D18_HTTT() throws Exception {
        String input="chuyên đề";
        MonHocDTO data1 = new MonHocDTO("INT1409", "Chuyên đề hệ thống thông tin", 1,
                null,null,null);
        ArrayList<MonHocDTO> outputData = new ArrayList<MonHocDTO>();
        outputData.add(data1);

        SinhVienDTO inputBody = new SinhVienDTO();
        inputBody.setMaSinhVien("B18DCCN001");
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
    public void MonHocControllerTimKiemCoKQ_D19() throws Exception {
        String input=" ";
        MonHocDTO data1 = new MonHocDTO("INT1340", "Nhập môn CNPM", 3,
                null,null,null);
        ArrayList<MonHocDTO> outputData = new ArrayList<MonHocDTO>();
        outputData.add(data1);

        SinhVienDTO inputBody = new SinhVienDTO();
        inputBody.setMaSinhVien("B19DCCN001");
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
    public void MonHocControllerTimKiemCoKQ_D20() throws Exception {
        String input=" ";
        MonHocDTO data1 = new MonHocDTO("INT1359", "Toán rời rạc 2", 3,
                null,null,null);
        ArrayList<MonHocDTO> outputData = new ArrayList<MonHocDTO>();
        outputData.add(data1);

        SinhVienDTO inputBody = new SinhVienDTO();
        inputBody.setMaSinhVien("B20DCCN001");
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
    public void MonHocControllerTimKiemCoKQ_D21() throws Exception {
        String input=" ";
        MonHocDTO data1 = new MonHocDTO("BAS1224", "Vật lý 1 và thí nghiệm", 4,
                null,null,null);
        ArrayList<MonHocDTO> outputData = new ArrayList<MonHocDTO>();
        outputData.add(data1);

        SinhVienDTO inputBody = new SinhVienDTO();
        inputBody.setMaSinhVien("B21DCCN001");
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

}
