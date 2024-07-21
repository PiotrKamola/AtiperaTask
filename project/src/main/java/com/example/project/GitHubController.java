package com.example.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GitHubController {

    @Autowired
    private GitHubService gitHubService;

    @GetMapping("/repositories")
    public List<Map<String, Object>> listRepositories(
            @RequestParam("username") String username,
            @RequestHeader(value = "Authorization", required = false) String token
    ) {
        try {
            return gitHubService.getRepositoriesAndBranches(username, token);
        } catch (Exception e) {
            throw new UserNotFoundException("GitHub user not found: " + username);
        }
    }
}
