package com.empik.recruitment.repositorysearch.controller;

import com.empik.recruitment.repositorysearch.model.RepoInfo;
import com.empik.recruitment.repositorysearch.service.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/repositories")
public class GitHubRepoController {

    @Autowired
    private RepoService repoService;

    @GetMapping(value = "/{owner}/{repository-name}")
    @ResponseBody
    public ResponseEntity<RepoInfo> getBasicInfo(@PathVariable("repository-name") String name,
                                                 @PathVariable String owner) {
        return ResponseEntity.ok(repoService.getRepoInfoByNameAndOwner(name, owner));
    }
}
