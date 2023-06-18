provider "kubernetes" {
  load_config_file = "false"
  host = data.aws_eks_cluster.ex-reg-cluster.endpoint
  token = data.aws_eks_cluster_auth.ex-reg-cluster.token
  cluster_ca_certificate = base64decode(data.aws_eks_cluster.ex-reg-cluster.certificate_authority.0.data)

}

data "aws_eks_cluster" "ex-reg-cluster" {
  name = module.eks.cluster_id
}
data "aws_eks_cluster_auth" "ex-reg-cluster" {
  name = module.eks.cluster_id
}

module "eks" {
  source  = "terraform-aws-modules/eks/aws"
  version = "19.15.3"
  cluster_name = "${var.cluster_name}-cluster"
  cluster_version = "1.25"
  subnet_ids = module.eks_exam_reg_vpc.private_subnets
  vpc_id = module.eks_exam_reg_vpc.vpc_id
  self_managed_node_groups = {
    worker_group_small = {
      name = "worker-group-small"
      min_size = 2
      max_size = 2
      desired_size = 2
      instance_type = "t2.small"
    }
    worker_group_medium = {
      name = "worker-group-medium"
      min_size = 1
      max_size = 2
      desired_size = 1
      instance_type = "t2.medium"
    }
  }

  tags = {
    owner = var.owner
    provisionedBy = var.provisionedBy
    environment = "development"
  }
  putin_khuylo = true
}
