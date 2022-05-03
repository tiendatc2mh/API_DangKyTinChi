package com.ptit.dangkytinchi.controller;

import com.ptit.dangkytinchi.repository.SinhVienRepository;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class SinhVienKhoaControllerTest extends AbstractTest{
    @Autowired
    private SinhVienRepository sinhVienRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

}
