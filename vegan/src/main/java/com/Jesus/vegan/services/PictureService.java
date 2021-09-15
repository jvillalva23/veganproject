package com.Jesus.vegan.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Jesus.vegan.models.Picture;
import com.Jesus.vegan.repositories.PictureRepository;

@Service
public class PictureService {
	@Autowired
	private PictureRepository pRepo;
	
	public void uploadPic(String url) {
		Picture newPic = new Picture(url);
		this.pRepo.save(newPic);
	}
	
	public List<Picture> findAllPictures(){
		return this.pRepo.findAll();
	}

}
