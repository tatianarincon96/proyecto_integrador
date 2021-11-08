package com.proyecto.integrador.controller;

import com.proyecto.integrador.DTO.ImageDTO;
import com.proyecto.integrador.exceptions.FindByIdException;
import com.proyecto.integrador.service.IImageService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/images")
public class ImageController implements CRUDController<ImageDTO> {

    @Autowired
    IImageService imageService;

    @Operation(summary = "Find All Images ", description = "Returns complete list of images")
    @GetMapping("/all")
    public ResponseEntity<List<ImageDTO>> getAll(){
        return ResponseEntity.ok(imageService.findAll());
    }

    @Operation(summary = "Find image by ID", description = "Returns a single image")
    @GetMapping("/get/{id}")
    public ResponseEntity<ImageDTO> getById(@PathVariable Integer id) throws FindByIdException {
        return ResponseEntity.ok(imageService.findById(id));
    }

    @Operation(summary = "Add a new image", description = "Creates a new image")
    @PostMapping("/create")
    public ResponseEntity<ImageDTO> create(@RequestBody ImageDTO image) throws FindByIdException {
        return ResponseEntity.ok(imageService.save(image));
    }

    @Operation(summary = "Update an existing image", description = "Updates some information of an exiting image")
    @PutMapping("/update")
    public ResponseEntity<ImageDTO> updateById(@RequestBody ImageDTO image) throws FindByIdException {
        return ResponseEntity.ok(imageService.update(image));
    }

    @Operation(summary = "Delete a image by ID", description = "Delete a image by id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) throws FindByIdException {
        imageService.deleteById(id);
        return ResponseEntity.ok("Se eliminó la imagen con id: "+id);
    }
}