package com.empik.recruitment.repositorysearch.mapper;

import com.empik.recruitment.repositorysearch.model.RepoInfo;
import org.json.JSONObject;

public class ResponseMapper {

    public static RepoInfo map(String restResponse) {
        JSONObject jsonObject = new JSONObject(restResponse);
        RepoInfo repoInfo = new RepoInfo();
        repoInfo.setFullName(jsonObject.getString("full_name"));
        repoInfo.setDescription((!jsonObject.get("description").toString().equals("null")) ? jsonObject.getString("description") : null);
        repoInfo.setCloneUrl(jsonObject.getString("clone_url"));
        repoInfo.setCreatedAt(jsonObject.getString("created_at"));
        repoInfo.setStars(jsonObject.getInt("stargazers_count"));
        return repoInfo;
    }

}