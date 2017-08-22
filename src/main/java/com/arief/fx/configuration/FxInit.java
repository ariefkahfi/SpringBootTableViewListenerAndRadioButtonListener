package com.arief.fx.configuration;

import org.springframework.context.ApplicationContextAware;

import javafx.fxml.Initializable;
import javafx.scene.Node;



public interface FxInit extends ApplicationContextAware, Initializable {	
	public Node initNodeForView(String fxmlFileLocation);
}
