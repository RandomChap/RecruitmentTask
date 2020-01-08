package com.empik.recruitment.repositorysearch.model;

import lombok.Data;

@Data
public class RepoInfo {

    private String fullName;
    private String description;
    private String cloneUrl;
    private String createdAt;
    private int stars;
}
