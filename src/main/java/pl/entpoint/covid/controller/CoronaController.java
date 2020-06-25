package pl.entpoint.covid.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

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
		List<Corona> conf = coronaService.getConfSats();
		List<Corona> death = coronaService.getDeathSats();
		List<Corona> recov = coronaService.getRecovSats();
		
		// Confirmed numbers
		int totalConfCases = conf.stream()
				.mapToInt(corona -> corona.getLatestTotalCases())
				.sum();
		int totalConfNewCases = conf.stream()
				.mapToInt(corona -> corona.getLastDayDiffrent())
				.sum();
		// Deaths numbers
		int totalDeathCases = death.stream()
				.mapToInt(corona -> corona.getLatestTotalCases())
				.sum();
		int totalDeathNewCases = death.stream()
				.mapToInt(corona -> corona.getLastDayDiffrent())
				.sum();
		// Recovered numbers
		int totalRecovCases = recov.stream()
				.mapToInt(corona -> corona.getLatestTotalCases())
				.sum();
		int totalRecovNewCases = recov.stream()
				.mapToInt(corona -> corona.getLastDayDiffrent())
				.sum();
		
		model.addAttribute("totalConfCases", totalConfCases);
		model.addAttribute("totalDeathCases", totalDeathCases);
		model.addAttribute("totalRecovCases", totalRecovCases);
		model.addAttribute("totalConfNewCases", totalConfNewCases);
		model.addAttribute("totalDeathNewCases", totalDeathNewCases);
		model.addAttribute("totalRecovNewCases", totalRecovNewCases);
		return "home";
	}
	
	@GetMapping("/conf")
	public String conf(Model model) {
		List<Corona> stats = coronaService.getConfSats();
		int totalCases = stats.stream()
				.mapToInt(corona -> corona.getLatestTotalCases())
				.sum();
		int totalNewCases = stats.stream()
				.mapToInt(corona -> corona.getLastDayDiffrent())
				.sum();
		
		model.addAttribute("totalStats", totalCases);
		model.addAttribute("totalNew", totalNewCases);
		model.addAttribute("coronaStats", stats);
		
		return "conf";
	}
	
	@GetMapping("/death")
	public String death(Model model) {
		List<Corona> stats = coronaService.getDeathSats();
		int totalCases = stats.stream()
				.mapToInt(corona -> corona.getLatestTotalCases())
				.sum();
		int totalNewCases = stats.stream()
				.mapToInt(corona -> corona.getLastDayDiffrent())
				.sum();
		
		model.addAttribute("totalStats", totalCases);
		model.addAttribute("totalNew", totalNewCases);
		model.addAttribute("coronaStats", stats);
		
		return "death";
	}
	
	@GetMapping("/recov")
	public String recov(Model model) {
		List<Corona> stats = coronaService.getRecovSats();
		int totalCases = stats.stream()
				.mapToInt(corona -> corona.getLatestTotalCases())
				.sum();
		int totalNewCases = stats.stream()
				.mapToInt(corona -> corona.getLastDayDiffrent())
				.sum();
		
		model.addAttribute("totalStats", totalCases);
		model.addAttribute("totalNew", totalNewCases);
		model.addAttribute("coronaStats", stats);
		
		return "recov";
	}
}
