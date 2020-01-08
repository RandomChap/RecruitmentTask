package com.empik.recruitment.repositorysearch.service;

import com.empik.recruitment.repositorysearch.model.RepoInfo;

public interface RepoService {

    RepoInfo getRepoInfoByNameAndOwner(String name, String owner);
}
