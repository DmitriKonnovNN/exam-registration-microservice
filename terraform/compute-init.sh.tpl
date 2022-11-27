#!/bin/zsh

 echo "-----------------------START BOOTSTRAPPING-----------------------"
                yum -y update
                yum -y install yum-utils
                
                yum-config-manager --add-repo
                amazon-linux-extras install docker
                service docker start
                service docker status
                mkdir var/www
                chmod 777 var/www
                curl https://raw.githubusercontent.com/DmitriKonnovNN/exam-registration-microservice/017a0d46107d1ee75dbb897998381acc1e1a1c02/docker-compose.yaml -O > /var/www/docker-compose.yaml
                curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
                chmod +x /usr/local/bin/docker-compose
    
                echo "$(docker-compose --version)"
                gpasswd -a $USER docker
                newgrp docker
                docker-compose up -d /var/www
                "UserData executed on $(date)">>/var/www/html/log.txt &
                echo "---------------------FINISH BOOTSTRAPPING_________________________"