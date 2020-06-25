package pl.entpoint.covid.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import pl.entpoint.covid.model.Corona;

/**
 * @author Mateusz Dąbek
 * @created 22 cze 2020
 * 
 */

@Service
public class CoronaDataService {
	
	private static final String DATA_CONF_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private static final String DATA_DEATH_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
	private static final String DATA_RECOV_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";
	
	private List<Corona> confSats = new ArrayList<>();
	private List<Corona> deathSats = new ArrayList<>();
	private List<Corona> recovSats = new ArrayList<>();
	
	public List<Corona> getConfSats() {
		return confSats;
	}
	
	
	public List<Corona> getDeathSats() {
		return deathSats;
	}

	public List<Corona> getRecovSats() {
		return recovSats;
	}

	@PostConstruct
	@Scheduled(cron = "0 */12 * * * *") // At minute 0 past every 12th hour.
	public void fechtData() {
		try {
			this.confSats = updateData(DATA_CONF_URL);
			this.deathSats = updateData(DATA_DEATH_URL);
			this.recovSats = updateData(DATA_RECOV_URL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Corona> updateData(String URL) throws IOException, InterruptedException {
		List<Corona> newStats = new ArrayList<>();

		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest httpRequest = HttpRequest.newBuilder()
			.uri(URI.create(URL))
			.build();
		
		HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
		 
 		// Test
		// System.out.println(httpResponse.body());
		
		// CSV conv
		StringReader csvReader = new StringReader(httpResponse.body());
		
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
		for(CSVRecord record: records) {
			Corona locationStats = new Corona();
			int lastDay = Integer.parseInt(record.get(record.size() - 1));
			int prevDay = Integer.parseInt(record.get(record.size() - 2));
			
			locationStats.setCountry(record.get("Country/Region"));
			locationStats.setProvince(record.get("Province/State"));
			locationStats.setLatestTotalCases(lastDay);
			locationStats.setLastDayDiffrent(lastDay - prevDay);
			
			newStats.add(locationStats);
		}		
		return newStats;		
	}
}
