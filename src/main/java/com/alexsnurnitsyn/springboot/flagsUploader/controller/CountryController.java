package com.alexsnurnitsyn.springboot.flagsUploader.controller;

import com.alexsnurnitsyn.springboot.flagsUploader.enums.Format;
import com.alexsnurnitsyn.springboot.flagsUploader.service.CountryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryServiceImpl service;


    @GetMapping
    public ResponseEntity getFlags(@RequestParam("countryCodes") String countryCodes, @RequestParam("format") Format format,
                                   @RequestParam("path") String path) {
        service.uploadCountry(countryCodes, format, path);

        return ResponseEntity.ok().build();
    }
}
