// This is currently the best way to push a tag (or a branch, etc) from a
// Pipeline job. It's not ideal - https://issues.jenkins-ci.org/browse/JENKINS-28335
// is an open JIRA for getting the GitPublisher Jenkins functionality working
// with Pipeline.

// credentialsId here is the credentials you have set up in Jenkins for pushing
// to that repository using username and password.
withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'MyID', usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD']]) {

    sh("git tag -a some_tag -m 'Jenkins'")
    sh("git push https://${env.GIT_USERNAME}:${env.GIT_PASSWORD}@<REPO> --tags")
}

// There isn't as trivial a way to override the ssh key if you want to push that
// way, but I hope to add that here once I find a reasonable approach.
