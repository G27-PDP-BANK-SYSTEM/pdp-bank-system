package com.example.card.render;

import com.example.card.render.entity.Render;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RenderRepository extends JpaRepository<Render, Long> {

    Page<Render> findAll(Specification<Render> specification, Pageable pageable);
}
