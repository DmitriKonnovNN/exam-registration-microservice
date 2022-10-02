variable "owner" {
  type = string
}

variable "ami" {
  type    = string
  default = "ami-026b57f3c383c2eec"
  description = "ami amazon linux 2"
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
