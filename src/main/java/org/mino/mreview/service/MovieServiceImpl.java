package org.mino.mreview.service;

import lombok.RequiredArgsConstructor;
import org.mino.mreview.dto.MovieDTO;
import org.mino.mreview.dto.PageRequestDTO;
import org.mino.mreview.dto.PageResultDTO;
import org.mino.mreview.entity.Movie;
import org.mino.mreview.entity.MovieImage;
import org.mino.mreview.repository.MovieImageRepository;
import org.mino.mreview.repository.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final MovieImageRepository movieImageRepository;

    @Transactional
    @Override
    public Long register(MovieDTO movieDTO) {
        Map<String, Object> entityMap = dtoToEntity(movieDTO);
        Movie movie = (Movie) entityMap.get("movie");
        List<MovieImage> movieImageList = (List<MovieImage>) entityMap.get("imgList");

        movieRepository.save(movie);

        movieImageList.forEach(movieImage -> {
            movieImageRepository.save(movieImage);
        });
        return movie.getMno();
    }

    @Override
    public PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("mno").descending());

        Page<Object[]> result = movieRepository.getListPage(pageable);

        Function<Object[],MovieDTO> fn = (arr -> entitiesToDTO(
                (Movie) arr[0],
                (List<MovieImage>)(Arrays.asList((MovieImage)arr[1])),
                (Double) arr[2],
                (Long) arr[3])
        ));
        return new PageResultDTO<>(result, fn);
    }
}
