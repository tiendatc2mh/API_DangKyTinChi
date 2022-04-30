package com.ptit.dangkytinchi.controller;

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

public abstract class SinhVienKhoaControllerTest extends AbstractTest{
    @Autowired
    private SinhVienRepository sinhVienRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

}
