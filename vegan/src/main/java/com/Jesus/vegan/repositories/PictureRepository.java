package com.Jesus.vegan.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Jesus.vegan.models.Picture;

@Repository
public interface PictureRepository extends CrudRepository<Picture, Long>{
	List<Picture> findAll();
}
