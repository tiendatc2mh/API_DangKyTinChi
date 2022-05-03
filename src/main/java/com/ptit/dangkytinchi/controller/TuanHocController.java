package com.ptit.dangkytinchi.controller;

import com.ptit.dangkytinchi.response.ResponeAPI;
import com.ptit.dangkytinchi.model.TuanHoc;
import com.ptit.dangkytinchi.repository.TuanHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/tuanhoc", produces = "application/json")
@CrossOrigin(origins = "*")
public class TuanHocController {

    @Autowired
    private TuanHocRepository tuanHocRepository;

    public TuanHocController(TuanHocRepository tuanHocRepository) {
        this.tuanHocRepository = tuanHocRepository;
    }

    //lay tat ca tuan hoc trong ky
    @GetMapping("/dstuanhoc")
    public ResponeAPI layDanhSachTuanHoc(){
        ResponeAPI res = new ResponeAPI();
        ArrayList<TuanHoc> dsTuanHoc = new ArrayList<TuanHoc>();
        dsTuanHoc  = (ArrayList<TuanHoc>) tuanHocRepository.findAll();
        res.setData(dsTuanHoc);
        return res;
    }
}
