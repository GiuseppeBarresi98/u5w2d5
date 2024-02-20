package com.barresi.u5w2d5.services;


import com.barresi.u5w2d5.entities.Dipendente;
import com.barresi.u5w2d5.exceptions.NotFoundException;
import com.barresi.u5w2d5.payloads.NewDipendenteDTO;
import com.barresi.u5w2d5.repositories.DipendenteDAO;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

import static org.hibernate.sql.ast.SqlTreeCreationLogger.LOGGER;

@Service
public class DipendenteService {
    @Autowired
    DipendenteDAO dipendenteDAO;

    @Autowired
    Cloudinary cloudinaryUploader;

    public Page<Dipendente> getListaDipendenti(int pageNumber,int size,String orderBy) {
        if(size < 100 ) size = 100;
        Pageable pageable = PageRequest.of(pageNumber,size, Sort.by(orderBy));
        return dipendenteDAO.findAll(pageable);
    }

    public Dipendente findDipendenteById(UUID id){
        LOGGER.info(id);
        return dipendenteDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public Dipendente saveDipendente(NewDipendenteDTO newDipendente){
        Dipendente dipendente = new Dipendente();
        dipendente.setNome(newDipendente.getNome());
        dipendente.setCognome(newDipendente.getCognome());
        dipendente.setUsername(newDipendente.getUsername());
        dipendente.setEmail(newDipendente.getEmail());
        dipendente.setPassword(newDipendente.getPassword());
        return dipendenteDAO.save(dipendente);
    }

    public Dipendente updateDipendente(UUID dipendenteID,NewDipendenteDTO dipendeteDTO){
        Dipendente found = this.findDipendenteById(dipendenteID);
        found.setUsername(dipendeteDTO.getUsername());
        found.setNome(dipendeteDTO.getNome());
        found.setCognome(dipendeteDTO.getCognome());
        found.setEmail(dipendeteDTO.getEmail());
        return dipendenteDAO.save(found);
    }

    public void deleteDipendente(UUID dipendenteID){
        Dipendente found = this.findDipendenteById(dipendenteID);
        dipendenteDAO.delete(found);
    }

    public String updateImg(UUID id, MultipartFile image) throws IOException {
        Dipendente found = this.findDipendenteById(id);
        String url = (String) cloudinaryUploader.uploader().upload(image.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setAvatar(url);
        dipendenteDAO.save(found);
        return url;
    }

    public Dipendente findByemail(String email){
        return dipendenteDAO.findByEmail(email).orElseThrow(()-> new NotFoundException(email));
    }


}
