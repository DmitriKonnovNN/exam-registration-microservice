output "public_ec_ip" {
  value       = aws_instance.app_reg-mcrsvc.public_ip
  description = "public ip value of ec2 instance"
  sensitive   = false
}
output "public_ec2_dns" {
  value       = aws_instance.app_reg-mcrsvc.public_dns
  description = "public dns of ec2 instance"

}

output "ec2-ami-info" {
  value = ({
    ami_id = "${aws_instance.app_reg-mcrsvc.ami}"
  volume_size = "${aws_instance.app_reg-mcrsvc.root_block_device[0].volume_size}" })

}

output "my_ip" {
  value       = local.my_public_ip.ip
  description = "IP of my local machine"
}

output "aws_default_vpc_info" {
  value       = data.aws_vpc.aws_default_vpc
  description = "default vpc of my aws account"
}

output "aws_default_vpc_subnet_ids" {
  description = "aws_default_vpc_subnet_ids"
  value       = data.aws_subnet_ids.subnets
}