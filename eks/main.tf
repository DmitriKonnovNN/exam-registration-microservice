terraform {

  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0.0"
    }

    helm = {
      source  = "hashicorp/helm"
      version = ">= 2.10.1"
    }

    kubernetes = {
      source  = "hashicorp/kubernetes"
      version = "2.21.1"
    }

    kubectl = {
      source  = "gavinbunney/kubectl"
      version = "1.14.0"
    }
  }
}

provider "aws" {
  profile = var.local_profile
  region  = var.aws_region
}