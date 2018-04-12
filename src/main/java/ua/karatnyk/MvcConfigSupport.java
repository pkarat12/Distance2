package ua.karatnyk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
public class MvcConfigSupport extends WebMvcConfigurationSupport{

	@Override
	@Bean
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		RequestMappingHandlerAdapter adapter = super.requestMappingHandlerAdapter();
		adapter.setIgnoreDefaultModelOnRedirect(true);
		return adapter;
	}
	
	

}
