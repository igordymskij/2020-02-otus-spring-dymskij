package com.education.spring.dao;

import com.education.spring.domain.Jenre;

import java.util.List;

public interface JenresDao {

    Jenre getById(int jenreId);
    Jenre getByName(String jenre);
    List<Jenre> getAll();
    int insert(Jenre jenre);
    int deleteById(int jenreId);
    int deleteByName(String jenre);
}
