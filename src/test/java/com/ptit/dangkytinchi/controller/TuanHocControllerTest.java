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

public class TuanHocControllerTest extends AbstractTest{
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testLayTuanHocThanhCong() throws Exception{

        TuanHoc tuanHoc1 = new TuanHoc("TUANHOC26", "Tuần 26","07/02-13/02/2022" );
        TuanHoc tuanHoc2 = new TuanHoc("TUANHOC27", "Tuần 27","14/02-20/02/2022");
        TuanHoc tuanHoc3 = new TuanHoc("TUANHOC28", "Tuần 28","21/02-27/02/2022" );
        TuanHoc tuanHoc4 = new TuanHoc("TUANHOC29", "Tuần 29","28/02-06/03/2022" );
        TuanHoc tuanHoc5 = new TuanHoc("TUANHOC30", "Tuần 30","07/03-13/03/2022" );
        TuanHoc tuanHoc6 = new TuanHoc("TUANHOC31", "Tuần 31","14/03-20/03/2022");
        TuanHoc tuanHoc7 = new TuanHoc("TUANHOC32", "Tuần 32","21/03-27/03/2022");
        TuanHoc tuanHoc8 = new TuanHoc("TUANHOC33", "Tuần 33","28/03-03/04/2022" );
        TuanHoc tuanHoc9 = new TuanHoc("TUANHOC34", "Tuần 34","04/04-10/04/2022" );
        TuanHoc tuanHoc10 = new TuanHoc("TUANHOC35", "Tuần 35","11/04-17/04/2022" );
        TuanHoc tuanHoc11 = new TuanHoc("TUANHOC36", "Tuần 36","18/04-24/04/2022" );
        TuanHoc tuanHoc12 = new TuanHoc("TUANHOC37", "Tuần 37","25/04-01/05/2022" );
        TuanHoc tuanHoc13 = new TuanHoc("TUANHOC38", "Tuần 38","02/05-08/05/2022" );
        TuanHoc tuanHoc14 = new TuanHoc("TUANHOC39", "Tuần 39","09/05-15/05/2022" );
        TuanHoc tuanHoc15 = new TuanHoc("TUANHOC40", "Tuần 40","16/05-22/05/2022" );
        TuanHoc tuanHoc16 = new TuanHoc("TUANHOC41", "Tuần 41","23/05-29/05/2022" );
        TuanHoc tuanHoc17 = new TuanHoc("TUANHOC42", "Tuần 42","30/05-05/06/2022" );
        TuanHoc tuanHoc18 = new TuanHoc("TUANHOC43", "Tuần 43","06/06-12/06/2022" );
        TuanHoc tuanHoc19 = new TuanHoc("TUANHOC44", "Tuần 44","13/06-19/06/2022" );
        TuanHoc tuanHoc20 = new TuanHoc("TUANHOC45", "Tuần 45","20/06-26/06/2022" );
        TuanHoc tuanHoc21 = new TuanHoc("TUANHOC46", "Tuần 46","27/06-03/07/2022" );
        TuanHoc tuanHoc22 = new TuanHoc("TUANHOC47", "Tuần 47","04/07-10/07/2022" );
        ArrayList<TuanHoc> dsTuanHoc = new ArrayList<TuanHoc>();
        dsTuanHoc.add(tuanHoc1);dsTuanHoc.add(tuanHoc2);dsTuanHoc.add(tuanHoc3);dsTuanHoc.add(tuanHoc4);dsTuanHoc.add(tuanHoc5);
        dsTuanHoc.add(tuanHoc6);dsTuanHoc.add(tuanHoc7);dsTuanHoc.add(tuanHoc8);dsTuanHoc.add(tuanHoc9);dsTuanHoc.add(tuanHoc10);
        dsTuanHoc.add(tuanHoc11);dsTuanHoc.add(tuanHoc12);dsTuanHoc.add(tuanHoc13);dsTuanHoc.add(tuanHoc14);dsTuanHoc.add(tuanHoc15);
        dsTuanHoc.add(tuanHoc16);dsTuanHoc.add(tuanHoc17);dsTuanHoc.add(tuanHoc18);dsTuanHoc.add(tuanHoc19);dsTuanHoc.add(tuanHoc20);
        dsTuanHoc.add(tuanHoc21);dsTuanHoc.add(tuanHoc22);
        ResponeAPI outputExpect = new ResponeAPI();
        outputExpect.setData(dsTuanHoc);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/tuanhoc/dstuanhoc")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = result.getResponse().getStatus();
        Assertions.assertNotNull(result);
        String content =result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        ResponeAPI outputResult = super.mapFromJson(content, ResponeAPI.class);
        ArrayList<TuanHoc> dataResultObj = new ArrayList<TuanHoc>();
        List<LinkedHashMap<String, String>> dataResult =(List<LinkedHashMap<String, String>>) outputResult.getData();
        dataResult.forEach(stringObjectLinkedHashMap ->{
                    TuanHoc temp = toTuanHoc(stringObjectLinkedHashMap);
                    dataResultObj.add(temp);
                }
        );
        ArrayList<LichHoc> dataExpect = (ArrayList<LichHoc>) outputExpect.getData();
        Assertions.assertEquals(dataResultObj, outputExpect.getData());
    }
}
