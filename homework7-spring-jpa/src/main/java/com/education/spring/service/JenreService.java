package com.education.spring.service;

import com.education.spring.domain.Jenre;

import java.util.List;
import java.util.Map;

public interface JenreService {

    String insertJenreInfo(Jenre jenre);
    boolean checkDublicateJenre(Jenre jenre);
    Jenre getJenreByName(String jenre);
    String updateJenre(String name, String newJenre);
    String deleteJenreByName(String name);
    List<Jenre> getAllJenres();
}
