package com.platzi.profesoresplatzi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.platzi.profesoresplatzi.dao.AbstractSession;
import com.platzi.profesoresplatzi.model.Course;
import com.platzi.profesoresplatzi.model.SocialMedia;
import com.platzi.profesoresplatzi.model.Teacher;

@SpringBootApplication
public class ProfesoresPlatziApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfesoresPlatziApplication.class, args);
		try {

				URL url = new URL("http://localhost:8080/login");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");

				String input = "{\"username\":\"matias\",\"password\":\"12345\"}";

				OutputStream os = conn.getOutputStream();
				os.write(input.getBytes());
				os.flush();
		                                
		                String token = conn.getHeaderField("Authorization");
		                
		                System.out.println("asdsad");
		                
				conn.disconnect();
		                                
		                url = new URL("http://localhost:8080/v1/socialMedias");
				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
		                conn.setRequestProperty("Authorization", token);
				conn.setRequestProperty("Accept", "application/json");

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
				}

				conn.disconnect();                

			  } catch (MalformedURLException e) {

				e.printStackTrace();

			  } catch (IOException e) {

				e.printStackTrace();

			  }

	    
		}
}
