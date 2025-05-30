package co.yiiu.grassbbs.mapper;

import co.yiiu.grassbbs.model.Carousel;
import java.util.List;

public interface CarouselMapper {

    List<Carousel> selectActive();

    void update(Carousel carousel);

    List<Carousel> selectAll();
}