package com.test.service.Impl;

import com.test.model.api.Vk;
import com.test.service.VkService;
import org.springframework.stereotype.Component;

/**
 * Created by Pavel on 02.12.2016.
 */
@Component
public class VkServiceImpl implements VkService {

    @Override
    public void openAuthorizationDialog() throws Exception {
        Vk.auth();
    }
}
