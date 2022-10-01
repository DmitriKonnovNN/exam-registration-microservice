variable "owner" {
  type = string
}

variable "ami" {
  type    = string
  description = "Ubuntu 20.02 AMI"
  default = "ami-08c40ec9ead489470"
}

variable "app_tag_name" {
  description = "Enter tag name for all resources to be built"
  type        = string
}
variable "aws_region" {
  type        = string
  description = "AWS region"
}
variable "local_profile" {
  description = "local aws cli profile"
  type        = string
}

variable "app_main_port" {
  description = "standard port web server listens to"
  type = number
}
