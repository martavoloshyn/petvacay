package com.petvacay.controllers;

import com.petvacay.services.implementation.MediaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("petvacay/api/v1")
public class GalleryController {

    private MediaServiceImpl mediaService;

    @Autowired
    public GalleryController(MediaServiceImpl mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping("/user/{id}/gallery")
    public long getUserGalleryId(@PathVariable long id) {
        return mediaService.getUserGalleryId(id);
    }

    @GetMapping("/petCheck/{id}/gallery")
    public long getPetCheckGalleryId(@PathVariable long id) {
        return mediaService.getPetCheckGalleryId(id);
    }

    @DeleteMapping("/gallery/{id}")
    public boolean deleteGallery(@PathVariable long id) {
        return mediaService.deleteGallery(id);
    }
}
