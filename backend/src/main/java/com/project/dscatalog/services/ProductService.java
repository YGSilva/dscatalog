package com.project.dscatalog.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dscatalog.dto.ProductDTO;
import com.project.dscatalog.entities.Product;
import com.project.dscatalog.repositories.ProductRepository;
import com.project.dscatalog.services.exceptions.DatabaseException;
import com.project.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPageble(PageRequest pageRequest) {
		
		Page<Product> list = repository.findAll(pageRequest);

		return list.map(x -> new ProductDTO(x));

		/*
		 * List<ProductDTO> listDto = new ArrayList<>();
		 * for (Product cat : list) { listDto.add(new ProductDTO(cat)); }
		 * return listDto;
		 */
	}
	
	@Transactional(readOnly=true)
	public ProductDTO findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not founds"));
		return new ProductDTO(entity, entity.getCategories()); 
	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new ProductDTO(entity);
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product entity = repository.getOne(id);
			entity.setName(dto.getName());
			entity = repository.save(entity);
			return new ProductDTO(entity);
		} catch(ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Id not found: " + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found: " + id);
		} catch(DatabaseException e) {
			throw new DatabaseException("Integrity violation");
		}
		
	}

	 
}