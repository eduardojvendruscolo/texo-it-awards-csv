package com.texoid.movies.demo;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void testMinMaxAwardsIntervals() throws Exception {

		MvcResult result = mvc.perform(MockMvcRequestBuilders
				.get("/movies/min-max")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		//test min results
		JSONArray minIntervalAwardsList = JsonPath.read(result.getResponse().getContentAsString(), "min");
		Assertions.assertEquals(2, minIntervalAwardsList.size());
		Assertions.assertEquals("{producer=Eduardo, interval=1, previousWin=1980, followingWin=1981}", minIntervalAwardsList.get(0).toString());
		Assertions.assertEquals("{producer=Eduardo, interval=1, previousWin=1982, followingWin=1983}", minIntervalAwardsList.get(1).toString());

		//test max results
		JSONArray maxIntervalAwardsList = JsonPath.read(result.getResponse().getContentAsString(), "max");
		Assertions.assertEquals(1, maxIntervalAwardsList.size());
		Assertions.assertEquals("{producer=Nina, interval=3, previousWin=1984, followingWin=1987}", maxIntervalAwardsList.get(0).toString());

	}

}
