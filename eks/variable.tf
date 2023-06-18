variable "vpc_cidr_block" {
  type = string
}
variable "private_subnet_cidr_blocks" {
  type = list(any)
}
variable "public_subnet_cidr_blocks" {
  type = list(any)
}

variable "owner" {
  type = string
}
variable "provisionedBy" {
  type = string
}

variable "aws_region" {
  type = string
}

variable "local_profile" {
  type = string
}

variable "cluster_name" {
  type = string
}