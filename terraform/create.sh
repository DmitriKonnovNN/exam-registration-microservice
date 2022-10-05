#!/bin/bash
set -e 
PN="plan-$(USER)"
terraform plan -out="$PN".tfplan
terraform apply "$PN".tfplan
export EC2_PBL_DNS=$(terraform output -json public_ec2_dns)
cp *-rsa-keys.pem ~/.ssh/
