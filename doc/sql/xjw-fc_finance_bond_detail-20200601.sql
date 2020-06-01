ALTER TABLE `finance`.`fc_finance_bond_detail` 
MODIFY COLUMN `subsidy` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '本年度政府补贴' AFTER `qualification`,
ADD COLUMN `lastSubsidy` varchar(255) NULL COMMENT '上一年度政府补贴' AFTER `deleted`;

