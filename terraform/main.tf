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
  ami           = data.aws_ami.amazon_linux_2_latest.id
  instance_type = "t2.micro"
  root_block_device {
    volume_size = 30
    volume_type = "gp2"
  }
  vpc_security_group_ids = [aws_security_group.sg-reg-mcrsvc.id]
  key_name               = aws_key_pair.ec2-key-pair.key_name
  user_data              = templatefile("compute-init.sh.tpl", "${}")
  tags = {
    Name  = "ec2-${var.app_tag_name}"
    Owner = "ec2-${var.owner}"
  }

}
resource "aws_key_pair" "ec2-key-pair" {
  key_name   = "ec2-key-pair"
  public_key = tls_private_key.rsa-4096-ec2.public_key_openssh
}

resource "tls_private_key" "rsa-4096-ec2" {
  algorithm = "RSA"
  rsa_bits  = 4096
}

resource "local_file" "ec2-rsa-keys" {
  content         = tls_private_key.rsa-4096-ec2.private_key_pem
  file_permission = 400
  filename        = "${var.app_tag_name}-rsa-keys.pem"
}

resource "aws_security_group" "sg-reg-mcrsvc" {
  name        = "reg-mcrsvc_${var.app_tag_name}"
  description = "sg-reg-mcrsvc_${var.app_tag_name} inbound traffic"

  ingress {
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
    from_port        = var.app_main_port
    to_port          = var.app_main_port
    protocol         = "tcp"
    description      = "http"
  }
  ingress {
    cidr_blocks = ["${local.my_public_ip.ip}/32"]
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    description = "ssh"
  }
  ingress {
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
    from_port        = 443
    to_port          = 443
    protocol         = "tcp"
    description      = "https"
  }
  egress {
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
    from_port        = 0
    protocol         = "-1"
    to_port          = 0
    description      = "allow all egress traffic"
  }
  tags = {
    Name  = "sg-${var.app_tag_name}"
    Owner = var.owner

  }
}
locals {
  my_public_ip = jsondecode(data.http.my_public_ip.response_body)
}





#                 cd /var/www && docker compose up -d zipkin maildev
#                 echo "containers running: $(docker ps)"
#                 