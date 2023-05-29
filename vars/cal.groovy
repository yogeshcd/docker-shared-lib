def login() {
    withCredentials([usernamePassword(credentialsId: 'e698243d-6ee0-47c3-9901-9d62b9d7e941', usernameVariable: 'username', passwordVariable: 'password')]) {
        sh """
            docker login --username="${username}" --password="${password}"
        """
    }
}

def build(String build_tag) {
    def scriptcontents = libraryResource "Dockerfile"
    writeFile file:"Dockerfile", text: scriptcontents

    sh """
        docker build -t "${build_tag}"  .
    """
}

def push(String tag) {
    sh """
        docker push "${tag}"
    """
}
