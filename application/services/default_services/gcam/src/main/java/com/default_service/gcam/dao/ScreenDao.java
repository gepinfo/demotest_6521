package com.default_service.gcam.dao;

import com.default_service.gcam.model.Screen;

import java.util.List;

public interface ScreenDao {
    void saveScreen(Screen screens);

    List<Screen> findAllScreens();

    void deleteScreen(Screen screen);
}
