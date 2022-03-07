package com.alexsnurnitsyn.springboot.flagsUploader.service;

import com.alexsnurnitsyn.springboot.flagsUploader.enums.Format;
import com.alexsnurnitsyn.springboot.flagsUploader.error.FileDownloadException;

public interface CountryService {

    void uploadCountry(String codes, Format format, String path) throws FileDownloadException;

}
