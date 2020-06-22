package pl.entpoint.covid.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.entpoint.covid.model.Corona;
import pl.entpoint.covid.services.CoronaDataService;

/**
 * @author Mateusz Dąbek
 * @created 22 cze 2020
 * 
 */

@Controller
public class CoronaController {
	
	@Autowired
	CoronaDataService coronaService;

	@GetMapping("/")
	public String home(Model model) {
		List<Corona> stats = coronaService.getLocationsStats();
		int totalCases = stats.stream()
				.mapToInt(corona -> corona.getLatestTotalCases())
				.sum();
		int totalNewCases = stats.stream()
				.mapToInt(corona -> corona.getLastDayDiffrent())
				.sum();
		
		model.addAttribute("totalStats", totalCases);
		model.addAttribute("totalNew", totalNewCases);
		model.addAttribute("coronaStats", coronaService.getLocationsStats());
		return "home";
	}
}
