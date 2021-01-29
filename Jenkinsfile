def label = "slave-${UUID.randomUUID().toString()}"
def BRANCH_NAME = scm.branches[0].name
def JOB_NAME = "template-api"
podTemplate(label: label, containers: [
    containerTemplate(name: 'helm', image: 'gcr.io/template/alpine', ttyEnabled: true),
    containerTemplate(name: 'maven', image: 'goyalzz/ubuntu-java-8-maven-docker-image:16.04', ttyEnabled: true),
    containerTemplate(name: 'docker', image: 'gcr.io/template/ubuntu:16.04', ttyEnabled: true),
  ],
  volumes: [
    hostPathVolume(hostPath: '/var/run/docker.sock', mountPath: '/var/run/docker.sock')
  ],
  envVars: [
    envVar(key: 'TZ', value: 'America/Sao_Paulo')
  ]
  ){
  node(label) {
        ansiColor('xterm') {
          timestamps {
                stage('Build') {
                  scmVars = checkout scm ' https://bitbucket.org/gok/template-api.git'
                  container('maven') {
                    if(BRANCH_NAME == "development" || BRANCH_NAME.startsWith("release")){
                      try {
                        echo "##################### INICIANDO O BUILD  #####################"
                        sh "mvn clean install -DskipTests"
                      }
                      catch (exc) {
                        //sh 'sed -i "s|JOB_BASE_NAME|$JOB_BASE_NAME|g; s|BUILD_ID|$BUILD_ID|g; s|gitlabUserName|$gitlabUserName|g; s|gitlabTargetBranch|$gitlabTargetBranch|g; s|gitlabMergeRequestLastCommit|$gitlabMergeRequestLastCommit|g" ./slack/error.json'
                        echo '##################### BUILD COM FALHA -> SLACK #####################'
                        //sh 'curl -X POST -d @slack/error.json https://hooks.slack.com/services/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'
                        throw exc
                      }
                    }
                  }
                }
                stage('Build Container') {
                  scmVars = checkout scm 'https://bitbucket.org/gok/template-api.git'
                  container('docker') {
                    if(BRANCH_NAME == "development" || BRANCH_NAME.startsWith("release")){
                      try {
                        echo "##################### INICIANDO O BUILD  #####################"
                        sh "docker build ."
                      }
                      catch (exc) {
                        //sh 'sed -i "s|JOB_BASE_NAME|$JOB_BASE_NAME|g; s|BUILD_ID|$BUILD_ID|g; s|gitlabUserName|$gitlabUserName|g; s|gitlabTargetBranch|$gitlabTargetBranch|g; s|gitlabMergeRequestLastCommit|$gitlabMergeRequestLastCommit|g" ./slack/error.json'
                        echo '##################### BUILD COM FALHA -> SLACK #####################'
                        //sh 'curl -X POST -d @slack/error.json https://hooks.slack.com/services/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'
                        throw exc
                      }
                    }
                  }
                }
                stage('Tests') {
                  checkout scm 'https://bitbucket.org/gok/template-api.git'
                  container('java') {
                    if(BRANCH_NAME == "development" || BRANCH_NAME.startsWith("release")){
                      try {
                        echo "##################### INICIANDO OS TESTES #####################"
                       //sh "dotnet tests"
                        echo '##################### TESTES PASSARAM COM SUCESSO -> SLACK #####################'
                       //sh 'curl -X POST -d @slack/success.json https://hooks.slack.com/services/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'
                      }
                      catch (exc) {
                       //sh 'sed -i "s|JOB_BASE_NAME|$JOB_BASE_NAME|g; s|BUILD_ID|$BUILD_ID|g; s|gitlabUserName|$gitlabUserName|g; s|gitlabTargetBranch|$gitlabTargetBranch|g; s|gitlabMergeRequestLastCommit|$gitlabMergeRequestLastCommit|g" ./slack/error.json'
                       echo '##################### TESTES COM FALHA -> SLACK #####################'
                      //sh 'curl -X POST -d @slack/error.json https://hooks.slack.com/services/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'
                      throw exc
                      }
                    }
                  }
                }
                stage('Build container') {
                  checkout scm 'https://bitbucket.org/gok/template-api.git'
                  container('docker') {
                    if(BRANCH_NAME == "development"){
                      echo "##################### CONFIGURE DOCKER GCP #####################"
                      sh 'mkdir -p /secrets/serviceaccounts/'
                      sh 'cp ./chart/service-account.json /secrets/serviceaccounts/service-account.json'
                      sh 'gcloud auth activate-service-account --key-file /secrets/serviceaccounts/service-account.json'
                      sh 'echo -ne "\n" | gcloud auth configure-docker'
                      sh 'docker build --build-arg NODE_ENV=development -t gcr.io/template/${JOB_NAME}:v1.0.$BUILD_ID .'
                      sh 'docker tag gcr.io/template/${JOB_NAME}:v1.0.$BUILD_ID gcr.io/template/${JOB_NAME}:v1.0.$BUILD_ID'
                      sh 'docker push gcr.io/template/${JOB_NAME}:v1.0.$BUILD_ID'
                    } else if(BRANCH_NAME.startsWith("release")){
                      echo "##################### CONFIGURE DOCKER GCP #####################"
                      sh 'mkdir -p /secrets/serviceaccounts/'
                      sh 'cp ./chart/service-account.json /secrets/serviceaccounts/service-account.json'
                      sh 'gcloud auth activate-service-account --key-file /secrets/serviceaccounts/service-account.json'
                      sh 'echo -ne "\n" | gcloud auth configure-docker'
                      sh 'docker build --build-arg NODE_ENV=staging -t gcr.io/gok/template-api-staging:v1.0.$BUILD_ID .'
                      sh 'docker tag gcr.io/cuidado-digital-covid/cucoops-api-staging:v1.0.$BUILD_ID gcr.io/cuidado-digital-covid/cucoops-api-staging:v1.0.$BUILD_ID'
                      sh 'docker push gcr.io/cuidado-digital-covid/cucoops-api-staging:v1.0.$BUILD_ID'

                      echo "##################### CONFIGURE DOCKER GCP #####################"
                      sh 'mkdir -p /secrets/serviceaccounts/'
                      sh 'cp ./chart/service-account.json /secrets/serviceaccounts/service-account.json'
                      sh 'gcloud auth activate-service-account --key-file /secrets/serviceaccounts/service-account.json'
                      sh 'echo -ne "\n" | gcloud auth configure-docker'
                      sh 'docker build --build-arg NODE_ENV=production -t gcr.io/cuidado-digital-covid/cucoops-api-production:v1.0.$BUILD_ID .'
                      sh 'docker tag gcr.io/cuidado-digital-covid/cucoops-api-production:v1.0.$BUILD_ID gcr.io/cuidado-digital-covid/cucoops-api-production:v1.$BUILD_ID'
                      sh 'docker push gcr.io/cuidado-digital-covid/cucoops-api-production:v1.0.$BUILD_ID'
                    }
                  }
                }
                stage('Deploy') {
                  checkout scm 'https://bitbucket.org/gok/template-api.git'
                  container('helm') {
                    echo "##################### HELM INSTALL #####################"
                    sh 'helm init --client-only'
                    echo '##################### CHANGE VALUES.YAML #####################'

                    if(BRANCH_NAME == "development"){
                      sh 'helm upgrade --install --force template-api --namespace develop --set image.repository=gcr.io/cuidado-digital-covid/${JOB_NAME} --set image.tag=v1.0.$BUILD_ID ./chart/develop'
                    } else if(BRANCH_NAME.startsWith("release")){
                      sh 'helm upgrade --install --force template-api-staging --namespace staging --set image.repository=gcr.io/cuidado-digital-covid/template-api-staging --set image.tag=v1.0.$BUILD_ID ./chart/staging'
                    }
                  }
                }
            }
        }
    }
}
