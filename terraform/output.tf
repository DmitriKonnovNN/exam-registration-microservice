output "public_ec_ip" {
  value       = aws_instance.app_reg-mcrsvc.public_ip
  description = "public ip value of ec2 instance"
  sensitive   = false
}
output "public_ec2_dns" {
  value       = aws_instance.app_reg-mcrsvc.public_dns
  description = "public dns of ec2 instance"

}