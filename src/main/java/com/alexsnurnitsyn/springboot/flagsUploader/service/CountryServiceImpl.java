package com.alexsnurnitsyn.springboot.flagsUploader.service;

import com.alexsnurnitsyn.springboot.flagsUploader.enums.Format;
import com.alexsnurnitsyn.springboot.flagsUploader.error.FileDownloadException;
import com.alexsnurnitsyn.springboot.flagsUploader.helpers.Client;
import com.alexsnurnitsyn.springboot.flagsUploader.model.CountryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final Client client;

    @Autowired
    public CountryServiceImpl(Client client) {
        this.client = client;
    }

    @Override
    public void uploadCountry(String codes, Format format, String path) {

        try {
            List<CountryModel> countriesInfo = client.getCountryInfo(codes);

            for (CountryModel countryInfo: countriesInfo) {
                String flagUrl = countryInfo.getFlags().get(format.toString().toLowerCase());
                byte[] flagData = client.downloadFile(flagUrl);
                Path finalPath = Paths.get(path + countryInfo.getName() + "." + format.toString().toLowerCase());
                Files.write(finalPath, flagData);
            }
        } catch (Exception e) {
            throw new FileDownloadException(e);
        }
    }
}
