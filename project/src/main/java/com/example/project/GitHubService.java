package com.example.project;

import com.example.project.GitHubClient.Branch;
import com.example.project.GitHubClient.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GitHubService {

    @Autowired
    private GitHubClient gitHubClient;

    public List<Map<String, Object>> getRepositoriesAndBranches(String username, String token){
        //check if there is Authorization token
        List<Repository> repositories = (token != null && !token.isEmpty())
                ? gitHubClient.listUserRepositories(username, "token " + token)
                : gitHubClient.listUserRepositories(username, null);
        List<Map<String, Object>> result = new ArrayList<>();

        for (Repository repo : repositories) {
            if (!repo.fork) {
                Map<String, Object> repoInfo = new LinkedHashMap<>();
                repoInfo.put("Repository Name", repo.name);
                repoInfo.put("Owner Login", repo.owner.login);

                //check if there is Authorization token
                List<Branch> branches = (token != null && !token.isEmpty())
                        ? gitHubClient.listRepositoryBranches(repo.owner.login, repo.name, "token " + token)
                        : gitHubClient.listRepositoryBranches(repo.owner.login, repo.name, null);

                Map<String, String> branchInfo = new LinkedHashMap<>();
                for (Branch branch : branches) {
                    branchInfo.put(branch.name, branch.commit.sha);
                }
                repoInfo.put("Branches", branchInfo);

                result.add(repoInfo);
            }
        }
        return result;
    }
}

