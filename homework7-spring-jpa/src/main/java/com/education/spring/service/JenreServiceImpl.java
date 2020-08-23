package com.education.spring.service;

import com.education.spring.repository.JenreRepository;
import com.education.spring.domain.Jenre;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JenreServiceImpl implements JenreService, AppService {

    private final JenreRepository jenreRepository;

    public JenreServiceImpl(JenreRepository jenreRepository) {
        this.jenreRepository = jenreRepository;
    }

    public Jenre findJenreByName(String jenre) {
        try {
            return jenreRepository.findByJenre(jenre);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public String insertJenreInfo(Map<String, String> jenreInfo) {
        Jenre jenre = new Jenre(0, jenreInfo.get("jenre"));
        if (checkDublicateJenre(jenre))
            return "Жанр уже есть в базе!";
        jenreRepository.save(jenre);
        return "Жанр успешно сохранен!";
    }

    public boolean checkDublicateJenre(Jenre jenre) {
        List<Jenre> jenres = jenreRepository.findAll();
        for (Jenre jenreExample: jenres) {
            if (jenre.equals(jenreExample)) {
                return true;
            }
        }
        return false;
    }

    public Jenre getJenreByName(String jenre) {
        try {
            return jenreRepository.findByJenre(jenre);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public String updateJenre(String name, String newJenre) {
        Jenre jenre = findJenreByName(name);
        if (jenre == null)
            return DONT_HAVE_JENRE;
        jenre.setJenre(newJenre);
        if(jenreRepository.update(jenre) != null)
            return String.format("Новый жанр книг: %s", jenre.getJenre());
        return "Жанр книг не изменен!";
    }

    public String deleteJenreByName(String name) {
        Jenre jenre = findJenreByName(name);
        if (jenre == null)
            return DONT_HAVE_JENRE;
        jenreRepository.deleteById(jenre.getId());
        if (findJenreByName(name) == null)
            return "Жанр успешно удалён!";
        return "Жанр не удалён!";
    }

    public List<Jenre> getAllJenres() {
        try {
            return jenreRepository.findAll();
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

}
