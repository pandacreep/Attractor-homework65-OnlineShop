package com.pandacreep.onlineshop.frontend;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PropertiesService {
    private final SpringDataWebProperties springDataWebProperties;

    public int getDefaultPageSize() {
        return springDataWebProperties.getPageable().getDefaultPageSize();
    }
}
