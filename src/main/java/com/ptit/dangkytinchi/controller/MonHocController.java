package com.ptit.dangkytinchi.controller;

import com.ptit.dangkytinchi.DTO.MonHocDTO;
import com.ptit.dangkytinchi.exception.ResponeAPI;
import com.ptit.dangkytinchi.model.MonHoc;
import com.ptit.dangkytinchi.repository.MonHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/monhoc", produces = "application/json")
@CrossOrigin(origins = "*")
public class MonHocController {
    @Autowired
    private MonHocRepository monHocRepository;

    public MonHocController(MonHocRepository monHocRepository) {
        this.monHocRepository = monHocRepository;
    }

    @GetMapping("/timkiem/{key}")
    public ResponeAPI getMonHocByTenMH(@PathVariable String key){
        ResponeAPI res = new ResponeAPI();
        ArrayList<MonHoc> dsMonHoc = (ArrayList<MonHoc>) monHocRepository.findMonHocByTenMonHocContains(key);

        ArrayList<MonHocDTO> dsMonHocDTO = new ArrayList<MonHocDTO>();
        dsMonHoc.forEach(monHoc->{
            MonHocDTO temp = new MonHocDTO(monHoc.getMaMonHoc(), monHoc.getTenMonHoc());
            dsMonHocDTO.add(temp);
        });

        res.setData(dsMonHocDTO);
        return res;
    }
}
