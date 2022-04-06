package com.example.siteESX.controller;

import com.example.siteESX.model.Rating;
import com.example.siteESX.model.Sala;
import com.example.siteESX.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    RatingService ratingService;

    @GetMapping()
    public Collection<Rating> getAll() {
        return ratingService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rating> getRatingId(@PathVariable String id) {
        Rating rating = ratingService.getRatingId(id);
        if (rating == null)
            return new ResponseEntity<Rating>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(ratingService.getRatingId(id));
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody Rating rating) {
        Rating rating1 = ratingService.getRatingId(rating.getId());

        if (rating1 == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        ratingService.update(rating);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity addRating(@RequestBody Rating rating) {
        rating.setId(UUID.randomUUID().toString());

        ratingService.addRating(rating);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRating(@PathVariable String id) {
        Rating rating = ratingService.getRatingId(id);
        if (rating == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        ratingService.deleteRating(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
