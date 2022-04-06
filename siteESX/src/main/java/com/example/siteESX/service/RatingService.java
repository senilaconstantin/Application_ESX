package com.example.siteESX.service;

import com.example.siteESX.model.Rating;
import com.example.siteESX.model.Sala;
import com.example.siteESX.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RatingService {
    @Autowired
    RatingRepository ratingRepository;
    public Collection<Rating> getAll() {
        return ratingRepository.getAllRatings();
    }

    public Rating getRatingId(String id) {
        return ratingRepository.getRatingId(id);
    }

    public void update(Rating rating) {
        ratingRepository.update(rating.getId(), rating.getNote(), rating.getComment());
    }

    public void addRating(Rating rating) {
        ratingRepository.addRating(rating.getId(), rating.getNote(), rating.getComment());
    }

    public void deleteRating(String id) {

        ratingRepository.deleteRating(id);
    }
}
