package com.example.world.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.world.domain.Country;
import com.example.world.repository.WorldDao;

@RestController
@RequestScope
public class WorldApi {
	@Autowired
	private WorldDao worldDao;
	
	// GET http://localhost:7200/world/api/v1/continents
	@GetMapping(value = "/continents"
			//produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
			)
	public Collection<String> getAllContinents(){
		return worldDao.getAllContinents();
	}
	
	// GET http://localhost:7200/world/api/v1/countries?continent=Asia
	@GetMapping("/countries") // /countries?continent=Asia // /countries -> continent=Asia
	public Collection<Country> getContinentCountries(
			@RequestParam(required = false, defaultValue = "Asia") String continent){
		return worldDao.findCountriesByContinent(continent);
	}
}
