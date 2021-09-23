package com.turchanova.testProject.repository;

import com.turchanova.testProject.model.Painting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaintingRepository extends JpaRepository<Painting, Long> {
}
