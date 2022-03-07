package com.alexsnurnitsyn.springboot.flagsUploader.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CountryModel {

    private String name;
    private Map<String, String> flags;
}
