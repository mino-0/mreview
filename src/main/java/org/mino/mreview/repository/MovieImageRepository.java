package org.mino.mreview.repository;

import org.mino.mreview.entity.MovieImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieImageRepository extends JpaRepository<MovieImage,Long> {
}
