package com.empik.recruitment.repositorysearch.service;


import com.empik.recruitment.repositorysearch.mapper.ResponseMapper;
import com.empik.recruitment.repositorysearch.model.RepoInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GitHubService implements RepoService {

    private static final String SLASH = "/";

    @Value("${git.url}")
    private String url;

    @Override
    public RepoInfo getRepoInfoByNameAndOwner(String name, String owner) {
        return ResponseMapper.map(getExternalResponse(name, owner));
    }

    private String getExternalResponse(String name, String owner) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(
                url + SLASH + owner + SLASH + name,
                String.class);
    }
}
