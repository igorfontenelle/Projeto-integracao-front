package main.java.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.measurer.apirest.models.MeasurerModel;
import com.measurer.apirest.resources.MeasurerResource;

public class ControllerApi {
	
	public List<MeasurerModel> apiGetListMeasurers(){
		String path = "http://localhost:8080/api/measurers";
		
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(path);
		Response response = target.request().get();
		String value = response.readEntity(String.class);
		
		Type listType = new TypeToken<ArrayList<MeasurerModel>>(){}.getType();
		List<MeasurerModel> listMeasurer = new Gson().fromJson(value, listType);
        response.close();  
		return listMeasurer;
	}
}
