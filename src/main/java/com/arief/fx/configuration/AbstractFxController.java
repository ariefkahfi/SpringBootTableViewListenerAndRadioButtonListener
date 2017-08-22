package com.arief.fx.configuration;

import java.io.IOException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public abstract class AbstractFxController implements FxInit {

	protected ApplicationContext context;
	
	
	
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		context = arg0;
	}

	@Override
	public Node initNodeForView(String fxmlFileLocation) {
		FXMLLoader loader = new FXMLLoader();
	
		loader.setLocation(getClass().getResource("/scene/"+fxmlFileLocation));
		loader.setController(context.getBean(getClass()));
		
		try {
			return loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
}
