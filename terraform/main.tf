terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 3.27"
    }
  }
  #   required_version = "~>0.12.0"
}

provider "aws" {
  profile = var.local_profile
  region  = var.aws_region
}

resource "aws_instance" "app_reg-mcrsvc" {
  ami                    = var.ami
  instance_type          = "t2.micro"
  vpc_security_group_ids = [aws_security_group.sg-reg-mcrsvc.id]
  user_data              = <<-EOF
                #!/bin/bash
                sudo apt-get update
                curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
                sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
                sudo apt-get update
                apt-cache policy docker-ce
                sudo apt-get install -y docker-ce
                sudo service docker start
                sudo service docker status 
                sudo docker run hello-world
                &
                EOF
  tags = {
    Name  = "ec2-${var.app_tag_name}"
    Owner = "ec2-${var.owner}"
  }

}

resource "aws_security_group" "sg-reg-mcrsvc" {
  name        = "allow_port_${var.app_main_port}"
  description = "Allow port ${var.app_main_port} inbound traffic"
  ingress {
    cidr_blocks = ["0.0.0.0/0"]
    from_port   = var.app_main_port
    to_port     = var.app_main_port
    protocol    = "tcp"
    description = "allow all tcp over http"
  }
  ingress {
    cidr_blocks = ["0.0.0.0/0"]
    from_port   = 443
    to_port     = 443
    protocol    = "tcp"
    description = "allow all tcp over https"
  }
  egress {
    cidr_blocks = ["0.0.0.0/0"]
    from_port   = 0
    protocol    = "-1"
    to_port     = 0
    description = "allow all egress traffic"
  }
  tags = {
    Name  = "sg-${var.app_tag_name}"
    Owner = var.owner

  }
}