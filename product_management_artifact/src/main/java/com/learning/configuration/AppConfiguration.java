package com.learning.configuration;

import com.learning.validator.BeforeSaveValidator;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

/**
 * Created by amits on 27/09/15.
 */
public class AppConfiguration extends RepositoryRestMvcConfiguration {
    @Override
    protected void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
        validatingListener.addValidator("beforeSave", new BeforeSaveValidator());
    }
}
