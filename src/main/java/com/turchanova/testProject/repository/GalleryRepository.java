package com.turchanova.testProject.repository;

import com.turchanova.testProject.model.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
}
