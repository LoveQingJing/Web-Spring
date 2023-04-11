package com.example.demo.web;

import java.lang.Class;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link WebController}
 */
public class WebController__BeanDefinitions {
  /**
   * Get the bean definition for 'webController'
   */
  public static BeanDefinition getWebControllerBeanDefinition() {
    Class<?> beanType = WebController.class;
    RootBeanDefinition beanDefinition = new RootBeanDefinition(beanType);
    beanDefinition.setInstanceSupplier(WebController::new);
    return beanDefinition;
  }
}
