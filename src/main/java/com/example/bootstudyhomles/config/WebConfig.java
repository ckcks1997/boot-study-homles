package com.example.bootstudyhomles.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/img/**")
			.addResourceLocations("/resources/img/");
			
			registry.addResourceHandler("/css/**")
			.addResourceLocations("/resources/css/") ;
			
			registry.addResourceHandler("/fonts/**")
			.addResourceLocations("/resources/fonts/") ;

			registry.addResourceHandler("/common/**")
			.addResourceLocations("/WEB-INF/common/") ;

			registry.addResourceHandler("/studyupload/**")
					.addResourceLocations("/studyupload/") ;
			registry.addResourceHandler("/imgupload/**")
					.addResourceLocations("/imgupload/") ;

			registry.addResourceHandler("/comboardupload/**")
					.addResourceLocations("/comboardupload/") ;

		}

}
 