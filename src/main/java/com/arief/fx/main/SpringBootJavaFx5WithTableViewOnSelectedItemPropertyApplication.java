package com.arief.fx.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.arief.fx.configuration.AbstractFxController;
import com.arief.fx.controllers.MainController;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SpringBootApplication
@ComponentScan(basePackages= {"com.arief.fx.controllers"})
@EnableJpaRepositories("com.arief.fx.services")
@EntityScan("com.arief.fx.entity")
public class SpringBootJavaFx5WithTableViewOnSelectedItemPropertyApplication extends Application{


	private static String []a;
	
	private ApplicationContext context;
	

	
	public static void main(String[] args) {
		a= args;
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		AbstractFxController main =(AbstractFxController)context.getBean(MainController.class);
		Parent p = (Parent)main.initNodeForView("main.fxml");
		
		primaryStage.setResizable(false);
		primaryStage.setScene(new Scene(p));
		primaryStage.setTitle("Spring FX App");
		primaryStage.show();
		
	}

	@Override
	public void init() throws Exception {
		super.init();
		context = SpringApplication.run(SpringBootJavaFx5WithTableViewOnSelectedItemPropertyApplication.class, a);
	}

	@Override
	public void stop() throws Exception {
		super.stop();
	}
	
	
	
}
