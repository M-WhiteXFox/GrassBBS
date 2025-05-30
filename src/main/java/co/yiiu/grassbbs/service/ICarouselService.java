package co.yiiu.grassbbs.service;

import co.yiiu.grassbbs.model.Carousel;
import java.util.List;

public interface ICarouselService {
    List<Carousel> selectAll();
    List<Carousel> selectActive();
    void update(Carousel carousel);
}