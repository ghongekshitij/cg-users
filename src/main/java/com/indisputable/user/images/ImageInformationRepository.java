package com.indisputable.user.images;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageInformationRepository extends JpaRepository<ImageInformation, String> {
}
