package com.example.project;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "githubClient", url = "https://api.github.com")
public interface GitHubClient {

    @GetMapping("/users/{username}/repos")
    List<Repository> listUserRepositories(
            @PathVariable("username") String username,
            @RequestHeader(value = "Authorization", required = false) String token
    );

    @GetMapping("/repos/{owner}/{repo}/branches")
    List<Branch> listRepositoryBranches(
            @PathVariable("owner") String owner,
            @PathVariable("repo") String repo,
            @RequestHeader(value = "Authorization", required = false) String token
    );

    class Repository {
        public String name;
        public Owner owner;
        public boolean fork;
    }

    class Owner {
        public String login;
    }

    class Branch {
        public String name;
        public Commit commit;
    }

    class Commit {
        public String sha;
    }
}
