package com.ptit.dangkytinchi.controller;

import com.ptit.dangkytinchi.response.ResponeAPI;
import com.ptit.dangkytinchi.model.TuanHoc;
import com.ptit.dangkytinchi.repository.TuanHocRepository;
import com.ptit.dangkytinchi.service.TuanHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/tuanhoc", produces = "application/json")
@CrossOrigin(origins = "*")
public class TuanHocController {

    @Autowired
    private TuanHocService tuanHocService;

    public TuanHocController(TuanHocService tuanHocService) {
        this.tuanHocService = tuanHocService;
    }

    //lay tat ca tuan hoc trong ky
    @GetMapping("/dstuanhoc")
    public ResponeAPI layDanhSachTuanHoc(){
        ResponeAPI res = new ResponeAPI();
        res.setData(tuanHocService.timKiemTatCaTuanHoc());
        return res;
    }
}
