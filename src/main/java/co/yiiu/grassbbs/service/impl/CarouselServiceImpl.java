package co.yiiu.grassbbs.service.impl;

import co.yiiu.grassbbs.mapper.CarouselMapper;
import co.yiiu.grassbbs.model.Carousel;
import co.yiiu.grassbbs.service.ICarouselService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CarouselServiceImpl implements ICarouselService {

    @Resource
    private CarouselMapper carouselMapper;

    @Override
    public List<Carousel> selectAll() {
        return carouselMapper.selectAll();
    }

    @Override
    public void update(Carousel carousel) {
        carouselMapper.update(carousel);
    }

    @Override
    public List<Carousel> selectActive() {
        return carouselMapper.selectActive();
    }
}