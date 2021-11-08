package com.proyecto.integrador.service;

import com.proyecto.integrador.DTO.ProductDTO;
import com.proyecto.integrador.exceptions.BadRequestException;
import com.proyecto.integrador.exceptions.FindByIdException;

import java.util.List;

public interface IProductService extends IBookingService<ProductDTO> {
    List<ProductDTO> findAll() throws FindByIdException;
    ProductDTO save(ProductDTO product) throws FindByIdException;
    ProductDTO findById(Integer productId) throws FindByIdException;
    void deleteById(Integer productId) throws FindByIdException;
    ProductDTO update(ProductDTO product) throws FindByIdException;
    List<ProductDTO> findAllByCategory(String categoryName) throws FindByIdException, BadRequestException;
    List<ProductDTO> findAllByCity(Integer cityId) throws FindByIdException, BadRequestException;
}
