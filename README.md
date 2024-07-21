# AtiperaTask
Recruitment task for Atipera: <br>

As an api consumer, given username and header “Accept: application/json”, I would like to list all his github repositories, which are not forks. Information, which I require in the response, is: <br>

Repository Name <br>
Owner Login <br>
For each branch it’s name and last commit sha

As an api consumer, given not existing github user, I would like to receive 404 response in such a format:<br>
{
<br>
    &emsp; “status”: ${responseCode}<br>
    &emsp; “message”: ${whyHasItHappened}<br>
}
<br>
<br>
There is also option for Authorization token since there is limit for using github api, 
such token can be generated here https://github.com/settings/tokens 
and use on Postman like that:
![postmanExample.png](postmanExample.png)