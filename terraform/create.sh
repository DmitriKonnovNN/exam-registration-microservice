#!/bin/bash
set -e 
terraform output -json > output.json
terraform apply [plan.tfplan] > ./output.json
mv *-rsa-keys.pem ~/dkuser/.ssh/