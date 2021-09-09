package org.mino.mreview.repository;

import org.junit.jupiter.api.Test;
import org.mino.mreview.entity.Member;
import org.mino.mreview.entity.Movie;
import org.mino.mreview.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {
    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMovieReviews() {
        //200개 리뷰 등록
        IntStream.rangeClosed(1,200).forEach(i->{
            //영화번호
            Long mno = (long)(Math.random()*100)+1;
            Movie movie = Movie.builder().mno(mno).build();

            //리뷰어 번호
            Long mid = ((long) (Math.random() * 100) + 1);
            Member member = Member.builder().mid(mid).build();

            Review movieReview = Review.builder()
                    .member(member)
                    .movie(movie)
                    .grade((int)(Math.random()*5)+1)
                    .text("이 영화에 대한 느낌...."+i)
                    .build();
            reviewRepository.save(movieReview);
        });
    }
    @Test
    public void testGetMovieReview() {
        Movie movie = Movie.builder().mno(92L).build();

        List<Review> result = reviewRepository.findByMovie(movie);

        result.forEach(movieReview ->{
            System.out.println("\t"+movieReview.getReviewnum());
            System.out.println("\t"+movieReview.getGrade());
            System.out.println("\t"+movieReview.getText());
            System.out.println("\t"+movieReview.getMember().getEmail());
            System.out.println("--------------------------------------");
        });
    }
}
