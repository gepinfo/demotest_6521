package com.default_service.gcam.dao.imple;

import com.default_service.gcam.dao.ScreenDao;
import com.default_service.gcam.model.Screen;
import com.default_service.gcam.repositories.ScreenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ScreenDaoImple implements ScreenDao {

    @Autowired
    ScreenRepository screenRepository;

    @Override
    public void saveScreen(Screen screens) {
        log.info("Enter into saveAll:ScreenDaoImple");
        screenRepository.save(screens);
    }

    @Override
    public List<Screen> findAllScreens() {
        log.info("Enter into findAllScreens:ScreenDaoImple");
        return screenRepository.findAll();
    }

    @Override
    public void deleteScreen(Screen screen) {
        log.info("Enter into deleteScreen:ScreenDaoImple");
        screenRepository.delete(screen);
    }

}

