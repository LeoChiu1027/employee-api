#!/bin/bash

# Jenkins
wget -q -O - https://pkg.jenkins.io/debian-stable/jenkins.io.key | sudo apt-key add -
sudo sh -c 'echo deb https://pkg.jenkins.io/debian-stable binary/ > /etc/apt/sources.list.d/jenkins.list'
sudo apt-get install dirmngr -y
sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys 0xB1998361219BD9C9
sudo apt-add-repository "deb http://repos.azul.com/azure-only/zulu/apt stable main"
sudo apt-get update
echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 select true" | sudo debconf-set-selections
sudo apt-get -y install zulu-8-azure-jdk
sudo update-alternatives --set java /usr/lib/jvm/zulu-8-azure-amd64/jre/bin/java
echo "JAVA_HOME='/usr/lib/jvm/zulu-8-azure-amd64'" >> /etc/profile
echo "PATH=$PATH:$HOME/bin:$JAVA_HOME/bin" >> /etc/profile
echo "export JAVA_HOME" >> /etc/profile
echo "export PATH" >> /etc/profile
sudo . /etc/environment
echo $JAVA_HOME
sudo apt-get install jenkins -y

echo $PATH

# Docker
sudo apt-get install apt-transport-https ca-certificates curl software-properties-common -y
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo apt-key fingerprint 0EBF:CD88
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
sudo apt-get update
sudo apt-get install docker-ce -y

# Azure CLI
curl -sL https://aka.ms/InstallAzureCLIDeb | sudo bash

# Kubectl
cd /tmp/
sudo curl -kLO https://storage.googleapis.com/kubernetes-release/release/v1.8.0/bin/linux/amd64/kubectl
chmod +x ./kubectl
sudo mv ./kubectl /usr/local/bin/kubectl

# Configure access
usermod -aG docker jenkins
usermod -aG docker azureuser
sudo touch /var/lib/jenkins/jenkins.install.InstallUtil.lastExecVersion
service jenkins restart
