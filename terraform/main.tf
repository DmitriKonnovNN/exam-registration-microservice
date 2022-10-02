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
  key_name = aws_key_pair.ec2-key-pair.key_name
  user_data              = <<-EOF
                #!/bin/bash
                echo "-----------------------START BOOTSTRAPPING-----------------------"
                apt-get update
                curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
                add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
                apt-get update
                apt-cache policy docker-ce
                apt-get install -y docker-ce
                service docker start
                service docker status
                "UserData executed on $(date)">>/var/www/html/log.txt &
                echo "---------------------FINISH BOOTSTRAPPING_________________________"
                &
                EOF
  tags = {
    Name  = "ec2-${var.app_tag_name}"
    Owner = "ec2-${var.owner}"
  }

}
resource "aws_key_pair" "ec2-key-pair" {
  key_name   = "deployer-key"
  public_key = tls_private_key.rsa-4096-ec2.public_key_openssh
}

resource "tls_private_key" "rsa-4096-ec2" {
  algorithm = "RSA"
  rsa_bits = 4096
}

resource "local_file" "ec2-rsa-keys" {
  content = tls_private_key.rsa-4096-ec2.private_key_pem
  file_permission = 400
  filename = "${var.app_tag_name}-rsa-keys"
}

resource "aws_security_group" "sg-reg-mcrsvc" {
  name        = "allow_port_${var.app_main_port}"
  description = "Allow port ${var.app_main_port} inbound traffic"

  ingress {
    cidr_blocks = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
    from_port   = var.app_main_port
    to_port     = var.app_main_port
    protocol    = "tcp"
    description = "http"
  }
  ingress {
    cidr_blocks = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
    from_port = 22
    to_port = 22
    protocol = "tcp"
    description = "ssh"
  }
  ingress {
    cidr_blocks = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
    from_port   = 443
    to_port     = 443
    protocol    = "tcp"
    description = "https"
  }
  egress {
    cidr_blocks = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
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