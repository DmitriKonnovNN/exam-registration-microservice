
data "aws_availability_zones" "azs" {

}
module "eks_exam_reg_vpc" {
  source                 = "terraform-aws-modules/vpc/aws"
  version                = "5.0.0"
  name                   = "${var.cluster_name}_vpc"
  cidr                   = var.vpc_cidr_block
  private_subnets        = var.private_subnet_cidr_blocks
  public_subnets         = var.public_subnet_cidr_blocks
  azs                    = data.aws_availability_zones.azs.names
  enable_nat_gateway     = true
  single_nat_gateway     = true
  one_nat_gateway_per_az = false
  enable_dns_hostnames   = true
  enable_dns_support     = true
  tags = {
    "kubernetes.io/cluster/${var.cluster_name}" = "shared"
    "owner"                                     = var.owner
    "provisionedBY"                             = var.provisionedBy
  }
  public_subnet_tags = {
    "kubernetes.io/cluster/${var.cluster_name}" = "shared"
    "kubernetes.io/role/elb"                    = 1
    "owner"                                     = var.owner
    "provisionedBY"                             = var.provisionedBy
  }
  private_subnet_tags = {
    "kubernetes.io/cluster/${var.cluster_name}" = "shared"
    "kubernetes.io/role/internal-elb"           = 1
    "owner"                                     = var.owner
    "provisionedBY"                             = var.provisionedBy
  }
}