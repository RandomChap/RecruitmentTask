package com.empik.recruitment.repositorysearch;

import com.empik.recruitment.repositorysearch.controller.GitHubRepoController;
import com.empik.recruitment.repositorysearch.model.RepoInfo;
import com.empik.recruitment.repositorysearch.service.RepoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GitHubRepoControllerTest {

    @Mock
    private RepoService repoService;

    @InjectMocks
    private GitHubRepoController controller;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(controller).build();
    }

    @Test
    public void shouldReturnCorrectFormat() throws Exception {
        given(repoService.getRepoInfoByNameAndOwner("mockName", "mockOwner")).willReturn(getMockRepoInfo());
        mockMvc.perform(get("/repositories/{owner}/{repository-name}", "mockOwner", "mockName")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName", is("MockRepo FullName")))
                .andExpect(jsonPath("$.description", is("MockRepo Description")))
                .andExpect(jsonPath("$.cloneUrl", is("MockRepo CloneUrl")))
                .andExpect(jsonPath("$.createdAt", is("2020-01-01T00:00:00Z")))
                .andExpect(jsonPath("$.stars", is(5)));
    }


    @Test
    public void shouldReturnStatusOk() throws Exception {
        mockMvc.perform(get("/repositories/{owner}/{repository-name}", "mockOwner", "mockName")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnConnectionSuccessStatusForRemoteRepo() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<Object> response =
                template.getForEntity("https://api.github.com/repos/chatwoot/chatwoot", Object.class);
        assertEquals(200, response.getStatusCode().value());
    }

    private RepoInfo getMockRepoInfo() {
        RepoInfo repoInfo = new RepoInfo();
        repoInfo.setFullName("MockRepo FullName");
        repoInfo.setDescription("MockRepo Description");
        repoInfo.setCloneUrl("MockRepo CloneUrl");
        repoInfo.setCreatedAt("2020-01-01T00:00:00Z");
        repoInfo.setStars(5);
        return repoInfo;
    }

}
