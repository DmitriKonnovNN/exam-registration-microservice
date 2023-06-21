provider "kubernetes" {

  host = data.aws_eks_cluster.default.endpoint
  /* token = data.aws_eks_cluster_auth.default.token */
  exec {
    api_version = "client.authentication.k8s.io/v1beta1"
    args        = ["eks", "get-token", "--cluster-name", data.aws_eks_cluster.default.id]
    command     = "aws"
  }
  cluster_ca_certificate = base64decode(data.aws_eks_cluster.default.certificate_authority.0.data)
}

data "aws_eks_cluster" "default" {
  #  name = var.cluster_name
  name = module.eks.cluster_name
}
data "aws_eks_cluster_auth" "default" {
  name = module.eks.cluster_name
}


/* data "aws_eks_cluster" "default" {
  #  name = var.cluster_name
  name = module.eks.cluster_id
}
data "aws_eks_cluster_auth" "default" {
  name = module.eks.cluster_id
} */

module "eks" {
  source                          = "terraform-aws-modules/eks/aws"
  version                         = "19.15.3"
  cluster_name                    = var.cluster_name
  cluster_version                 = "1.25"
  subnet_ids                      = module.eks_exam_reg_vpc.private_subnets
  vpc_id                          = module.eks_exam_reg_vpc.vpc_id
  cluster_endpoint_private_access = true
  cluster_endpoint_public_access  = true
  enable_irsa                     = true
  #  https://github.com/hashicorp/terraform-provider-aws/issues/27481
  cluster_addons = {
    coredns = {
      most_recent = true
    }
    kube-proxy = {
      most_recent = true
    }
    vpc-cni = {
      most_recent = true
    }
  }
  eks_managed_node_group_defaults = {
    disk_size = 50
  }
  #  eks can perform rolling upgrade without almost any downtime
  eks_managed_node_groups = {
    general = {
      desired_size = 1
      min_size     = 1
      max_size     = 3

      labels = {
        role = "general"
      }
      instance_types = ["t3.small"]
      capacity_type  = "ON_DEMAND"
    }
    spot = {
      desired_size = 1
      min_size     = 1
      max_size     = 3

      labels = {
        role = "spot"
      }
      instance_types = ["t3.micro"]
      capacity_types = "SPOT"
    }
  }
  tags = {
    owner         = var.owner
    provisionedBy = var.provisionedBy
    environment   = "development"
  }
  putin_khuylo              = true
  manage_aws_auth_configmap = true
  aws_auth_roles = [
    {
      rolearn  = module.eks_admins_iam_role.iam_role_arn
      username = module.eks_admins_iam_role.iam_role_name
      groups   = ["system:masters"]
    },
  ]
  /* this rule down below might not be necessary anymore*/
  /* node_security_group_additional_rules = {
    ingress_allow_access_from_control_plane = {
      type                          = "ingress"
      protocol                      = "tcp"
      from_port                     = 9443
      to_port                       = 9443
      source_cluster_security_group = true
      description                   = "Allow access from control plane to webhook port of AWS load balancer controller"
    }
  } */
  #  self_managed_node_group_defaults = {
  #    instance_type                          = "t3.micro"
  #    update_launch_template_default_version = true
  #    iam_role_additional_policies = {
  #      AmazonSSMManagedInstanceCore = "arn:aws:iam::aws:policy/AmazonSSMManagedInstanceCore"
  #    }
  #  }
  #
  #  self_managed_node_groups = {
  #    worker_group_small = {
  #      name                       = "worker-group-small"
  #      min_size                   = 2
  #      max_size                   = 2
  #      desired_size               = 2
  #      instance_type              = "t3.small"
  #      use_mixed_instances_policy = true
  #      mixed_instances_policy = {
  #        instances_distribution = {
  #          on_demand_base_capacity                  = 0
  #          on_demand_percentage_above_base_capacity = 10
  #          spot_allocation_strategy                 = "capacity-optimized"
  #        }
  #      }
  #    }
  #    worker_group_medium = {
  #      name          = "worker-group-medium"
  #      min_size      = 1
  #      max_size      = 2
  #      desired_size  = 1
  #      instance_type = "t3.medium"
  #    }
  #
  #
  #  }

}
output "endpoint" {
  value = data.aws_eks_cluster.default.endpoint
}

